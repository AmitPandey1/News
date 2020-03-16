package com.sodelhinews.view.fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sodelhinews.utility.Constant.Companion.currentPage
import com.sodelhinews.utility.Constant.Companion.isLastPage
import com.sodelhinews.utility.Constant.Companion.isLoading
import com.sodelhinews.utility.Constant.Companion.totalPage
import com.sodelhinews.R
import com.sodelhinews.model.News
import com.sodelhinews.adapter.NewsAdapter
import com.sodelhinews.model.NewsArticle
import com.sodelhinews.adapter.PaginationListener
import com.sodelhinews.view.fragment.base.BaseFragment
import com.sodelhinews.utility.CommonMethod
import com.sodelhinews.utility.Util
import kotlinx.android.synthetic.main.fragment_home_screen.*
import retrofit2.Response
import java.lang.Exception


class HomeScreen : BaseFragment() {
    var count = 0       // set count for to hit different api with every count increment
    private var mAdapter: NewsAdapter? = null
    private var newsView: RecyclerView? = null
    private val mLayoutManager = LinearLayoutManager(context)      // set layout manager

    override fun setLayout(view: Int): Int {
        return R.layout.fragment_home_screen
    }

    override fun setData(view: View) {
        super.setData(view)

        // add swipe listener for recycler view
        val swipeRefreshLayout : SwipeRefreshLayout? = refreshLayout
        swipeRefreshLayout!!.setOnRefreshListener {
            setDataInRecyclerView("in",null)     // perform api call
            swipeRefreshLayout.isRefreshing = false   // reset the SwipeRefreshLayout to stop the loading spinner)
        }
        setDataInRecyclerView("in",null)
    }

    /**
     * Instantiates a new setLayout
     * this method to set data in recycler view
     * @param country
     * @param category
     */
    private fun setDataInRecyclerView(country:String, category: String?){
        if (Util.isNetworkAvailable(context!!)) {
            Util.fetchNewsData(country, category, context!!, object : CommonMethod {
                override fun success(response: Response<NewsArticle>) {
                    val newsList: List<News>? = response.body().articles
                    newsView = homeScreen_list      // init recyclerView id
                    newsView!!.layoutManager = mLayoutManager
                    newsView!!.itemAnimator = DefaultItemAnimator() // set anim for item
                    mAdapter = NewsAdapter(newsList!!)              // set adapter
                    newsView!!.adapter = mAdapter

                    // add pagination listener for endless scroll
                    newsView!!.addOnScrollListener(object : PaginationListener(mLayoutManager) {
                        override fun loadMoreItems() {
                            isLoading = true
                            currentPage++
                            loadMore()
                        }
                        override fun isLastPage(): Boolean {
                            return isLastPage
                        }
                        override fun isLoading(): Boolean {
                            return isLoading
                        }
                    })
                }
            })
        }else{
            Util.showToast(getString(R.string.internet_warning),1,context!!)
        }
    }

    /**
     * Instantiates a new setLayout
     * this method is to check if user is
     * on last page then show new data from different api
     */
    fun loadMore() {
        // every time when this method will be call,
        // count will increment and different api will be hit
        count++
        try {
            when(count){
                0 -> setDataInRecyclerView("in","health")
                1 -> setDataInRecyclerView("in","business")
                2 -> setDataInRecyclerView("in","entertainment")
                3 -> setDataInRecyclerView("in","science")
                4 -> setDataInRecyclerView("in","sports")
                5 -> setDataInRecyclerView("in","technology")
                else -> setDataInRecyclerView("in",null)
            }

            // check weather is last page or not
            if (currentPage < totalPage) {
                mAdapter!!.addLoading()
            } else {
                isLastPage = true
            }
            isLoading = false
        }catch (e:Exception){}
    }
}


