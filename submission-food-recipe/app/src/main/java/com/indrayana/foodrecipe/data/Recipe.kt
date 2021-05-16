package com.indrayana.foodrecipe.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/****************************************************
 * Created by Indra Muliana (indra.ndra26@gmail.com)
 * On Wednesday, 12/05/2021 08.57
 * https://gitlab.com/indra-yana
 ****************************************************/

@Parcelize
data class Recipe(
    var title: String = "",
    var description: String = "",
    var ingredient: ArrayList<String>,
    var step: ArrayList<String>,
    var thumb: String = ""
) : Parcelable