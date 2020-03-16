package com.sodelhinews.model

import com.google.gson.annotations.SerializedName
import com.sodelhinews.model.News

class NewsArticle {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResults = 0

    @SerializedName("articles")
    var articles: List<News>? = null
}