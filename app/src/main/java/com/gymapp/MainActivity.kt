package com.gymapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gymapp.data.GymDataRepository

/**
 * النشاط الرئيسي - الشاشة الأولى للتطبيق
 * يعرض جميع العضلات كأزرار للاختيار منها
 */
class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // ربط أزرار العضلات بالوظائف
        setupMuscleButtons()
    }
    
    /**
     * إعداد أزرار العضلات وربطها بوظائف النقر
     */
    private fun setupMuscleButtons() {
        // زر عضلة الظهر
        findViewById<Button>(R.id.btn_back_muscle).setOnClickListener {
            openExerciseList(1) // معرف عضلة الظهر
        }
        
        // زر عضلة الصدر
        findViewById<Button>(R.id.btn_chest_muscle).setOnClickListener {
            openExerciseList(2) // معرف عضلة الصدر
        }
        
        // زر عضلة الأرجل
        findViewById<Button>(R.id.btn_legs_muscle).setOnClickListener {
            openExerciseList(3) // معرف عضلة الأرجل
        }
        
        // زر عضلة الأكتاف
        findViewById<Button>(R.id.btn_shoulders_muscle).setOnClickListener {
            openExerciseList(4) // معرف عضلة الأكتاف
        }
        
        // زر عضلة البايسبس
        findViewById<Button>(R.id.btn_biceps_muscle).setOnClickListener {
            openExerciseList(5) // معرف عضلة البايسبس
        }
        
        // زر عضلة الترايسبس
        findViewById<Button>(R.id.btn_triceps_muscle).setOnClickListener {
            openExerciseList(6) // معرف عضلة الترايسبس
        }
        
        // زر عضلة السواعد
        findViewById<Button>(R.id.btn_forearms_muscle).setOnClickListener {
            openExerciseList(7) // معرف عضلة السواعد
        }
    }
    
    /**
     * فتح شاشة قائمة التمارين لعضلة معينة
     * @param muscleId معرف العضلة
     */
    private fun openExerciseList(muscleId: Int) {
        val intent = Intent(this, ExerciseListActivity::class.java)
        intent.putExtra("MUSCLE_ID", muscleId)
        startActivity(intent)
    }
}