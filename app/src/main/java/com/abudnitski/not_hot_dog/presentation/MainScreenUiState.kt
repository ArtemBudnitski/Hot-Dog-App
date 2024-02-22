package com.abudnitski.not_hot_dog.presentation

data class MainScreenUiState(
    val labels: List<UiLabel>,
    val itIsHotDog : Boolean = true,
    var checked : Boolean = true,
    var labelText : Boolean = false
)

data class UiLabel(
    val text: String,
    val textAndConfidence: String,
)
