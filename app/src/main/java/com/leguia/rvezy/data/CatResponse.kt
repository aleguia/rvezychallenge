package com.leguia.rvezy.data

import com.google.gson.annotations.SerializedName

data class CatResponse(

    @SerializedName("breeds" ) var breeds : ArrayList<Breed> = arrayListOf(),
    @SerializedName("id"     ) var id     : String?           = null,
    @SerializedName("url"    ) var url    : String?           = null,
    @SerializedName("width"  ) var width  : Int?              = null,
    @SerializedName("height" ) var height : Int?              = null

)
