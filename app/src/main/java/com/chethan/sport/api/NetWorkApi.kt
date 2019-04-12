package com.chethan.sport.api

/**
 * Created by Chethan on 4/3/2019.
 */

import androidx.lifecycle.LiveData
import com.chethan.sport.model.ItemList
import retrofit2.http.GET

/**
 * This interface contains the definition list of all the network endpoints used by the App.
 * Ref: Retrofit
 */
interface NetWorkApi {

    @GET("/_ah/api/myApi/v1/goals")
    fun getGoals(): LiveData<ApiResponse<ItemList>>

}