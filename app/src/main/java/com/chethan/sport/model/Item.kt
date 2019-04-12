package com.chethan.sport.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Chethan on 4/3/2019.
 */


@Entity
data class Item(
    @PrimaryKey
    @field:SerializedName("id")
    var id: Int? = 0,
    @field:SerializedName("title")
    var title: String? = "",
    @field:SerializedName("description")
    var description: String? = "",
    @field:SerializedName("type")
    var type: String? = "",
    @field:SerializedName("goal")
    var goal: String? = "",
    @field:Embedded // embedded tag to store object as nested columns in Room Database
    var reward: Reward,
    @field:SerializedName("itemId")
    var itemId: Int? = 0

) : Serializable