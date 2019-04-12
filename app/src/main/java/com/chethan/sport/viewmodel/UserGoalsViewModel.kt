package com.chethan.sport


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.chethan.sport.model.Item
import com.chethan.sport.repository.DataRepository
import com.chethan.sport.repository.Resource
import org.koin.standalone.KoinComponent


class UserGoalsViewModel(val dataRepository: DataRepository) : ViewModel(), KoinComponent {

    var listOfUserGoals = MutableLiveData<List<Item>>()

    init {
        listOfUserGoals.value = listOf()
    }

    val result: LiveData<Resource<List<Item>>> =  Transformations.switchMap(listOfUserGoals) { q ->
        dataRepository.getGoalList()
    }

}