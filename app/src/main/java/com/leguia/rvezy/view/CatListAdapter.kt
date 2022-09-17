package com.leguia.rvezy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leguia.rvezy.R
import com.leguia.rvezy.data.CatResponse




class CatListAdapter: PagingDataAdapter<CatResponse, CatListAdapter.CatViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<CatResponse>() {
            override fun areItemsTheSame(oldItem: CatResponse, newItem: CatResponse): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: CatResponse, newItem: CatResponse): Boolean =
                oldItem == newItem
        }
    }

    private var cats = listOf<CatResponse>()
    var onCatClicked: ((CatResponse) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListAdapter.CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_list_item, parent, false)
        val lp = view.layoutParams as GridLayoutManager.LayoutParams
        lp.height = parent.measuredHeight / 4
        view.layoutParams = lp
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatListAdapter.CatViewHolder, position: Int) {

        getItem(position)?.let { holder.bind(it) }
    }

    inner class CatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.cat_image)

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { item -> onCatClicked?.invoke(item) }
            }
        }


        fun bind(cat: CatResponse){
            Glide.with(itemView.context)
                .load(cat.url)
                .fitCenter()
                .into(imageView);
        }


    }
}