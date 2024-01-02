package com.example.suitmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmedia.R
import com.example.suitmedia.data.User

class UserAdapter(private val itemClickListener: OnItemClickListener ) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    interface OnItemClickListener {
        fun onItemClicked(user: User)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClicked(user)
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.findViewById<TextView>(R.id.tv_item_firsname).text= user.first_name

            val image = itemView.findViewById<ImageView>(R.id.img_item_photo)
            Glide.with(itemView.context).
                    load(user.avatar).into(image)
            itemView.findViewById<TextView>(R.id.tv_item_lastname).text = user.last_name
            itemView.findViewById<TextView>(R.id.tv_email).text = user.email
        }
    }

    private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

}

