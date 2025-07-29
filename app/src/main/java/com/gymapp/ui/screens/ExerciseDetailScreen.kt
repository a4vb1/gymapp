package com.gymapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gymapp.R
import com.gymapp.data.GymDataRepository
import com.gymapp.database.GymDatabaseHelper
import com.gymapp.model.ExerciseRecord
import kotlinx.coroutines.launch

/**
 * شاشة تفاصيل التمرين
 * تحولت من Activity إلى Composable
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(
    exerciseId: Int,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val exercise = GymDataRepository.getExerciseById(exerciseId)
    val databaseHelper = remember { GymDatabaseHelper(context) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    
    // حالات الإدخال
    var weight by remember { mutableStateOf("") }
    var repetitions by remember { mutableStateOf("") }
    var weightError by remember { mutableStateOf<String?>(null) }
    var repetitionsError by remember { mutableStateOf<String?>(null) }
    
    // تحميل البيانات المحفوظة
    LaunchedEffect(exerciseId) {
        val savedRecord = databaseHelper.getLatestExerciseRecord(exerciseId)
        savedRecord?.let {
            weight = it.weight.toString()
            repetitions = it.repetitions.toString()
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = exercise?.let { stringResource(it.nameResourceId) } ?: "تفاصيل التمرين",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "العودة"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        exercise?.let { currentExercise ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // بطاقة معلومات التمرين
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // صورة التمرين
                        Image(
                            painter = painterResource(currentExercise.imageResourceId),
                            contentDescription = stringResource(currentExercise.nameResourceId),
                            modifier = Modifier
                                .size(200.dp)
                                .padding(bottom = 16.dp)
                        )
                        
                        // اسم التمرين
                        Text(
                            text = stringResource(currentExercise.nameResourceId),
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        
                        // وصف التمرين
                        Text(
                            text = stringResource(currentExercise.descriptionResourceId),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                
                // قسم إدخال البيانات
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "بيانات التمرين",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        
                        // إدخال الوزن
                        OutlinedTextField(
                            value = weight,
                            onValueChange = { 
                                weight = it
                                weightError = null
                            },
                            label = { Text(stringResource(R.string.weight_label)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            isError = weightError != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        
                        weightError?.let { error ->
                            Text(
                                text = error,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // إدخال التكرارات
                        OutlinedTextField(
                            value = repetitions,
                            onValueChange = { 
                                repetitions = it
                                repetitionsError = null
                            },
                            label = { Text(stringResource(R.string.reps_label)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            isError = repetitionsError != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        
                        repetitionsError?.let { error ->
                            Text(
                                text = error,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // زر الحفظ
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    // التحقق من صحة البيانات
                                    var hasError = false
                                    
                                    if (weight.trim().isEmpty()) {
                                        weightError = "يرجى إدخال الوزن"
                                        hasError = true
                                    } else {
                                        try {
                                            val weightValue = weight.trim().toDouble()
                                            if (weightValue <= 0) {
                                                weightError = "يجب أن يكون الوزن أكبر من صفر"
                                                hasError = true
                                            }
                                        } catch (e: NumberFormatException) {
                                            weightError = "يرجى إدخال رقم صحيح"
                                            hasError = true
                                        }
                                    }
                                    
                                    if (repetitions.trim().isEmpty()) {
                                        repetitionsError = "يرجى إدخال عدد التكرارات"
                                        hasError = true
                                    } else {
                                        try {
                                            val repsValue = repetitions.trim().toInt()
                                            if (repsValue <= 0) {
                                                repetitionsError = "يجب أن يكون عدد التكرارات أكبر من صفر"
                                                hasError = true
                                            }
                                        } catch (e: NumberFormatException) {
                                            repetitionsError = "يرجى إدخال رقم صحيح"
                                            hasError = true
                                        }
                                    }
                                    
                                    if (!hasError) {
                                        // حفظ البيانات
                                        val exerciseRecord = ExerciseRecord(
                                            exerciseId = exerciseId,
                                            weight = weight.trim().toDouble(),
                                            repetitions = repetitions.trim().toInt()
                                        )
                                        
                                        val result = databaseHelper.saveExerciseRecord(exerciseRecord)
                                        
                                        coroutineScope.launch {
                                            if (result > 0) {
                                                snackbarHostState.showSnackbar("تم حفظ البيانات بنجاح")
                                            } else {
                                                snackbarHostState.showSnackbar("حدث خطأ أثناء حفظ البيانات")
                                            }
                                        }
                                    }
                                },
                                modifier = Modifier.width(120.dp)
                            ) {
                                Text(stringResource(R.string.save_button))
                            }
                        }
                    }
                }
            }
        }
    }
}