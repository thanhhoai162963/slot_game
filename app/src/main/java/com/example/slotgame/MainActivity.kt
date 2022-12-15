package com.example.slotgame

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
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
        binding.btnStart.setOnClickListener {
            val linearSmoothScroller1: LinearSmoothScroller =
                object : LinearSmoothScroller(this) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return 200f / displayMetrics.densityDpi
                    }
                }
            linearSmoothScroller1.targetPosition = Random.nextInt(6)

            val linearSmoothScroller2: LinearSmoothScroller =
                object : LinearSmoothScroller(this) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return 300f / displayMetrics.densityDpi
                    }
                }
            linearSmoothScroller2.targetPosition = Random.nextInt(6)

            val linearSmoothScroller3: LinearSmoothScroller =
                object : LinearSmoothScroller(this) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return 400f / displayMetrics.densityDpi
                    }
                }
            linearSmoothScroller3.targetPosition = Random.nextInt(6)


            (binding.listImgFirst.layoutManager as LinearLayoutManager).startSmoothScroll(
                linearSmoothScroller1
            )
            (binding.listImgSecond.layoutManager as LinearLayoutManager).startSmoothScroll(
                linearSmoothScroller2
            )
            (binding.listImgThird.layoutManager as LinearLayoutManager).startSmoothScroll(
                linearSmoothScroller3
            )
        }
    }

    private fun setListener() {
        binding.listImgFirst.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.listImgSecond.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.listImgThird.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


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
        }
    }

    private fun setAdapter(images: List<ImageGame>?, numberAdapter: Int?) {
        when (numberAdapter) {
            1 -> {
                mAdapter1 = SlotGameAdapter()
                images?.let { mAdapter1!!.setData(it, this) }
                binding.listImgFirst.apply {
                    adapter = mAdapter1
                    addItemDecoration(
                        DividerItemDecoration(
                            this@MainActivity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
            }
            2 -> {
                mAdapter2 = SlotGameAdapter()
                images?.let { mAdapter2!!.setData(it, this) }
                binding.listImgSecond.apply {
                    adapter = mAdapter2
                    addItemDecoration(
                        DividerItemDecoration(
                            this@MainActivity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
            }
            3 -> {
                mAdapter3 = SlotGameAdapter()
                images?.let { mAdapter3!!.setData(it, this) }
                binding.listImgThird.apply {
                    adapter = mAdapter3
                    addItemDecoration(
                        DividerItemDecoration(
                            this@MainActivity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
            }
        }

    }

}