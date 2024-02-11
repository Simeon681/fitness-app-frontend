package com.example.fitnessapp1

import com.example.fitnessapp1.repository.ActivityStatRepository
import com.example.fitnessapp1.repository.AuthRepository
import com.example.fitnessapp1.repository.MealRepository
import com.example.fitnessapp1.repository.MealStatRepository
import com.example.fitnessapp1.repository.ProfileRepository
import com.example.fitnessapp1.service.ActivityStatService
import com.example.fitnessapp1.service.AuthService
import com.example.fitnessapp1.service.MealService
import com.example.fitnessapp1.service.MealStatService
import com.example.fitnessapp1.service.ProfileService
import com.example.fitnessapp1.service.serviceImpl.ActivityStatServiceImpl
import com.example.fitnessapp1.service.serviceImpl.AuthServiceImpl
import com.example.fitnessapp1.service.serviceImpl.MealServiceImpl
import com.example.fitnessapp1.service.serviceImpl.MealStatServiceImpl
import com.example.fitnessapp1.service.serviceImpl.ProfileServiceImpl
import com.example.fitnessapp1.view_model.LoginViewModel
import com.example.fitnessapp1.view_model.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        SharedPreferencesInstance.apply {
            initSharedPreferences(androidContext())
        }
    }

    single<AuthRepository> {
        RetrofitInstance.getRetrofitInstance()!!.create(AuthRepository::class.java)
    }

    single<MealRepository> {
        RetrofitInstance.getRetrofitInstance()!!.create(MealRepository::class.java)
    }

    single<MealStatRepository> {
        RetrofitInstance.getRetrofitInstance()!!.create(MealStatRepository::class.java)
    }

    single<ActivityStatRepository> {
        RetrofitInstance.getRetrofitInstance()!!.create(ActivityStatRepository::class.java)
    }

    single<ProfileRepository> {
        RetrofitInstance.getRetrofitInstance()!!.create(ProfileRepository::class.java)
    }


    single<AuthService> {
        AuthServiceImpl(get())
    }

    single<MealService> {
        MealServiceImpl(get())
    }

    single<MealStatService> {
        MealStatServiceImpl(get())
    }

    single<ActivityStatService> {
        ActivityStatServiceImpl(get())
    }

    single<ProfileService> {
        ProfileServiceImpl(get())
    }


    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        RegisterViewModel(get())
    }
}