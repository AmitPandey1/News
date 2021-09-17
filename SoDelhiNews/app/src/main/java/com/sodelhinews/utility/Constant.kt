package com.sodelhinews.utility

class Constant {
    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"
        private const val PAGE_START = 1
        var currentPage: Int = PAGE_START
        var isLastPage = false
        const val totalPage = 10
        var isLoading = false
        const val NETWORK_CALL_TIMEOUT = 60
    }
}