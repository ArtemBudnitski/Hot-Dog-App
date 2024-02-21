package com.abudnitski.not_hot_dog.domain

import com.abudnitski.not_hot_dog.presentation.Label
import com.google.mlkit.vision.label.ImageLabel

class LabelMapper {
    private fun map(data: ImageLabel): Label {
        return Label(text = data.text, confidence = data.confidence.toDouble())
    }

    fun mapImageLabels(data: List<ImageLabel>): List<Label> {
        return data.map { map(it) }
    }
}