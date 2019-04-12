package com.chethan.sport.model

import java.io.Serializable

/**
 * Created by Chethan on 4/4/2019.
 */
data class ItemList(
    var items: List<Item>,
    var nextPageToken : String
) : Serializable
