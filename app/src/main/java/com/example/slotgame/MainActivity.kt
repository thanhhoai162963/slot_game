package com.example.slotgame

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.slotgame.adapter.SlotGameAdapter
import com.example.slotgame.databinding.ActivityMainBinding
import com.example.slotgame.model.ImageGame
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mListData: ArrayList<ImageGame>? = null
    private var mAdapter1: SlotGameAdapter? = null
    private var mAdapter2: SlotGameAdapter? = null
    private var mAdapter3: SlotGameAdapter? = null
    private var mResult1: Int? = null
    private var mResult2: Int? = null
    private var mResult3: Int? = null
    private var mScore: Int? = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setListener()
        mockData()

        setAdapter(mListData, 1)
        setAdapter(mListData, 2)
        setAdapter(mListData, 3)
    }

    private fun setListener() {
        binding.recyclerviewFirst.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    return true
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
        }

        binding.recyclerviewSecond.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    return true
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
        }

        binding.recyclerviewThird.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    return true
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
        }
        binding.recyclerviewThird.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("bbb123", "da dừng lại")
                    result()
                }
            }
        })

        val linearSmoothScroller1: LinearSmoothScroller =
            object : LinearSmoothScroller(this) {
                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                    return 200f / displayMetrics.densityDpi
                }
            }
        val linearSmoothScroller2: LinearSmoothScroller =
            object : LinearSmoothScroller(this) {
                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                    return 300f / displayMetrics.densityDpi
                }
            }
        val linearSmoothScroller3: LinearSmoothScroller =
            object : LinearSmoothScroller(this) {
                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                    return 400f / displayMetrics.densityDpi
                }
            }
        binding.btnStart.setOnClickListener {
            val positionFirst = Random.nextInt(11)
            val positionSecond = Random.nextInt(10)
            val positionThird = Random.nextInt(9)

            linearSmoothScroller1.targetPosition = positionFirst
            mResult1 = linearSmoothScroller1.targetPosition

            linearSmoothScroller2.targetPosition = positionSecond
            mResult2 = linearSmoothScroller2.targetPosition

            linearSmoothScroller3.targetPosition = positionThird
            mResult3 = linearSmoothScroller3.targetPosition

            (binding.recyclerviewFirst.layoutManager as LinearLayoutManager).startSmoothScroll(
                linearSmoothScroller1
            )
            (binding.recyclerviewSecond.layoutManager as LinearLayoutManager).startSmoothScroll(
                linearSmoothScroller2
            )
            (binding.recyclerviewThird.layoutManager as LinearLayoutManager).startSmoothScroll(
                linearSmoothScroller3
            )
        }
    }

    private fun mockData() {
        mListData = ArrayList()
        mListData?.apply {
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

    @SuppressLint("SetTextI18n")
    private fun result() {
        if (mResult1 == mResult2 && mResult2 == mResult3) {
            mScore = mScore?.plus(10)
            binding.txtResult.text = "Score: ${mScore?.plus(50)}"
        } else {
            mScore = mScore?.minus(10)
            binding.txtResult.text = "Score: ${mScore?.minus(10)}"
        }
    }


}