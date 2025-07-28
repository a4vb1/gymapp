# دليل التشغيل - Running Guide

## خطوات فتح المشروع في Android Studio

### الطريقة الأولى: فتح المشروع مباشرة
1. افتح Android Studio
2. اختر "Open an Existing Project"
3. اذهب لمجلد المشروع واختره
4. انتظر حتى يتم تحميل المشروع
5. إذا طُلب منك تحديث Gradle، اختر "Don't remind me again"

### الطريقة الثانية: استيراد المشروع
1. افتح Android Studio  
2. اختر "Import Project"
3. اختر مجلد المشروع
4. اتبع التعليمات لإعداد المشروع

## حل المشاكل المحتملة

### إذا ظهرت مشكلة في Gradle:
```
File > Settings > Build > Build Tools > Gradle
- استخدم Gradle wrapper
```

### إذا لم تظهر الأيقونات:
```
Build > Clean Project
Build > Rebuild Project
```

### إذا ظهرت مشاكل في SDK:
```
File > Project Structure > SDK Location
تأكد من أن Android SDK محدد بشكل صحيح
```

## التشغيل

### على محاكي:
1. اذهب لـ AVD Manager
2. أنشئ جهاز افتراضي جديد (API 21 أو أحدث)
3. اضغط Run (Shift+F10)

### على جهاز حقيقي:
1. فعّل "Developer Options" في الجهاز
2. فعّل "USB Debugging"  
3. وصّل الجهاز بالكمبيوتر
4. اضغط Run (Shift+F10)

## هيكل التطبيق

```
MainActivity (الشاشة الرئيسية)
    ↓ اختيار عضلة
ExerciseListActivity (قائمة التمارين)
    ↓ اختيار تمرين
ExerciseDetailActivity (تفاصيل التمرين)
    ↓ حفظ البيانات
SQLite Database (قاعدة البيانات)
```

## الملفات المهمة

- `MainActivity.kt` - الشاشة الرئيسية مع أزرار العضلات
- `ExerciseListActivity.kt` - عرض تمارين العضلة المختارة  
- `ExerciseDetailActivity.kt` - تفاصيل التمرين مع حفظ البيانات
- `GymDataRepository.kt` - بيانات العضلات والتمارين
- `GymDatabaseHelper.kt` - إدارة قاعدة البيانات SQLite

## إضافة صور حقيقية للتمارين

1. اذهب للمجلد: `app/src/main/res/drawable/`
2. استبدل الملفات الموجودة بصور التمارين الحقيقية
3. حافظ على نفس أسماء الملفات (مثل: `deadlift.png`, `squat.jpg`)

## الإعدادات المطلوبة

- **Minimum SDK**: 21 (Android 5.0)
- **Target SDK**: 33 (Android 13)
- **Gradle**: 7.6.4
- **Kotlin**: 1.8.10

---

## English Instructions

### Opening the Project
1. Open Android Studio
2. Choose "Open an Existing Project"
3. Navigate to and select the project folder
4. Wait for project to load
5. If prompted to update Gradle, choose "Don't remind me again"

### Running the App
1. Create a new AVD (API 21+) or connect a real device
2. Press Run (Shift+F10)
3. The app should build and launch

### Key Files
- MainActivity.kt - Main screen with muscle group buttons
- ExerciseListActivity.kt - Shows exercises for selected muscle
- ExerciseDetailActivity.kt - Exercise details with save functionality
- GymDataRepository.kt - Data source for muscles and exercises
- GymDatabaseHelper.kt - SQLite database management