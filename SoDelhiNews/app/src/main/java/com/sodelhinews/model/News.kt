package com.sodelhinews.model

import com.google.gson.annotations.SerializedName


class News {

    @SerializedName("author")
    var authorName: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description :String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("urlToImage")
    var urlToImage: String? = null

    @SerializedName("publishedAt")
    var publishedAt: String? = null

    var source: Source? = null

}

class Source {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

}
