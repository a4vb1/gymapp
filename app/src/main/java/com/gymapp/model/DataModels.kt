package com.gymapp.model

/**
 * نموذج بيانات العضلة
 * يحتوي على معلومات العضلة الأساسية
 */
data class Muscle(
    val id: Int,
    val nameResourceId: Int, // معرف النص من strings.xml
    val exercises: List<Exercise> // قائمة التمارين لهذه العضلة
)

/**
 * نموذج بيانات التمرين  
 * يحتوي على جميع معلومات التمرين
 */
data class Exercise(
    val id: Int,
    val nameResourceId: Int, // معرف اسم التمرين من strings.xml
    val descriptionResourceId: Int, // معرف وصف التمرين من strings.xml
    val imageResourceId: Int, // معرف الصورة من drawable
    val muscleId: Int // معرف العضلة التي ينتمي إليها التمرين
)

/**
 * نموذج بيانات سجل التمرين
 * يحتوي على الوزن والتكرارات المحفوظة لكل تمرين
 */
data class ExerciseRecord(
    val id: Long = 0,
    val exerciseId: Int,
    val weight: Double, // الوزن بالكيلوغرام
    val repetitions: Int, // عدد التكرارات
    val timestamp: Long = System.currentTimeMillis() // وقت الحفظ
)