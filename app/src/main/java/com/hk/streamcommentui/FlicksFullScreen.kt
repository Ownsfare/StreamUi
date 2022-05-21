package com.hk.streamcommentui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2



class FlicksFullScreen : AppCompatActivity() {

    private var viewPager2: ViewPager2? = null
    private var videoList: MutableList<FlicksModelClass>? = null
    private var adapter: FullScreenFlicksAdapter? = null
    var videoView: VideoView? = null
    var seekBar:SeekBar?=null
//    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flicks_fullscreen)

        var pos = intent.getIntExtra("position",0)

        seekBar=findViewById(R.id.seekBar)
        videoView=findViewById(R.id.videoView)


        videoList = ArrayList()
        viewPager2 = findViewById(R.id.viewPager2)


        supportActionBar?.hide()
//
//        val url:String= intent.getStringExtra("videoURL").toString()
//        val videotitle=intent.getStringExtra("title").toString()
//        val videodesc=intent.getStringExtra("desc").toString()



//        videoList?.add(
//            FlicksModelClass(url,
//                videotitle, videodesc
//            )
//        )

//        readFirestoreData()

        videoList?.add(FlicksModelClass("android.resource://" + getPackageName() + "/" + R.raw.one,"abc","abc"))
        videoList?.add(FlicksModelClass("android.resource://" + getPackageName() + "/" + R.raw.two,"abc","abc"))
        videoList?.add(FlicksModelClass("android.resource://" + getPackageName() + "/" + R.raw.three,"abc","abc"))


        adapter = FullScreenFlicksAdapter(videoList as ArrayList<FlicksModelClass>,this,pos){
            val intent = Intent(this,MainFlickScreen::class.java)
//            intent.putExtra("posBack",it)
//            intent.putExtra("temp",1)  //
            startActivity(intent)
        }
        viewPager2?.setAdapter(adapter)



    }

//    private fun readFirestoreData() {
//        val db = FirebaseFirestore.getInstance()
//
//        Log.i("hi", "hi")
//
//        GlobalScope.launch(Dispatchers.IO) {
//
//            db.collectionGroup("Flick_Video")
//
//                .get()
//                .addOnCompleteListener {
//                    Log.i("hi", "hi2")
//
//                    if (it.isSuccessful) {
//                        val document: QuerySnapshot = it.result
//                        Log.i("hi", "hi3")
//
//                        document.forEach { data ->
//
//                            Log.i("hi", "hi4")
//                            val flicksPost = FlickPost(
//                                data["_post_id_"] as String,
//                                data["_audio_"] as List<String>?,
//                                data["_title_"] as String,
//                                data["_desc_"] as String,
//                                data["_imageUrl_"] as List<String>?,
//                                data["_video_"] as String,
//                                data["_owner_"] as List<FLickOwner>?,
//                                data["_postType_"] as String,
//                                data["_timestamp_"] as String,
//                                data["_shares_"] as Long,
//                                data["_likes_"] as Long,
//                                data["_comment_"] as Long,
//                                data["_on_market_"] as Boolean,
//                                data["_share_allowed_"] as Boolean,
//                                data["_comment_allowed_"] as Boolean,
//                                data["_where_"] as FlickWherePost?,
//                                data["_creatorUid_"] as String,
//                                data["_location_"] as FlickLocation?,
//                                data["_creatorUsername_"] as String,
//                                data["_creatorProfilePicture_"] as String,
//                                data["_ownerUsername_"] as String,
//                                data["_ownerProfilePicture_"] as String
//
//                            )
//                            Log.i("info", flicksPost.toString())
//
//                            val url = flicksPost.videoUrl
//                            val title = flicksPost.title
//                            val desc = flicksPost.desc
//
//                            Log.i("firebaseurl", url.toString())
//
//                            videoList?.add(
//                                FlicksModelClass(url!!, title, desc)
//                            )
//                            adapter = FullScreenFlicksAdapter(videoList as ArrayList<FlicksModelClass>)
//                            viewPager2?.setAdapter(adapter)
//                        }
//                    }
//                }
//                .addOnFailureListener {
//                    applicationContext.displayToast(it.message)
//                }
//                .await()
//
//        }
//    }

}


