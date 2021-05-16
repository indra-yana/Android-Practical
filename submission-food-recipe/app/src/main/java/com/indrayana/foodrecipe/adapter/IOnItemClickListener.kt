package com.indrayana.foodrecipe.adapter

import com.indrayana.foodrecipe.data.Recipe

/****************************************************
 * Created by Indra Muliana (indra.ndra26@gmail.com)
 * On Friday, 14/05/2021 22.25
 * https://gitlab.com/indra-yana
 ****************************************************/

interface IOnItemClickListener {
    fun onItemClicked(data: Recipe)
    fun onButtonFavouriteClicked(data: Recipe) { }
    fun onButtonShareClicked(data: Recipe) { }
}