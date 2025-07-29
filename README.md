# تطبيق الجيم - Gym App

تطبيق أندرويد حديث يعرض أشهر التمارين لكل عضلة رئيسية في الجسم، مطور بـ **Jetpack Compose** بلغة Kotlin مع قاعدة بيانات SQLite.

## المميزات الجديدة ✨

- **🎨 واجهة حديثة**: تم تطوير التطبيق بـ Jetpack Compose و Material Design 3
- **🔄 تصميم متجاوب**: يتكيف مع جميع أحجام الشاشات
- **🌙 الوضع المظلم**: دعم تلقائي للوضع المظلم
- **⚡ أداء محسن**: إعادة رسم ذكية مع Compose
- **🧭 ملاحة سلسة**: Navigation Compose مع type-safe arguments

## المميزات

- **7 عضلات رئيسية**: الظهر، الصدر، الأرجل، الأكتاف، البايسبس، الترايسبس، السواعد
- **35 تمرين**: 5 تمارين لكل عضلة
- **حفظ البيانات**: إمكانية حفظ الوزن وعدد التكرارات لكل تمرين
- **واجهة سهلة**: تنقل بسيط بين الشاشات مع Jetpack Compose
- **تعليقات عربية**: الكود يحتوي على تعليقات باللغة العربية
- **دعم العربية**: واجهة كاملة باللغة العربية مع دعم RTL

## العضلات والتمارين

### 💪 الظهر (Back)
- Deadlift
- Pull-ups  
- Bent-over Row
- Lat Pulldown
- T-Bar Row

### 🏋️ الصدر (Chest)
- Bench Press
- Incline Dumbbell Press
- Push-ups
- Chest Fly
- Cable Crossover

### 🦵 الأرجل (Legs)
- Squat
- Leg Press
- Lunges
- Romanian Deadlift
- Leg Curl

### 🔺 الأكتاف (Shoulders)
- Overhead Press
- Lateral Raise
- Front Raise
- Arnold Press
- Shrugs

### 💪 البايسبس (Biceps)
- Barbell Curl
- Hammer Curl
- Concentration Curl
- Preacher Curl
- Cable Curl

### 💪 الترايسبس (Triceps)
- Triceps Pushdown
- Skull Crushers
- Dips
- Overhead Extension
- Close-grip Bench

### 🤏 السواعد (Forearms)
- Wrist Curl
- Reverse Curl
- Farmer's Carry
- Zottman Curl
- Plate Pinch

## التقنيات المستخدمة

- **Kotlin**: لغة البرمجة الأساسية
- **Android SDK**: الحد الأدنى 21، الهدف 33
- **SQLite**: قاعدة البيانات المحلية
- **Material Design**: تصميم واجهة المستخدم
- **Gradle 7.6.4**: أداة البناء

## كيفية التشغيل

1. افتح Android Studio
2. اختر "Open an Existing Project"
3. اختر مجلد المشروع
4. انتظر حتى يتم تحميل التبعيات
5. اضغط "Run" أو Shift+F10

## هيكل المشروع

```
app/
├── src/main/java/com/gymapp/
│   ├── MainActivity.kt              # الشاشة الرئيسية
│   ├── ExerciseListActivity.kt      # شاشة قائمة التمارين
│   ├── ExerciseDetailActivity.kt    # شاشة تفاصيل التمرين
│   ├── model/
│   │   └── DataModels.kt           # نماذج البيانات
│   ├── data/
│   │   └── GymDataRepository.kt    # مستودع البيانات
│   └── database/
│       └── GymDatabaseHelper.kt    # مساعد قاعدة البيانات
├── src/main/res/
│   ├── layout/                     # ملفات التخطيط
│   ├── values/                     # القيم والنصوص
│   └── drawable/                   # الصور والرسوم
└── src/main/AndroidManifest.xml    # ملف الإعداد الرئيسي
```

## الاستخدام

1. **الشاشة الرئيسية**: اختر العضلة المراد تمرينها
2. **شاشة التمارين**: اختر التمرين المطلوب
3. **شاشة التفاصيل**: 
   - اطلع على وصف التمرين
   - أدخل الوزن المستخدم
   - أدخل عدد التكرارات
   - اضغط "حفظ" لحفظ البيانات

## ملاحظات

- يمكن إضافة صور حقيقية للتمارين في مجلد `res/drawable`
- البيانات تُحفظ محلياً على الجهاز
- التطبيق يدعم اللغة العربية في الواجهة

---

## Gym App

An Android application that displays popular exercises for each major muscle group, developed in Kotlin with SQLite database.

## How to Run

1. Open Android Studio
2. Choose "Open an Existing Project"  
3. Select the project folder
4. Wait for dependencies to load
5. Press "Run" or Shift+F10

## Technical Requirements

- Android Studio
- Gradle 7.6.4
- Min SDK 21
- Kotlin 1.8.10