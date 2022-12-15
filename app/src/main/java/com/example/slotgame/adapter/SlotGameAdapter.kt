package com.example.slotgame.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.slotgame.R
import com.example.slotgame.databinding.ItemSlotGameBinding
import com.example.slotgame.model.ImageGame

class SlotGameAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDataList: List<ImageGame>? = null
    private lateinit var context: Context

    fun setData(data: List<ImageGame>, context: Context) {
        mDataList = data
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemSlotGameBinding =
            ItemSlotGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(itemSlotGameBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GameViewHolder) {
            mDataList?.get(position)?.let { holder.bindData(it) }
        }
    }

    override fun getItemCount(): Int {
        if (mDataList != null) {
            return mDataList?.size!!
        }
        return 0
    }

    inner class GameViewHolder(var itemSlotGameBinding: ItemSlotGameBinding) :
        RecyclerView.ViewHolder(itemSlotGameBinding.root) {
        fun bindData(imageGame: ImageGame) {
            itemSlotGameBinding.imgFirst.background =
                ContextCompat.getDrawable(context, R.drawable.bar)
            itemSlotGameBinding.imgSecond.background =
                ContextCompat.getDrawable(context, R.drawable.lemon)
            itemSlotGameBinding.imgThird.background =
                ContextCompat.getDrawable(context, R.drawable.cherry)
        }
    }
}