package com.hk.streamcommentui

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainScreenFlicksAdapter(
    var videoList: List<FlicksModelClass>,
    var context: Context,
//    var posBack: Int,
//    var temp: Int,
    var listener: (Int) -> Unit
) :
    RecyclerView.Adapter<MainScreenFlicksAdapter.FlicksViewHolder>() {

    class FlicksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoView: VideoView = itemView.findViewById(R.id.videoView)
        var title: TextView = itemView.findViewById(R.id.titleTextView)
        var desc: TextView = itemView.findViewById(R.id.descTextView)
        var seekBar: SeekBar? = itemView.findViewById(R.id.seekBar)
        private val updateHandler: Handler = Handler()
        var commentBtn: ImageView = itemView.findViewById(R.id.commentImageView)



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
    ): MainScreenFlicksAdapter.FlicksViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.each_fullscreen_flicks, parent, false)
        return MainScreenFlicksAdapter.FlicksViewHolder(view)

    }

    override fun onBindViewHolder(holder: MainScreenFlicksAdapter.FlicksViewHolder, position: Int) {

       holder.setVideoData(videoList[position])

        val bottomSheetFragment = CommentBottomSheet(holder.videoView)
        holder.commentBtn.setOnClickListener{
            bottomSheetFragment.show((context as AppCompatActivity).supportFragmentManager,"BottomSheetDialog")

            CommentBottomSheet.seekPosition = holder.videoView.currentPosition
            holder.videoView.pause()
        }

        holder.videoView.setOnClickListener{
            listener(position)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

}
