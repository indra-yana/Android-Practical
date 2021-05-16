package com.indrayana.foodrecipe.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indrayana.foodrecipe.adapter.viewholder.BaseViewHolder
import com.indrayana.foodrecipe.adapter.viewholder.RecipeCardViewHolder
import com.indrayana.foodrecipe.adapter.viewholder.RecipeGridViewHolder
import com.indrayana.foodrecipe.adapter.viewholder.RecipeListViewHolder
import com.indrayana.foodrecipe.data.Recipe

/****************************************************
 * Created by Indra Muliana (indra.ndra26@gmail.com)
 * On Wednesday, 12/05/2021 09.11
 * https://gitlab.com/indra-yana
 ****************************************************/

class RecipeAdapter(private val listRecipe: ArrayList<Recipe>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var iOnItemClickListener: IOnItemClickListener? = null
    var holderType = ViewHolderType.CARD
    var vHolder:RecyclerView.ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(holderType) {
            ViewHolderType.CARD -> RecipeCardViewHolder(parent)
            ViewHolderType.LIST -> RecipeListViewHolder(parent)
            ViewHolderType.GRID -> RecipeGridViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder).bind(listRecipe[position], iOnItemClickListener)
        vHolder = holder
    }

    override fun getItemCount(): Int = listRecipe.size

    private fun bindView(viewGroup: ViewGroup, baseViewHolder: BaseViewHolder) : RecyclerView.ViewHolder {
        baseViewHolder.bindView(viewGroup)

        return baseViewHolder
    }

}