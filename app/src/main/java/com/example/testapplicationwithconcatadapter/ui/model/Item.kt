package com.example.testapplicationwithconcatadapter.ui.model

import androidx.annotation.DrawableRes

sealed class Item(
    open val id: String,
    open val title: String,
    @DrawableRes open val drawId: Int
)

data class Featured(
    override val id: String,
    override val title: String,
    override val drawId: Int,
    val description: String
): Item(
    id = id,
    title = title,
    drawId = drawId
)

data class Regular(
    override val id: String,
    override val title: String,
    override val drawId: Int
): Item(
    id = id,
    title = title,
    drawId = drawId
)