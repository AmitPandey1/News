package com.sodelhinews.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import com.sodelhinews.R
import com.sodelhinews.model.NewsArticle
import com.sodelhinews.network.ApiCalls
import com.sodelhinews.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Util {

    /**
     * Instantiates a new MyViewHolder
     * this is dynamic method to show toast
     * @param msg
     * @param length
     * @param context
     */
    fun showToast(msg:String,length:Int,context:Context){
        when(length){
            0 -> Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
            else -> Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Instantiates a new MyViewHolder
     * this is dynamic method to check internet
     * @param context
     */
    fun isNetworkAvailable(context : Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            if (network != null) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                //It will check for both wifi and cellular network
                return networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
            return false
        } else {
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }

    /**
     * Instantiates a new MyViewHolder
     * this is dynamic method to fetch newsApi data
     * @param context
     * @param source
     * @param category
     * @param callback
     */
    fun fetchNewsData(source:String,category: String?,context: Context,callback:CommonMethod){
        val apiService: ApiService = ApiCalls.create()
        val call: Call<NewsArticle>
        call = if (category == null){
            apiService.getNewsByCountry(source,context.getString(R.string.key))
        }else{
            apiService.getNewsByCountryAndCategory(source,category,context.getString(R.string.key))
        }
        call.enqueue(object : Callback<NewsArticle> {
            override fun onResponse(call: Call<NewsArticle>, response: Response<NewsArticle>) {
                if (response.body().status!!.equals("ok")){
                    callback.success(response)
                }
            }
            override fun onFailure(call: Call<NewsArticle>, t: Throwable) {
                showToast(t.toString(),1, context)
                println("api failure : "+t)
            }
        })
    }
}

    interface CommonMethod{
        fun success(response: Response<NewsArticle>)
    }