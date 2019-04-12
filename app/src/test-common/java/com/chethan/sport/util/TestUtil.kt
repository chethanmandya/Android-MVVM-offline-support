package com.chethan.sport.util

import com.chethan.sport.model.Item
import com.chethan.sport.model.ItemList
import com.chethan.sport.model.Reward

object TestUtil {

    fun createGoalObject(
        items: List<Item>,
        nextPageToken: String
    ): ItemList {
       return ItemList(
            items = items,
            nextPageToken = nextPageToken
        )
    }


    fun createItemArrayList(count : Int, item : Item): List<Item> {
        val list: ArrayList<Item> = arrayListOf<Item>()
        for (i in 0 until count) {
            val messageId : Int = 1000 + i
            list.add(createItem(messageId,
                item.title!!, item.description!!, item.type!!, item.goal!!, item.reward.trophy, item.reward.points ))
        }

        return list

    }


    fun createItem(
        id: Int,
        title: String,
        description: String,
        type: String,
        goal: String,
        trophy: String,
        points: Int
    ) = Item(
        id = id,
        title = title,
        description = description,
        type = type,
        goal = goal,
        reward = Reward(trophy, points),
        itemId = 3
    )


}
