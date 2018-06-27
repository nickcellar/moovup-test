package com.nicholasworkshop.moovuptest

import com.nicholasworkshop.moovuptest.api.ApiModule
import com.nicholasworkshop.moovuptest.fragment.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        MainModule::class,
        ApiModule::class))
interface MainComponent {

    fun inject(homeFragment: HomeFragment)
}