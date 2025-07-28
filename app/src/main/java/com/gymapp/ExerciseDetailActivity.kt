package com.gymapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gymapp.data.GymDataRepository
import com.gymapp.database.GymDatabaseHelper
import com.gymapp.model.Exercise
import com.gymapp.model.ExerciseRecord

/**
 * نشاط تفاصيل التمرين
 * يعرض معلومات التمرين مع إمكانية إدخال وحفظ الوزن والتكرارات
 */
class ExerciseDetailActivity : AppCompatActivity() {
    
    private var exerciseId: Int = 0
    private lateinit var exercise: Exercise
    private lateinit var databaseHelper: GymDatabaseHelper
    
    // عناصر الواجهة
    private lateinit var exerciseNameTextView: TextView
    private lateinit var exerciseDescriptionTextView: TextView
    private lateinit var exerciseImageView: ImageView
    private lateinit var weightEditText: EditText
    private lateinit var repetitionsEditText: EditText
    private lateinit var saveButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)
        
        // الحصول على معرف التمرين من Intent
        exerciseId = intent.getIntExtra("EXERCISE_ID", 1)
        
        // إنشاء مساعد قاعدة البيانات
        databaseHelper = GymDatabaseHelper(this)
        
        // ربط عناصر الواجهة
        bindViews()
        
        // إعداد الواجهة
        setupUI()
        
        // تحميل البيانات المحفوظة سابقاً
        loadSavedData()
        
        // إعداد أزرار التحكم
        setupButtons()
    }
    
    /**
     * ربط عناصر الواجهة بالمتغيرات
     */
    private fun bindViews() {
        exerciseNameTextView = findViewById(R.id.tv_exercise_name)
        exerciseDescriptionTextView = findViewById(R.id.tv_exercise_description)
        exerciseImageView = findViewById(R.id.iv_exercise_image)
        weightEditText = findViewById(R.id.et_weight)
        repetitionsEditText = findViewById(R.id.et_repetitions)
        saveButton = findViewById(R.id.btn_save)
    }
    
    /**
     * إعداد واجهة المستخدم وعرض بيانات التمرين
     */
    private fun setupUI() {
        // الحصول على بيانات التمرين
        val exerciseData = GymDataRepository.getExerciseById(exerciseId)
        
        if (exerciseData != null) {
            exercise = exerciseData
            
            // عرض اسم التمرين
            exerciseNameTextView.text = getString(exercise.nameResourceId)
            
            // عرض وصف التمرين
            exerciseDescriptionTextView.text = getString(exercise.descriptionResourceId)
            
            // عرض صورة التمرين
            exerciseImageView.setImageResource(exercise.imageResourceId)
        }
    }
    
    /**
     * تحميل البيانات المحفوظة سابقاً من قاعدة البيانات
     */
    private fun loadSavedData() {
        val savedRecord = databaseHelper.getLatestExerciseRecord(exerciseId)
        
        if (savedRecord != null) {
            // عرض البيانات المحفوظة في الحقول
            weightEditText.setText(savedRecord.weight.toString())
            repetitionsEditText.setText(savedRecord.repetitions.toString())
        }
    }
    
    /**
     * إعداد أزرار التحكم
     */
    private fun setupButtons() {
        // زر الحفظ
        saveButton.setOnClickListener {
            saveExerciseData()
        }
        
        // زر العودة
        findViewById<Button>(R.id.btn_back).setOnClickListener {
            finish() // العودة للشاشة السابقة
        }
    }
    
    /**
     * حفظ بيانات التمرين في قاعدة البيانات
     */
    private fun saveExerciseData() {
        val weightText = weightEditText.text.toString().trim()
        val repetitionsText = repetitionsEditText.text.toString().trim()
        
        // التحقق من صحة البيانات
        if (weightText.isEmpty()) {
            weightEditText.error = "يرجى إدخال الوزن"
            return
        }
        
        if (repetitionsText.isEmpty()) {
            repetitionsEditText.error = "يرجى إدخال عدد التكرارات"
            return
        }
        
        try {
            val weight = weightText.toDouble()
            val repetitions = repetitionsText.toInt()
            
            // التحقق من القيم المنطقية
            if (weight <= 0) {
                weightEditText.error = "يجب أن يكون الوزن أكبر من صفر"
                return
            }
            
            if (repetitions <= 0) {
                repetitionsEditText.error = "يجب أن يكون عدد التكرارات أكبر من صفر"
                return
            }
            
            // إنشاء سجل التمرين
            val exerciseRecord = ExerciseRecord(
                exerciseId = exerciseId,
                weight = weight,
                repetitions = repetitions
            )
            
            // حفظ السجل في قاعدة البيانات
            val result = databaseHelper.saveExerciseRecord(exerciseRecord)
            
            if (result > 0) {
                // عرض رسالة نجاح
                Toast.makeText(this, getString(R.string.data_saved), Toast.LENGTH_SHORT).show()
                
                // مسح رسائل الخطأ
                weightEditText.error = null
                repetitionsEditText.error = null
            } else {
                // عرض رسالة خطأ
                Toast.makeText(this, "حدث خطأ أثناء حفظ البيانات", Toast.LENGTH_SHORT).show()
            }
            
        } catch (e: NumberFormatException) {
            // خطأ في تحويل النصوص لأرقام
            Toast.makeText(this, "يرجى إدخال أرقام صحيحة", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // إغلاق قاعدة البيانات
        databaseHelper.close()
    }
}