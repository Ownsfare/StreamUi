package com.hk.streamcommentui

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.hk.streamcommentui.CommentBottomSheet.Companion.seekPosition


class FullScreenFlicksAdapter(
    var videoList: List<FlicksModelClass>,
    var context: Context,
    var pos: Int,
    var listener: (Int) -> Unit
    ) :
    RecyclerView.Adapter<FullScreenFlicksAdapter.FlicksViewHolder>() {

    class FlicksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoView: VideoView = itemView.findViewById(R.id.videoView)
        var title: TextView = itemView.findViewById(R.id.titleTextView)
        var desc: TextView = itemView.findViewById(R.id.descTextView)
        var seekBar: SeekBar? = itemView.findViewById(R.id.seekBar)
        private val updateHandler: Handler = Handler()
        var commentBtn: ImageView = itemView.findViewById(R.id.commentImageView)
        var backIv: ImageView = itemView.findViewById(R.id.backImageView)



        fun setVideoData(video: FlicksModelClass) {

            title.text = video.title
            desc.text = video.desc

            videoView.setVideoPath(video.videourl)


            videoView.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.start()

                seekBar?.setProgress(0);
                seekBar?.setMax(videoView.duration)
                seekBar?.postDelayed(updateVideoTime, 0)

                val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight
                    .toFloat()
                val screennRatio = videoView.width / videoView.height
                    .toFloat()
                val scale = videoRatio / screennRatio
                if (scale >= 1f) {
                    videoView.scaleX = scale
                } else {
                    videoView.scaleY = 1f / scale
                }

            }

            videoView.setOnCompletionListener { mediaPlayer ->
                mediaPlayer.start()

            }

        }

        private val updateVideoTime: Runnable = object : Runnable {
            override fun run() {
                val currentPosition: Int = videoView.currentPosition
                seekBar?.progress = currentPosition.toInt()
                updateHandler.postDelayed(this, 0)
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FullScreenFlicksAdapter.FlicksViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.each_fullscreen_flicks, parent, false)
        return FullScreenFlicksAdapter.FlicksViewHolder(view)

    }

    override fun onBindViewHolder(holder: FullScreenFlicksAdapter.FlicksViewHolder, position: Int) {
        holder.setVideoData(videoList[(position+pos)%videoList.size])

        val bottomSheetFragment = CommentBottomSheet(holder.videoView)
        holder.commentBtn.setOnClickListener{
            bottomSheetFragment.show((context as AppCompatActivity).supportFragmentManager,"BottomSheetDialog")
//            holder.videoView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,860)
            seekPosition = holder.videoView.currentPosition
            holder.videoView.pause()
            }
        holder.backIv.setOnClickListener{
            listener((position+pos)%videoList.size)
//            Log.d("xyz",((position+pos)%videoList.size).toString())
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

}

