package com.hk.streamcommentui

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainFlickScreen : AppCompatActivity() {
    private var viewPager1: ViewPager2? = null
    private var videoList: MutableList<FlicksModelClass>? = null
    private var adapter: MainScreenFlicksAdapter? = null
    var videoView: VideoView? = null
    var seekBar: SeekBar?=null
//    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flick_mainscreen)

//        val posBack = intent.getIntExtra("posBack",0)
//        val temp = intent.getIntExtra("temp",0)   //

        seekBar = findViewById(R.id.seekBar)
        videoView = findViewById(R.id.videoView)


        videoList = ArrayList()
        viewPager1 = findViewById(R.id.viewPager1)

        videoList?.add(FlicksModelClass("android.resource://" + getPackageName() + "/" + R.raw.one,"abc","abc"))
        videoList?.add(FlicksModelClass("android.resource://" + getPackageName() + "/" + R.raw.two,"abc","abc"))
        videoList?.add(FlicksModelClass("android.resource://" + getPackageName() + "/" + R.raw.three,"abc","abc"))


//        adapter = MainScreenFlicksAdapter(videoList as ArrayList<FlicksModelClass>,this){ position ->
//            val intent = Intent(this,FlicksFullScreen::class.java)
//            intent.putExtra("position",position)
//            startActivity(intent)
//        }
        adapter = MainScreenFlicksAdapter(videoList as ArrayList<FlicksModelClass>,this,{supportActionBar?.hide()},{ supportActionBar ?.show() })


        viewPager1?.setAdapter(adapter)


    }
}