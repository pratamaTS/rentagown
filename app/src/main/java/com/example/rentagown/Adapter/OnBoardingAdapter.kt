package com.example.rentagown.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.rentagown.Model.OnBoardingItem
import com.example.rentagown.R

class OnBoardingAdapter(var mContext: Context, mListScreen: ArrayList<OnBoardingItem>) :
    PagerAdapter() {
    var mListScreen: ArrayList<OnBoardingItem>
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutScreen: View = inflater.inflate(R.layout.layout_screen_boading, null)
        val imageSlide = layoutScreen.findViewById<ImageView>(R.id.img_intro)
        val title = layoutScreen.findViewById<TextView>(R.id.tv_intro_title)
        val desc = layoutScreen.findViewById<TextView>(R.id.tv_intro_desc)
        title.setText(mListScreen[position].title)
        desc.setText(mListScreen[position].description)
        imageSlide.setImageResource(mListScreen[position].screenImg)
        container.addView(layoutScreen)
        return layoutScreen
    }

    override fun getCount(): Int {
        return mListScreen.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    init {
        this.mListScreen = mListScreen
    }
}