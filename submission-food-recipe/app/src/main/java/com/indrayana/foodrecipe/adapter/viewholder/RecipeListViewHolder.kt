package com.indrayana.foodrecipe.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.indrayana.foodrecipe.R
import com.indrayana.foodrecipe.adapter.IOnItemClickListener
import com.indrayana.foodrecipe.data.Recipe

/****************************************************
 * Created by Indra Muliana (indra.ndra26@gmail.com)
 * On Friday, 14/05/2021 22.33
 * https://gitlab.com/indra-yana
 ****************************************************/

class RecipeListViewHolder(itemView: View) : BaseViewHolder(itemView) {
    private var tvName: TextView = itemView.findViewById(R.id.tv_item_title)
    private var tvDetail: TextView = itemView.findViewById(R.id.tv_item_description)
    private var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_thumb)

    constructor(parent: ViewGroup) : this(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_mode_list,
            parent,
            false
        )
    )

    override fun bindView(viewGroup: ViewGroup): View {
        return LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item_mode_list,
            viewGroup,
            false
        )
    }

    override fun bind(recipe: Recipe, listener: IOnItemClickListener?) {
        tvName.text = recipe.title
        tvDetail.text = recipe.description
        Glide.with(itemView.context)
            .load(recipe.thumb)
            .apply(RequestOptions().override(55, 55))
            .into(imgPhoto)

        itemView.setOnClickListener {
            listener?.onItemClicked(recipe)
        }
    }
}