package com.hk.streamcommentui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView



class BottomSheetAdapter(
    private val context: Context ,
    private val commentsList: List<CommentItem>
) : RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var comment : TextView = itemView.findViewById(R.id.commentTv)
        var userName: TextView = itemView.findViewById(R.id.userNameTv)
        var noOfComments: TextView = itemView.findViewById(R.id.noOfCommentsTv)
        var image : ImageView = itemView.findViewById(R.id.profileIv)
        var recyclerView: RecyclerView = itemView.findViewById(R.id.commentRv)
        var showCommentsTv: TextView = itemView.findViewById(R.id.showCommentsTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(
           LayoutInflater.from(context).inflate(R.layout.single_comment_row,parent,false)
       )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = commentsList[position].comment
        val userName = commentsList[position].userName
        val noOfComments = commentsList[position].noOfComments
//        val imgUrl = commentsList[position].imgUrl

        holder.comment.text = comment
        holder.userName.text = userName
        holder.noOfComments.text = "${noOfComments} Comments"
        holder.showCommentsTv.setOnClickListener{


        }
//        Glide.with(context).load(imgUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }
}
