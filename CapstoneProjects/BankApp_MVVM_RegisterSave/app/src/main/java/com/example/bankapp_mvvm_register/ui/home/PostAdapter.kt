package com.example.bankapp_mvvm_register.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp_mvvm_register.data.model.Post
import com.example.bankapp_mvvm_register.databinding.ItemPostBinding


/**Created by Raviteja Emandi on 22,June,2025 **/
class PostAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.tvTitle.text = post.title
        holder.binding.tvBody.text = post.body
        holder.binding.tvId.text = "ID: ${post.id}"
    }

    override fun getItemCount(): Int = posts.size
}
