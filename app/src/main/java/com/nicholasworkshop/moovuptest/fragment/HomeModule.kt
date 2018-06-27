package com.nicholasworkshop.moovuptest.fragment

import android.arch.lifecycle.ViewModelProviders
import com.nicholasworkshop.moovuptest.model.FriendDao
import dagger.Module
import dagger.Provides

@Module
class HomeModule(val fragment: HomeFragment) {

    @HomeScope
    @Provides
    fun homeViewModelFactory(friendDao: FriendDao): HomeViewModelFactory {
        return HomeViewModelFactory(friendDao)
    }

    @HomeScope
    @Provides
    fun homeViewModel(factory: HomeViewModelFactory): HomeViewModel {
        return ViewModelProviders.of(fragment, factory).get(HomeViewModel::class.java)
    }
}