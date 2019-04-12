package com.chethan.sport.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.chethan.sport.R
import com.chethan.sport.model.Item
import com.chethan.sport.viewmodel.FitnessDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserGoalDetailFragment : Fragment() {


    lateinit var item: Item
    private val fitnessDataViewModel: FitnessDataViewModel by viewModel()

    companion object {
        const val KEY_ITEM = "KEY_ITEM"

        fun newInstance(items: Item): UserGoalDetailFragment {
            val args = Bundle()
            args.putSerializable(KEY_ITEM, items)
            val fragment = UserGoalDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { item = it.getSerializable(KEY_ITEM) as Item }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_goals_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle = getView()?.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = getView()?.findViewById<TextView>(R.id.tvDescription)
        val tvTrophy = getView()?.findViewById<TextView>(R.id.tvTrophy)
        val tvPoints = getView()?.findViewById<TextView>(R.id.tvPoints)
        val tvSteps = getView()?.findViewById<TextView>(R.id.tvSteps)

        val progressBar = getView()?.findViewById<ProgressBar>(R.id.progressBar)
        progressBar!!.max = item.goal!!.toInt()


        fitnessDataViewModel.numberOfSteps.observe(viewLifecycleOwner, Observer { result ->
            val numberOfSteps = result.toInt()
            val stepsProgress = numberOfSteps.toString() + "/" +item.goal
            tvSteps?.setText(stepsProgress)
            setProgressBar(progressBar, numberOfSteps)
        })


        tvTitle?.setText(item.title)
        tvDescription?.setText(item.description)
        tvTrophy?.setText("Trophy - " +item.reward.trophy)
        tvPoints?.setText("Points Earned - " +item.reward.points.toString())

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



}