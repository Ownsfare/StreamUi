package com.hk.streamcommentui

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.coordinatorlayout.widget.CoordinatorLayout

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommentBottomSheet(
    private var videoView: VideoView
):BottomSheetDialogFragment(){
    private lateinit var bottomSheetAdapter: BottomSheetAdapter
 private lateinit var recyclerView: RecyclerView
 private lateinit var commentsList: ArrayList<CommentItem>

 companion object{
     var seekPosition : Int? = null
 }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flicks_comment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        recyclerView = view.findViewById(R.id.commentsRecView)
        commentsList = arrayListOf(CommentItem("hello","itsMe",10),CommentItem("hello","itsMe",10),CommentItem("hello","itsMe",10),CommentItem("hello","itsMe",10),CommentItem("hello","itsMe",10),CommentItem("hello","itsMe",10),CommentItem("hello","itsMe",10),CommentItem("hello","itsMe",10),CommentItem("hello","itsMe",10))
        Log.d("abc",commentsList.toString())
        bottomSheetAdapter = BottomSheetAdapter(requireContext(),commentsList
        )
        recyclerView.adapter = bottomSheetAdapter


//        val bottomSheetBehavior = BottomSheetBehavior.from(view)
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//
//        val layout: CoordinatorLayout = view.findViewById(R.id.bottomSheetLayout)
//        layout.minimumHeight = Resources.getSystem().displayMetrics.heightPixels

    }
    

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
//        videoView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
//        Log.d("seekPos", seekPosition.toString())
        if(seekPosition != null){
            videoView.seekTo(seekPosition!!)
            videoView.start()
        }else{
            videoView.resume()
        }

    }

}