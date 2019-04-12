package com.chethan.sport


import android.app.Application
import androidx.room.Room
import com.chethan.demoproject.utils.LiveDataCallAdapterFactory
import com.chethan.sport.api.NetWorkApi
import com.chethan.sport.db.AppDatabase
import com.chethan.sport.db.ItemsDao
import com.chethan.sport.repository.DataRepository
import com.chethan.sport.viewmodel.FitnessDataViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Koin : dependency injection framework for Kotlin. Written in pure Kotlin using functional
 * resolution only: no proxy, no code generation, no reflection!
 */

// Declaring a module
val mainModule = module {

    single { DataRepository(get(), get(), get()) }

    single { createWebService() }

    single { provideDb(get()) }

    single { AppExecutors() }

    single { provideItemsDao(get()) }

    viewModel { UserGoalsViewModel(get()) }

    viewModel { FitnessDataViewModel(get()) }

}


fun createWebService(): NetWorkApi {
    val retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl(API_REST_URL)
            .build()

    return retrofit.create(NetWorkApi::class.java)
}

fun provideDb(app: Application): AppDatabase {
    return Room.databaseBuilder(app, AppDatabase::class.java, "Sports.db")
        .fallbackToDestructiveMigration()
        .build()
}


fun provideItemsDao(db: AppDatabase): ItemsDao {
    return db.itemDao()
}
