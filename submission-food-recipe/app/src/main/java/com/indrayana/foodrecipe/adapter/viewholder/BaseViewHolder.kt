package com.indrayana.foodrecipe.adapter.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indrayana.foodrecipe.adapter.IOnItemClickListener
import com.indrayana.foodrecipe.data.Recipe

/****************************************************
 * Created by Indra Muliana (indra.ndra26@gmail.com)
 * On Friday, 14/05/2021 22.48
 * https://gitlab.com/indra-yana
 ****************************************************/

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindView(viewGroup: ViewGroup) : View
    abstract fun bind(recipe: Recipe, listener: IOnItemClickListener?)
}