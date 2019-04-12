package com.chethan.sport.model

import java.io.Serializable

/**
 * Created by Chethan on 4/3/2019.
 */

data class Reward (
    var trophy: String,
    var points: Int
) : Serializable
