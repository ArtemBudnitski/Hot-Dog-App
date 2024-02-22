package com.abudnitski.not_hot_dog.presentation

import com.abudnitski.not_hot_dog.domain.Label

class LabelUiMapper {
    fun map(data: List<Label>): List<UiLabel>{
        return data.take(4).map { UiLabel(text = it.text, textAndConfidence = formatLabelToTextAndConfidence(it)) }
    }
    private fun formatLabelToTextAndConfidence(label: Label): String {
        return "${label.text} = ${"%.3f".format(label.confidence * 100)}%"
    }
}