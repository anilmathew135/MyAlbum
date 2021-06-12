package com.example.myalbum.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myalbum.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun pushFragment(fragment: Fragment) {
        pushFragment(false, fragment)
    }

    fun pushFragment(addToHistory: Boolean, fragment: Fragment) {

        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_main, fragment, fragment.javaClass.name)
        if (addToHistory)
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        fragmentTransaction.commitAllowingStateLoss()
        this.supportFragmentManager.executePendingTransactions()

    }
}
