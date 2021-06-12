package com.example.myalbum.di.module

import com.example.myalbum.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment(): HomeFragment
}