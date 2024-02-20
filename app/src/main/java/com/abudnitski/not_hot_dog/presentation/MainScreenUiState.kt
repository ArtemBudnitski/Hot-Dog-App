package com.abudnitski.not_hot_dog.presentation

data class MainScreenUiState(
    val list: List<Label>
)

data class Label(
    val text: String,
    val confidence: Double
)
