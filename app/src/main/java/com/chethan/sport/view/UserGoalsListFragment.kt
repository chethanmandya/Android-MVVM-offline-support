package com.chethan.sport.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chethan.sport.R
import com.chethan.sport.UserGoalsViewModel
import com.chethan.sport.model.Item
import com.chethan.sport.viewmodel.FitnessDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserGoalsListFragment : Fragment() {

    private val userGoalsListModel: UserGoalsViewModel by viewModel()
    private val fitnessDataViewModel: FitnessDataViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_goal_list, container, false)
    }


    override fun onStart() {
        super.onStart()

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)

        userGoalsListModel.result.observe(viewLifecycleOwner, Observer { result ->
            var data: List<Item> = listOf()
            if (result.data != null)
                data = result.data

            var userGoalsListAdapter: UserGoalsListAdapter =
                UserGoalsListAdapter(viewLifecycleOwner, data, fitnessDataViewModel)
            recyclerView.adapter = userGoalsListAdapter
            userGoalsListAdapter.setItemClickListener(object : UserGoalsListAdapter.ItemClickListener {
                override fun onItemClick(item: Item) {

                    //set the message to share to another fragment
                    val newFragment = UserGoalDetailFragment.newInstance(item)
                    val transaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frag_container, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()


                }
            })
        })


    }


}
