package com.example.myalbum.di.module

import com.example.myalbum.source.Repository
import com.example.myalbum.source.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        FragmentBuilderModule::class
    ]
)
abstract class AppModule {

    @Binds
    @Singleton
    internal abstract fun provideRepository(repository: RepositoryImpl): Repository
}