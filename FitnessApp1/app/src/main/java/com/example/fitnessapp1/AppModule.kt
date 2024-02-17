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
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single {
        SharedPreferencesInstance.apply {
            initSharedPreferences(androidContext())
        }
    }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    single {
        Gson()
    }
    single {
        val okHttpClient = get<OkHttpClient>()

        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single<AuthRepository> {
        get<Retrofit>().create(AuthRepository::class.java)
    }

    single<MealRepository> {
        get<Retrofit>().create(MealRepository::class.java)
    }

    single<MealStatRepository> {
        get<Retrofit>().create(MealStatRepository::class.java)
    }

    single<ActivityStatRepository> {
        get<Retrofit>().create(ActivityStatRepository::class.java)
    }

    single<ProfileRepository> {
        get<Retrofit>().create(ProfileRepository::class.java)
    }

    single<AuthService> { AuthServiceImpl(get(), get()) }
    single<MealService> { MealServiceImpl(get()) }
    single<MealStatService> { MealStatServiceImpl(get()) }
    single<ActivityStatService> { ActivityStatServiceImpl(get()) }
    single<ProfileService> { ProfileServiceImpl(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }

    factory { MainApplication() }
}