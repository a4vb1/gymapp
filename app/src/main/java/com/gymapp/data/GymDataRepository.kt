package com.gymapp.data

import com.gymapp.R
import com.gymapp.model.Exercise
import com.gymapp.model.Muscle

/**
 * مستودع البيانات الثابتة للعضلات والتمارين
 * يحتوي على جميع العضلات والتمارين المحددة في المتطلبات
 */
object GymDataRepository {
    
    /**
     * قائمة جميع التمارين مرتبة حسب العضلة
     */
    private val exercises = listOf(
        // تمارين الظهر (Back)
        Exercise(1, R.string.deadlift, R.string.deadlift_desc, R.drawable.deadlift, 1),
        Exercise(2, R.string.pullups, R.string.pullups_desc, R.drawable.pullups, 1),
        Exercise(3, R.string.bent_over_row, R.string.general_exercise_desc, R.drawable.bent_over_row, 1),
        Exercise(4, R.string.lat_pulldown, R.string.general_exercise_desc, R.drawable.lat_pulldown, 1),
        Exercise(5, R.string.tbar_row, R.string.general_exercise_desc, R.drawable.tbar_row, 1),
        
        // تمارين الصدر (Chest)
        Exercise(6, R.string.bench_press, R.string.bench_press_desc, R.drawable.bench_press, 2),
        Exercise(7, R.string.incline_dumbbell_press, R.string.general_exercise_desc, R.drawable.incline_dumbbell_press, 2),
        Exercise(8, R.string.pushups, R.string.general_exercise_desc, R.drawable.pushups, 2),
        Exercise(9, R.string.chest_fly, R.string.general_exercise_desc, R.drawable.chest_fly, 2),
        Exercise(10, R.string.cable_crossover, R.string.general_exercise_desc, R.drawable.cable_crossover, 2),
        
        // تمارين الأرجل (Legs)
        Exercise(11, R.string.squat, R.string.squat_desc, R.drawable.squat, 3),
        Exercise(12, R.string.leg_press, R.string.general_exercise_desc, R.drawable.leg_press, 3),
        Exercise(13, R.string.lunges, R.string.general_exercise_desc, R.drawable.lunges, 3),
        Exercise(14, R.string.romanian_deadlift, R.string.general_exercise_desc, R.drawable.romanian_deadlift, 3),
        Exercise(15, R.string.leg_curl, R.string.general_exercise_desc, R.drawable.leg_curl, 3),
        
        // تمارين الأكتاف (Shoulders)
        Exercise(16, R.string.overhead_press, R.string.general_exercise_desc, R.drawable.overhead_press, 4),
        Exercise(17, R.string.lateral_raise, R.string.general_exercise_desc, R.drawable.lateral_raise, 4),
        Exercise(18, R.string.front_raise, R.string.general_exercise_desc, R.drawable.front_raise, 4),
        Exercise(19, R.string.arnold_press, R.string.general_exercise_desc, R.drawable.arnold_press, 4),
        Exercise(20, R.string.shrugs, R.string.general_exercise_desc, R.drawable.shrugs, 4),
        
        // تمارين البايسبس (Biceps)
        Exercise(21, R.string.barbell_curl, R.string.general_exercise_desc, R.drawable.barbell_curl, 5),
        Exercise(22, R.string.hammer_curl, R.string.general_exercise_desc, R.drawable.hammer_curl, 5),
        Exercise(23, R.string.concentration_curl, R.string.general_exercise_desc, R.drawable.concentration_curl, 5),
        Exercise(24, R.string.preacher_curl, R.string.general_exercise_desc, R.drawable.preacher_curl, 5),
        Exercise(25, R.string.cable_curl, R.string.general_exercise_desc, R.drawable.cable_curl, 5),
        
        // تمارين الترايسبس (Triceps)
        Exercise(26, R.string.triceps_pushdown, R.string.general_exercise_desc, R.drawable.triceps_pushdown, 6),
        Exercise(27, R.string.skull_crushers, R.string.general_exercise_desc, R.drawable.skull_crushers, 6),
        Exercise(28, R.string.dips, R.string.general_exercise_desc, R.drawable.dips, 6),
        Exercise(29, R.string.overhead_extension, R.string.general_exercise_desc, R.drawable.overhead_extension, 6),
        Exercise(30, R.string.close_grip_bench, R.string.general_exercise_desc, R.drawable.close_grip_bench, 6),
        
        // تمارين السواعد (Forearms)
        Exercise(31, R.string.wrist_curl, R.string.general_exercise_desc, R.drawable.wrist_curl, 7),
        Exercise(32, R.string.reverse_curl, R.string.general_exercise_desc, R.drawable.reverse_curl, 7),
        Exercise(33, R.string.farmers_carry, R.string.general_exercise_desc, R.drawable.farmers_carry, 7),
        Exercise(34, R.string.zottman_curl, R.string.general_exercise_desc, R.drawable.zottman_curl, 7),
        Exercise(35, R.string.plate_pinch, R.string.general_exercise_desc, R.drawable.plate_pinch, 7)
    )
    
    /**
     * قائمة جميع العضلات مع التمارين المرتبطة بها
     */
    val muscles = listOf(
        Muscle(1, R.string.muscle_back, exercises.filter { it.muscleId == 1 }),
        Muscle(2, R.string.muscle_chest, exercises.filter { it.muscleId == 2 }),
        Muscle(3, R.string.muscle_legs, exercises.filter { it.muscleId == 3 }),
        Muscle(4, R.string.muscle_shoulders, exercises.filter { it.muscleId == 4 }),
        Muscle(5, R.string.muscle_biceps, exercises.filter { it.muscleId == 5 }),
        Muscle(6, R.string.muscle_triceps, exercises.filter { it.muscleId == 6 }),
        Muscle(7, R.string.muscle_forearms, exercises.filter { it.muscleId == 7 })
    )
    
    /**
     * الحصول على عضلة معينة بواسطة المعرف
     */
    fun getMuscleById(muscleId: Int): Muscle? {
        return muscles.find { it.id == muscleId }
    }
    
    /**
     * الحصول على تمرين معين بواسطة المعرف
     */
    fun getExerciseById(exerciseId: Int): Exercise? {
        return exercises.find { it.id == exerciseId }
    }
    
    /**
     * الحصول على جميع التمارين لعضلة معينة
     */
    fun getExercisesForMuscle(muscleId: Int): List<Exercise> {
        return exercises.filter { it.muscleId == muscleId }
    }
}