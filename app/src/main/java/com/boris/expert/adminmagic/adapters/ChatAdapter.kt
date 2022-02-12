package com.boris.expert.adminmagic.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.boris.expert.adminmagic.R
import com.boris.expert.adminmagic.model.Message
import com.boris.expert.adminmagic.utils.Constants
import com.boris.expert.adminmagic.view.activities.BaseActivity
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import java.util.*
import kotlin.collections.ArrayList

class ChatAdapter(val context: Context, val chatList: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageBody: MaterialTextView
        var messageTime: MaterialTextView
        var imageView:AppCompatImageView

        init {
            messageBody = itemView.findViewById(R.id.message_body)
            messageTime = itemView.findViewById(R.id.message_time)
            imageView = itemView.findViewById(R.id.message_image_view)
        }
    }

    class ItemViewHolder1(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageBody: MaterialTextView
        var messageTime: MaterialTextView
        var imageView:AppCompatImageView

        init {
            messageBody = itemView.findViewById(R.id.message_body)
            messageTime = itemView.findViewById(R.id.message_time)
            imageView = itemView.findViewById(R.id.message_image_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.sender_message_item_row,
                parent,
                false
            )
            return ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.receiver_message_item_row,
                parent,
                false
            )
            return ItemViewHolder1(view)
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = chatList[position]
        when (holder.itemViewType) {
            0 -> {
                val senderHolder = holder as ItemViewHolder
                if (item.type == "text" || item.type.isEmpty()){
                    senderHolder.messageBody.text = item.message
                    senderHolder.messageBody.visibility = View.VISIBLE
                    senderHolder.imageView.visibility = View.GONE
                }
                else{
                    Glide
                        .with(context)
                        .load(item.message)
                        .centerCrop()
                        .placeholder(R.drawable.placeholder)
                        .into(senderHolder.imageView)
                    senderHolder.messageBody.visibility = View.GONE
                    senderHolder.imageView.visibility = View.VISIBLE
                }

                senderHolder.messageTime.text = BaseActivity.getDateTimeFromTimeStamp(item.timeStamp)
            }
            1 -> {
                val receiverHolder = holder as ItemViewHolder1
                if (item.type == "text" || item.type.isEmpty()){
                    receiverHolder.messageBody.text = item.message
                    receiverHolder.messageBody.visibility = View.VISIBLE
                    receiverHolder.imageView.visibility = View.GONE
                }
                else{
                    Glide
                        .with(context)
                        .load(item.message)
                        .centerCrop()
                        .placeholder(R.drawable.placeholder)
                        .into(receiverHolder.imageView)
                    receiverHolder.messageBody.visibility = View.GONE
                    receiverHolder.imageView.visibility = View.VISIBLE
                }
                receiverHolder.messageTime.text = BaseActivity.getDateTimeFromTimeStamp(item.timeStamp)
            }
            else -> {

            }
        }


    }

    override fun getItemCount(): Int {
        return chatList.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (chatList[position].userId == Constants.firebaseUserId) {
            1
        } else {
            0
        }
    }

}