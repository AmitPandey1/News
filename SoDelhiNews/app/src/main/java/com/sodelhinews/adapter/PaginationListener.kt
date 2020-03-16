package com.sodelhinews.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationListener(private var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0){
                loadMoreItems()
            }
        }
    }

    /**
     * Instantiates a new loadMoreItems
     * @param
     */
    protected abstract fun loadMoreItems()


    /**
     * Instantiates a new isLastPage
     * @param
     */
    abstract fun isLastPage(): Boolean

    /**
     * Instantiates a new isLoading
     * @param
     */
    abstract fun isLoading(): Boolean
}