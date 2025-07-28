package com.gymapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.gymapp.model.ExerciseRecord

/**
 * مساعد قاعدة البيانات SQLite
 * يدير إنشاء وتحديث قاعدة البيانات المحلية لحفظ بيانات التمارين
 */
class GymDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    
    companion object {
        private const val DATABASE_NAME = "gym_app.db"
        private const val DATABASE_VERSION = 1
        
        // اسم الجدول والأعمدة
        private const val TABLE_EXERCISE_RECORDS = "exercise_records"
        private const val COLUMN_ID = "id"
        private const val COLUMN_EXERCISE_ID = "exercise_id"
        private const val COLUMN_WEIGHT = "weight"
        private const val COLUMN_REPETITIONS = "repetitions"
        private const val COLUMN_TIMESTAMP = "timestamp"
        
        // استعلام إنشاء الجدول
        private const val CREATE_TABLE_SQL = """
            CREATE TABLE $TABLE_EXERCISE_RECORDS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EXERCISE_ID INTEGER NOT NULL,
                $COLUMN_WEIGHT REAL NOT NULL,
                $COLUMN_REPETITIONS INTEGER NOT NULL,
                $COLUMN_TIMESTAMP INTEGER NOT NULL
            )
        """
    }
    
    override fun onCreate(db: SQLiteDatabase) {
        // إنشاء الجدول عند إنشاء قاعدة البيانات لأول مرة
        db.execSQL(CREATE_TABLE_SQL)
    }
    
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // حذف الجدول القديم وإنشاء جدول جديد عند التحديث
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EXERCISE_RECORDS")
        onCreate(db)
    }
    
    /**
     * حفظ سجل تمرين جديد أو تحديث السجل الموجود
     */
    fun saveExerciseRecord(exerciseRecord: ExerciseRecord): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EXERCISE_ID, exerciseRecord.exerciseId)
            put(COLUMN_WEIGHT, exerciseRecord.weight)
            put(COLUMN_REPETITIONS, exerciseRecord.repetitions)
            put(COLUMN_TIMESTAMP, exerciseRecord.timestamp)
        }
        
        return try {
            // البحث عن سجل موجود لنفس التمرين
            val existingRecord = getLatestExerciseRecord(exerciseRecord.exerciseId)
            
            if (existingRecord != null) {
                // تحديث السجل الموجود
                val whereClause = "$COLUMN_EXERCISE_ID = ?"
                val whereArgs = arrayOf(exerciseRecord.exerciseId.toString())
                db.update(TABLE_EXERCISE_RECORDS, values, whereClause, whereArgs).toLong()
            } else {
                // إدراج سجل جديد
                db.insert(TABLE_EXERCISE_RECORDS, null, values)
            }
        } catch (e: Exception) {
            -1 // في حالة حدوث خطأ
        } finally {
            db.close()
        }
    }
    
    /**
     * الحصول على أحدث سجل لتمرين معين
     */
    fun getLatestExerciseRecord(exerciseId: Int): ExerciseRecord? {
        val db = this.readableDatabase
        var exerciseRecord: ExerciseRecord? = null
        
        val query = """
            SELECT * FROM $TABLE_EXERCISE_RECORDS 
            WHERE $COLUMN_EXERCISE_ID = ? 
            ORDER BY $COLUMN_TIMESTAMP DESC 
            LIMIT 1
        """
        
        try {
            val cursor = db.rawQuery(query, arrayOf(exerciseId.toString()))
            
            if (cursor.moveToFirst()) {
                exerciseRecord = ExerciseRecord(
                    id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    exerciseId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_ID)),
                    weight = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT)),
                    repetitions = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_REPETITIONS)),
                    timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP))
                )
            }
            cursor.close()
        } catch (e: Exception) {
            // في حالة حدوث خطأ، إرجاع null
        } finally {
            db.close()
        }
        
        return exerciseRecord
    }
    
    /**
     * الحصول على جميع سجلات تمرين معين مرتبة بالوقت
     */
    fun getAllRecordsForExercise(exerciseId: Int): List<ExerciseRecord> {
        val db = this.readableDatabase
        val records = mutableListOf<ExerciseRecord>()
        
        val query = """
            SELECT * FROM $TABLE_EXERCISE_RECORDS 
            WHERE $COLUMN_EXERCISE_ID = ? 
            ORDER BY $COLUMN_TIMESTAMP DESC
        """
        
        try {
            val cursor = db.rawQuery(query, arrayOf(exerciseId.toString()))
            
            while (cursor.moveToNext()) {
                val record = ExerciseRecord(
                    id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    exerciseId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_ID)),
                    weight = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT)),
                    repetitions = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_REPETITIONS)),
                    timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP))
                )
                records.add(record)
            }
            cursor.close()
        } catch (e: Exception) {
            // في حالة حدوث خطأ، إرجاع قائمة فارغة
        } finally {
            db.close()
        }
        
        return records
    }
}