package com.example.myalbum.ui.home

import android.os.Bundle
import com.example.myalbum.ui.base.BaseActivity

/*
* Home Activity
* */
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pushFragment(HomeFragment.newInstance())
    }
}
