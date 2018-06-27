package com.nicholasworkshop.moovuptest.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicholasworkshop.moovuptest.MainApplication
import com.nicholasworkshop.moovuptest.R
import com.nicholasworkshop.moovuptest.api.FriendService
import com.nicholasworkshop.moovuptest.databinding.ViewFriendBinding
import com.nicholasworkshop.moovuptest.model.Friend
import com.nicholasworkshop.moovuptest.model.FriendDao
import com.nicholasworkshop.moovuptest.model.FriendDatabase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject lateinit var friendService: FriendService
    @Inject lateinit var friendDatabase: FriendDatabase
    @Inject lateinit var friendDao: FriendDao
    @Inject lateinit var viewModel: HomeViewModel

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (activity!!.application as MainApplication).component.inject(this)
        val component = DaggerHomeComponent
                .builder()
                .mainComponent((activity!!.application as MainApplication).component)
                .build()
                .inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = Adapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        viewModel.friendDao.all().observe(this, Observer<List<Friend>> { friendList ->
            adapter.friendList = friendList
            adapter.notifyDataSetChanged()
        })
        Observable
                .just(friendDao)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    it.insertAll(Friend(
                            UUID.randomUUID().toString(),
                            "sadasdasd",
                            "sadasdasd",
                            "sadasdasd",
                            123.123,
                            2523.3))
                }
    }

    inner class Adapter : RecyclerView.Adapter<HomeViewHolder>() {

        var friendList: List<Friend>? = null

        override fun getItemCount(): Int {
            return if (friendList != null) friendList!!.size else 0
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
            val binding = ViewFriendBinding.inflate(layoutInflater, parent, false)
            return HomeViewHolder(binding)
        }

        override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
            holder.bind(friendList!![position])
        }
    }
}


class HomeViewHolder(
        private val binding: ViewFriendBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(friend: Friend) {
        binding.friend = friend
        binding.executePendingBindings()
    }
}

class HomeViewModel(
        val friendDao: FriendDao
) : ViewModel()

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
        val friendDao: FriendDao
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(friendDao) as T
    }
}