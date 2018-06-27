package com.nicholasworkshop.moovuptest.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nicholasworkshop.moovuptest.R
import com.nicholasworkshop.moovuptest.fragment.DetailFragment
import com.nicholasworkshop.moovuptest.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerView, HomeFragment())
//                .replace(R.id.containerView, DetailFragment())
                .commit()
    }
}