package com.leguia.rvezy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leguia.rvezy.R
import com.leguia.rvezy.data.CatResponse

class CatListAdapter: RecyclerView.Adapter<CatListAdapter.CatViewHolder>() {
    private var cats = listOf<CatResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListAdapter.CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_list_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatListAdapter.CatViewHolder, position: Int) {
        val cat = cats.get(position)
        holder.bind(cat)
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    fun setItems(items: List<CatResponse>){
        cats = items
        notifyDataSetChanged()
    }

    class CatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.cat_image)
        fun bind(cat: CatResponse){
            Glide.with(itemView.context)
                .load(cat.url)
                .fitCenter()
                .into(imageView);
        }
    }
}