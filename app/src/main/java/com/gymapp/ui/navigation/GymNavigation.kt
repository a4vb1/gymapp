package com.gymapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gymapp.ui.screens.ExerciseDetailScreen
import com.gymapp.ui.screens.ExerciseListScreen
import com.gymapp.ui.screens.MainScreen

/**
 * نظام التنقل الرئيسي للتطبيق باستخدام Navigation Compose
 */
@Composable
fun GymNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        // الشاشة الرئيسية - قائمة العضلات
        composable("main") {
            MainScreen(
                onMuscleClick = { muscleId ->
                    navController.navigate("exercise_list/$muscleId")
                }
            )
        }
        
        // شاشة قائمة التمارين
        composable(
            "exercise_list/{muscleId}",
            arguments = listOf(navArgument("muscleId") { type = NavType.IntType })
        ) { backStackEntry ->
            val muscleId = backStackEntry.arguments?.getInt("muscleId") ?: 1
            ExerciseListScreen(
                muscleId = muscleId,
                onExerciseClick = { exerciseId ->
                    navController.navigate("exercise_detail/$exerciseId")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        // شاشة تفاصيل التمرين
        composable(
            "exercise_detail/{exerciseId}",
            arguments = listOf(navArgument("exerciseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getInt("exerciseId") ?: 1
            ExerciseDetailScreen(
                exerciseId = exerciseId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}