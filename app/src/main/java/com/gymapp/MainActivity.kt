package com.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gymapp.ui.theme.GymAppTheme
import com.gymapp.ui.navigation.GymNavigation

/**
 * النشاط الرئيسي - الشاشة الأولى للتطبيق
 * تم تحويله لاستخدام Jetpack Compose
 */
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAppTheme {
                // سطح التطبيق، لون الخلفية من الثيم
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    GymNavigation(navController = navController)
                }
            }
        }
    }
}