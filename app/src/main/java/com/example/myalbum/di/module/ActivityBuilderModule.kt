package com.example.myalbum.di.module

import com.example.myalbum.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun bindHomeActivity(): HomeActivity
}