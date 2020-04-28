package com.example.pixabayapi

import android.annotation.SuppressLint
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.item.view.*

class ItemAdapter(
    private val listItems: List<Image> = listOf(),
    private val clickListener: (Image) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val apiManager by lazy { ApiManager() }

        @SuppressLint("SetTextI18n")
        fun bindItem(image: Image, clickListener: (Image) -> Unit) {
            view.tags_text.text = "Tags: " + image.tags
            view.likes_text.text = "Likes: " + image.likes.toString()
            Picasso.get()
                .load(image.largeImageURL)
                .into(view.image_view)

            view.setOnClickListener { clickListener(image) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(listItems[position], clickListener)
    }
}