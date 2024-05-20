package com.example.fitnessapp1

import com.example.fitnessapp1.API.ActivityStatAPI
import com.example.fitnessapp1.API.AuthAPI
import com.example.fitnessapp1.API.HeightChangeAPI
import com.example.fitnessapp1.API.MealAPI
import com.example.fitnessapp1.API.MealStatAPI
import com.example.fitnessapp1.API.ProfileAPI
import com.example.fitnessapp1.API.WeightChangeAPI
import com.example.fitnessapp1.RetrofitHost.TUES
import com.example.fitnessapp1.service.ActivityStatService
import com.example.fitnessapp1.service.AuthService
import com.example.fitnessapp1.service.HeightChangeService
import com.example.fitnessapp1.service.MealService
import com.example.fitnessapp1.service.MealStatService
import com.example.fitnessapp1.service.ProfileService
import com.example.fitnessapp1.service.WeightChangesService
import com.example.fitnessapp1.service.service_impl.ActivityStatServiceImpl
import com.example.fitnessapp1.service.service_impl.AuthServiceImpl
import com.example.fitnessapp1.service.service_impl.HeightChangeServiceImpl
import com.example.fitnessapp1.service.service_impl.MealServiceImpl
import com.example.fitnessapp1.service.service_impl.MealStatServiceImpl
import com.example.fitnessapp1.service.service_impl.ProfileServiceImpl
import com.example.fitnessapp1.service.service_impl.WeightChangesServiceImpl
import com.example.fitnessapp1.view_model.ActivityStatViewModel
import com.example.fitnessapp1.view_model.HeightChangeViewModel
import com.example.fitnessapp1.view_model.LoginViewModel
import com.example.fitnessapp1.view_model.MealStatViewModel
import com.example.fitnessapp1.view_model.MealViewModel
import com.example.fitnessapp1.view_model.ProfileViewModel
import com.example.fitnessapp1.view_model.RegisterViewModel
import com.example.fitnessapp1.view_model.WeightChangeViewModel
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
            .addInterceptor(TokenInterceptor(get()))
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
            .baseUrl(TUES)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single<AuthAPI> {
        get<Retrofit>().create(AuthAPI::class.java)
    }

    single<MealAPI> {
        get<Retrofit>().create(MealAPI::class.java)
    }

    single<MealStatAPI> {
        get<Retrofit>().create(MealStatAPI::class.java)
    }

    single<ActivityStatAPI> {
        get<Retrofit>().create(ActivityStatAPI::class.java)
    }

    single<ProfileAPI> {
        get<Retrofit>().create(ProfileAPI::class.java)
    }

    single<HeightChangeAPI> {
        get<Retrofit>().create(HeightChangeAPI::class.java)
    }

    single<WeightChangeAPI> {
        get<Retrofit>().create(WeightChangeAPI::class.java)
    }

    single<AuthService> { AuthServiceImpl(get(), get()) }
    single<MealService> { MealServiceImpl(get()) }
    single<MealStatService> { MealStatServiceImpl(get()) }
    single<ActivityStatService> { ActivityStatServiceImpl(get()) }
    single<ProfileService> { ProfileServiceImpl(get()) }
    single<HeightChangeService> { HeightChangeServiceImpl(get()) }
    single<WeightChangesService> { WeightChangesServiceImpl(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { MealViewModel(get()) }
    viewModel { MealStatViewModel(get()) }
    viewModel { ActivityStatViewModel(get()) }
    viewModel { HeightChangeViewModel(get()) }
    viewModel { WeightChangeViewModel(get()) }

    factory { MainApplication() }
}