package com.sodelhinews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sodelhinews.R
import com.sodelhinews.adapter.NewsAdapter.MyViewHolder
import com.sodelhinews.model.News
import com.squareup.picasso.Picasso

class NewsAdapter(private val newsList: List<News>) : RecyclerView.Adapter<MyViewHolder>() {

    /**
     * Instantiates a new MyViewHolder
     * this class is for view holder
     * @param view
     */
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById<View>(R.id.news_title) as TextView
        var description: TextView = view.findViewById<View>(R.id.news_description) as TextView
        var author: TextView = view.findViewById<View>(R.id.news_writer) as TextView
        var sourceUrl: TextView = view.findViewById<View>(R.id.news_sourceUrl) as TextView
        var source: TextView = view.findViewById<View>(R.id.news_source) as TextView
        var publishAt: TextView = view.findViewById<View>(R.id.news_publisher) as TextView
        var image: ImageView = view.findViewById<View>(R.id.news_image) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news: News = newsList[position]
        holder.title.text = news.title
        holder.description.text = news.description
        holder.author.text = "Writer : ${news.authorName}"
        holder.publishAt.text = news.publishedAt
        holder.sourceUrl.text = news.url
        holder.source.text = if (news.source?.name == null) "Unknown" else news.source!!.name
        Picasso.get().load(news.urlToImage).into(holder.image)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    /**
     * Instantiates a new addLoading
     * this method to add new items in news list
     * @param
     */
    fun addLoading() {
        val newsList: ArrayList<News> = this.newsList as ArrayList<News>
        newsList.add(News())
        notifyItemInserted(newsList.size - 1)
    }
}