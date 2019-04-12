package com.chethan.sport.view

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.chethan.sport.R
import com.chethan.sport.model.Item
import com.chethan.sport.viewmodel.FitnessDataViewModel


class UserGoalsListAdapter(
    val viewLifecycleOwner: LifecycleOwner,
    val items: List<Item>,
    val fitnessDataViewModel: FitnessDataViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemClickListener: ItemClickListener? = null

    inner class HeaderViewHolder(var mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind() {
            var tvSteps = mView.findViewById<TextView>(R.id.tvSteps)
            var tvCal = mView.findViewById<TextView>(R.id.tvCal)
            var tvKm = mView.findViewById<TextView>(R.id.tvKm)
            val progressBar = mView.findViewById(R.id.progressBar) as ProgressBar



            fitnessDataViewModel.numberOfSteps.observe(viewLifecycleOwner, Observer { result ->
                val numberOfSteps = result.toString();
                tvSteps.setText(numberOfSteps)
                setProgressBar(progressBar, result.toInt())
            })

            fitnessDataViewModel.caloriesBurn.observe(viewLifecycleOwner, Observer { result ->
                val caloriesBurn = result.toString();
                tvCal.setText(caloriesBurn)
            })


            fitnessDataViewModel.distanceCovered.observe(viewLifecycleOwner, Observer { result ->
                val caloriesBurn = result.toString();
                tvKm.setText(caloriesBurn)
            })

        }
    }

    private fun setProgressBar(progressBar: ProgressBar, value: Int) {
        val animation = ObjectAnimator.ofInt(
            progressBar,
            "progress",
            0,
            value
        ) // see this max value coming back here, we animate towards that value
        animation.duration = 3000 // in milliseconds
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }

    inner class YourGoalHeaderHolder(var mView: View) : RecyclerView.ViewHolder(mView) {

        fun bind() {

        }
    }

    inner class ListItemHolder(var mView: View) : RecyclerView.ViewHolder(mView) {

        fun bind(item: Item) {
            var tvName = mView.findViewById<TextView>(R.id.tvName)
            tvName.text = item.title

            mView.setOnClickListener {
                onItemClickListener?.onItemClick(item)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                return (viewHolder as HeaderViewHolder).bind()
            }

            1 -> {
                return (viewHolder as YourGoalHeaderHolder).bind()
            }

            else -> {
                return (viewHolder as ListItemHolder).bind(items[position - 2])
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        when (type) {
            0 -> {
                var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_header_view, parent, false)
                return HeaderViewHolder(view)
            }

            1 -> {
                var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_your_goal_label, parent, false)
                return YourGoalHeaderHolder(view)
            }

            else -> {
                var view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_goal_item, parent, false)
                return ListItemHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + 2;
    }

    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(item: Item)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}