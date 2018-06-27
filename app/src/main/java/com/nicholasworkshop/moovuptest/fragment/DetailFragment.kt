package com.nicholasworkshop.moovuptest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nicholasworkshop.moovuptest.MainApplication
import com.nicholasworkshop.moovuptest.R
import com.nicholasworkshop.moovuptest.databinding.ViewFriendBinding
import com.nicholasworkshop.moovuptest.model.FriendDao
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : Fragment() {

    companion object {

        private const val ARG_ID = "ARG_ID"

        fun newInstance(friendId: String): DetailFragment {
            val args = Bundle()
            args.putSerializable(ARG_ID, friendId)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var friendDao: FriendDao

    private val subject: BehaviorSubject<LatLng> = BehaviorSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity!!.application as MainApplication).component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments!!.getString(ARG_ID)
        val binding = ViewFriendBinding.inflate(layoutInflater, infoView, true)

        just(friendDao)
                .subscribeOn(io())
                .map { it.findById(id) }
                .observeOn(mainThread())
                .subscribe {
                    binding.friend = it
                    binding.executePendingBindings()
                    subject.onNext(LatLng(it.latitude!!, it.longitude!!))
                }

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { map ->
            subject.subscribe {
                map.addMarker(MarkerOptions().position(it).title("Hi"))
                map.moveCamera(CameraUpdateFactory.newLatLng(it))
                map.resetMinMaxZoomPreference()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mapView != null) {
            mapView.onDestroy()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}