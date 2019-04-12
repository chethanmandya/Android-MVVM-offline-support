package com.chethan.sport.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.tasks.OnSuccessListener
import org.koin.standalone.KoinComponent


/**
 * Created by Chethan on 4/6/2019.
 */
class FitnessDataViewModel(context: Context) : ViewModel(), KoinComponent {
    private val TAG = "FitnessDataViewModel";
    val numberOfSteps = MutableLiveData<Long>()
    val caloriesBurn = MutableLiveData<String>()
    val distanceCovered = MutableLiveData<String>()

    init {
        numberOfSteps.value = 0
        caloriesBurn.value = ""
        distanceCovered.value = ""
        getStepsCount(context)
        getCaloriesBurnCount(context)
        getKmCovered(context)
    }

    /**
     * Following code reads the current daily step total, computed from midnight of the current
     * day on the device's current timezone.
     */

    private fun getStepsCount(context: Context) {
        Fitness.getHistoryClient(context, GoogleSignIn.getLastSignedInAccount(context))
            .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
            .addOnSuccessListener(
                object : OnSuccessListener<DataSet> {
                    override fun onSuccess(dataSet: DataSet) {
                        val total = (if (dataSet.isEmpty)
                            0
                        else
                            dataSet.dataPoints[0].getValue(Field.FIELD_STEPS).asInt()).toLong()

                        numberOfSteps.value = total
                    }
                })
    }


    // Calories Burn
    private fun getCaloriesBurnCount(context: Context) {
        Fitness.getHistoryClient(context, GoogleSignIn.getLastSignedInAccount(context))
            .readDailyTotal(DataType.TYPE_CALORIES_EXPENDED)
            .addOnSuccessListener(
                object : OnSuccessListener<DataSet> {
                    override fun onSuccess(dataSet: DataSet) {
                        val value = dataSet.dataPoints[0].getValue(Field.FIELD_CALORIES).toString()
                        caloriesBurn.value = value
                    }
                })
    }


    // Km Covered
    private fun getKmCovered(context: Context) {
        Fitness.getHistoryClient(context, GoogleSignIn.getLastSignedInAccount(context))
            .readDailyTotal(DataType.TYPE_DISTANCE_DELTA)
            .addOnSuccessListener(
                object : OnSuccessListener<DataSet> {
                    override fun onSuccess(dataSet: DataSet) {
                        val value = dataSet.dataPoints[0].getValue(Field.FIELD_DISTANCE).toString()

                        distanceCovered.value = value
                    }
                })



    }

}