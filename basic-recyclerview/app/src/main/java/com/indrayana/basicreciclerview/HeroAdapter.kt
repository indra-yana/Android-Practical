package com.indrayana.basicreciclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/****************************************************
 * Created by Indra Muliana (indra.ndra26@gmail.com)
 * On Wednesday, 12/05/2021 09.11
 * https://gitlab.com/indra-yana
 ****************************************************/

class HeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClickCallback: OnItemClickCallback? = null
    var itemViewType = ViewType.LIST

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View

        return when(itemViewType) {
            ViewType.LIST -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_hero,  parent, false)
                HeroListViewHolder(view)
            }
            ViewType.GRID -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_hero,  parent, false)
                HeroGridViewHolder(view)
            }
            ViewType.CARD -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_hero,  parent, false)
                HeroCardViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(itemViewType) {
            ViewType.LIST -> (holder as HeroListViewHolder).bind(listHero[position])
            ViewType.GRID -> (holder as HeroGridViewHolder).bind(listHero[position])
            ViewType.CARD -> (holder as HeroCardViewHolder).bind(listHero[position])
        }
    }

    override fun getItemCount(): Int = listHero.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }

    inner class HeroListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        private var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)

        fun bind(hero: Hero) {
            tvName.text = hero.name
            tvDetail.text = hero.detail
            Glide.with(itemView.context)
                .load(hero.photo)
                .apply(RequestOptions().override(55, 55))
                .into(imgPhoto)

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(hero)
            }
        }
    }

    inner class HeroGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)

        fun bind(hero: Hero) {
            Glide.with(itemView.context)
                .load(hero.photo)
                .apply(RequestOptions().override(350, 350))
                .into(imgPhoto)

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(hero)
            }
        }
    }

    inner class HeroCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        private var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        private var btnFavourite: Button = itemView.findViewById(R.id.btn_set_favourite)
        private var btnShare: Button = itemView.findViewById(R.id.btn_set_share)

        fun bind(hero: Hero) {
            tvName.text = hero.name
            tvDetail.text = hero.detail
            Glide.with(itemView.context)
                .load(hero.photo)
                .apply(RequestOptions().override(350, 350))
                .into(imgPhoto)

            btnFavourite.setOnClickListener {
                Toast.makeText(itemView.context, "${listHero[absoluteAdapterPosition].name} Added to favourite!", Toast.LENGTH_SHORT).show()
            }

            btnShare.setOnClickListener {
                Toast.makeText(itemView.context, "${listHero[absoluteAdapterPosition].name} Shared to social media!", Toast.LENGTH_SHORT).show()
            }

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(hero)
            }
        }
    }
}