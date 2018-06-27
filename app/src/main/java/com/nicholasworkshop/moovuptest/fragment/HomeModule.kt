package com.nicholasworkshop.moovuptest.fragment

import android.arch.lifecycle.ViewModelProviders
import com.nicholasworkshop.moovuptest.model.FriendDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeModule(val fragment: HomeFragment) {
//
//    @Singleton
//    @Provides
//    fun homeViewModelFactory(friendDao: FriendDao): HomeViewModelFactory {
//        return HomeViewModelFactory(friendDao)
//    }
//
//    @Singleton
//    @Provides
//    fun homeViewModel(factory: HomeViewModelFactory): HomeViewModel {
//        return ViewModelProviders.of(fragment, factory).get(HomeViewModel::class.java)
//    }
}