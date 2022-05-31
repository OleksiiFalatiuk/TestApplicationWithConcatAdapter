package com.example.testapplicationwithconcatadapter.data

import com.example.testapplicationwithconcatadapter.R
import com.example.testapplicationwithconcatadapter.ui.model.Featured
import com.example.testapplicationwithconcatadapter.ui.model.Item
import com.example.testapplicationwithconcatadapter.ui.model.Regular
import java.util.*

object Data{
    fun getItems(): List<Item>{
        return listOf(
            Featured(
                id = UUID.randomUUID().toString(),
                title = "Item 1 Featured",
                description = "Super exciting new design",
                drawId = R.drawable.picture1
            ),
            Featured(
                id = UUID.randomUUID().toString(),
                title = "Item 2 Featured",
                description = "Featured design is here",
                drawId = R.drawable.picture2
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 3",
                drawId = R.drawable.picture3
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 4",
                drawId = R.drawable.picture4
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 5",
                drawId = R.drawable.picture5
            )
        )
    }
}