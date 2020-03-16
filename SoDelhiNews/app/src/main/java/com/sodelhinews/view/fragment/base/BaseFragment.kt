package com.sodelhinews.view.fragment.base


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sodelhinews.R


abstract class BaseFragment : Fragment(), CommonMethods {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(setLayout(R.layout.fragment_base), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(view)
    }

    /**
     * Instantiates a new setLayout
     * this method to set layout
     * @param view
     */
    abstract fun setLayout(view : Int):Int

    /**
     * Instantiates a new setData
     * this method to set data for view
     * @param view
     */
    override fun setData(view: View) {}

    /**
     * Instantiates a new setClickListener
     * this method to set click listener for view
     *
     */
    override fun setClickListener() {}

    override fun onClick(v: View?) {}

}

private interface CommonMethods : View.OnClickListener{
    fun setData(view: View)
    fun setClickListener()
}
