package com.example.taskapp51.ui.onBoard

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.taskapp51.R

class BoardAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    private val listBoarding = arrayListOf(BoardModel(
        R.drawable.img,
        "To-do list!",
        "Here you can write down something important or make a schedule for tomorrow:)"
    ),
        BoardModel(R.drawable.img2,
        "Share your crazy idea ^_^",
        "You can easily share with your report, list or schedule and it's convenient"
        ),
        BoardModel(R.drawable.img3,
        "Flexibility",
        "Your note with you at home, at work, even at the resort")
    )

    override fun getCount(): Int {
        return listBoarding.size
    }

    override fun getItem(position: Int): Fragment {
        val data = bundleOf("onBoarding" to listBoarding[position])
        val fragment = OnBoardPageFragment()
        fragment.arguments = data
        return fragment
    }
}