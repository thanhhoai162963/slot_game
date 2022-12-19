package com.example.slotgame

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import com.example.slotgame.adapter.SlotGameAdapter
import com.example.slotgame.databinding.ActivityMainBinding
import com.example.slotgame.model.ImageGame
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listData: ArrayList<ImageGame>? = null
    private var mAdapter1: SlotGameAdapter? = null
    private var mAdapter2: SlotGameAdapter? = null
    private var mAdapter3: SlotGameAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setListener()
        mockData()

        setAdapter(listData, 1)
        setAdapter(listData, 2)
        setAdapter(listData, 3)
    }

    private fun setListener() {
        binding.recyclerviewFirst.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewSecond.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewThird.layoutManager = LinearLayoutManager(this)

        binding.btnStart.setOnClickListener {
            val positionFirst = Random.nextInt(11)
            val positionSecond = Random.nextInt(10)
            val positionThird = Random.nextInt(9)

            val linearSmoothScroller1: LinearSmoothScroller =
                object : LinearSmoothScroller(this) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return 200f / displayMetrics.densityDpi
                    }
                }
            linearSmoothScroller1.targetPosition = positionFirst

            val linearSmoothScroller2: LinearSmoothScroller =
                object : LinearSmoothScroller(this) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return 300f / displayMetrics.densityDpi
                    }

                    override fun onStop() {
                        setAnimation()
                        super.onStop()
                    }
                }
            linearSmoothScroller2.targetPosition = positionSecond

            val linearSmoothScroller3: LinearSmoothScroller =
                object : LinearSmoothScroller(this) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return 400f / displayMetrics.densityDpi
                    }
                }
            linearSmoothScroller3.targetPosition = positionThird

            val linearManager1 = (binding.recyclerviewFirst.layoutManager as LinearLayoutManager)
            linearManager1.apply {
                startSmoothScroll(linearSmoothScroller1)
            }
            (binding.recyclerviewSecond.layoutManager as LinearLayoutManager).startSmoothScroll(
                linearSmoothScroller2
            )
            (binding.recyclerviewThird.layoutManager as LinearLayoutManager).startSmoothScroll(
                linearSmoothScroller3
            )

        }
    }

    private fun mockData() {
        listData = ArrayList()
        listData?.apply {
            add(ImageGame(R.drawable.bar, 1))
            add(ImageGame(R.drawable.lemon, 2))
            add(ImageGame(R.drawable.cherry, 3))
            add(ImageGame(R.drawable.orange, 4))
            add(ImageGame(R.drawable.seven, 5))
            add(ImageGame(R.drawable.watermelon, 6))
            add(ImageGame(R.drawable.seven, 7))
            add(ImageGame(R.drawable.orange, 8))
            add(ImageGame(R.drawable.cherry, 9))
            add(ImageGame(R.drawable.lemon, 10))
        }
    }

    private fun setAdapter(images: List<ImageGame>?, numberAdapter: Int?) {
        when (numberAdapter) {
            1 -> {
                mAdapter1 = SlotGameAdapter()
                images?.let { mAdapter1!!.setData(it, this) }
                binding.recyclerviewFirst.apply {
                    adapter = mAdapter1
                }
            }
            2 -> {
                mAdapter2 = SlotGameAdapter()
                images?.let { mAdapter2!!.setData(it, this) }
                binding.recyclerviewSecond.apply {
                    adapter = mAdapter2
                }
            }
            3 -> {
                mAdapter3 = SlotGameAdapter()
                images?.let { mAdapter3!!.setData(it, this) }
                binding.recyclerviewThird.apply {
                    adapter = mAdapter3
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAnimation() {
        val animationTranslate = TranslateAnimation(0f, 0f, 0f, 20f)
        animationTranslate.duration = 1000
        animationTranslate.interpolator = AccelerateDecelerateInterpolator()
        binding.recyclerviewFirst.animation = animationTranslate
        mAdapter1?.notifyDataSetChanged()

    }
}