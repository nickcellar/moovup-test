package com.nicholasworkshop.moovuptest.fragment

import com.nicholasworkshop.moovuptest.MainComponent
import dagger.Component

@HomeScope
@Component(
        dependencies = arrayOf(MainComponent::class),
        modules = arrayOf(HomeModule::class))
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)
}

