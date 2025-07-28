package com.gymapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gymapp.data.GymDataRepository
import com.gymapp.model.Exercise

/**
 * نشاط عرض قائمة التمارين لعضلة معينة
 * يعرض جميع التمارين (5 تمارين) للعضلة المختارة
 */
class ExerciseListActivity : AppCompatActivity() {
    
    private var muscleId: Int = 0
    private lateinit var exercisesContainer: LinearLayout
    private lateinit var muscleNameTextView: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list)
        
        // الحصول على معرف العضلة من Intent
        muscleId = intent.getIntExtra("MUSCLE_ID", 1)
        
        // ربط عناصر الواجهة
        exercisesContainer = findViewById(R.id.exercises_container)
        muscleNameTextView = findViewById(R.id.tv_muscle_name)
        
        // إعداد الواجهة
        setupUI()
        
        // إعداد زر العودة
        findViewById<Button>(R.id.btn_back).setOnClickListener {
            finish() // العودة للشاشة السابقة
        }
    }
    
    /**
     * إعداد واجهة المستخدم وعرض التمارين
     */
    private fun setupUI() {
        // الحصول على بيانات العضلة
        val muscle = GymDataRepository.getMuscleById(muscleId)
        
        if (muscle != null) {
            // عرض اسم العضلة
            muscleNameTextView.text = getString(muscle.nameResourceId)
            
            // عرض التمارين
            displayExercises(muscle.exercises)
        }
    }
    
    /**
     * عرض قائمة التمارين في الواجهة
     * @param exercises قائمة التمارين
     */
    private fun displayExercises(exercises: List<Exercise>) {
        exercisesContainer.removeAllViews() // مسح المحتوى السابق
        
        for (exercise in exercises) {
            val exerciseButton = Button(this)
            
            // إعداد نص الزر
            exerciseButton.text = getString(exercise.nameResourceId)
            
            // إعداد تصميم الزر
            exerciseButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 8)
            }
            
            // تطبيق التصميم من الثيم
            exerciseButton.setBackgroundColor(getColor(R.color.exercise_button_color))
            exerciseButton.setTextColor(getColor(R.color.white))
            exerciseButton.textSize = 16f
            exerciseButton.setPadding(32, 24, 32, 24)
            
            // إعداد وظيفة النقر
            exerciseButton.setOnClickListener {
                openExerciseDetail(exercise.id)
            }
            
            // إضافة الزر للحاوية
            exercisesContainer.addView(exerciseButton)
        }
    }
    
    /**
     * فتح شاشة تفاصيل التمرين
     * @param exerciseId معرف التمرين
     */
    private fun openExerciseDetail(exerciseId: Int) {
        val intent = Intent(this, ExerciseDetailActivity::class.java)
        intent.putExtra("EXERCISE_ID", exerciseId)
        startActivity(intent)
    }
}