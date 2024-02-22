package com.abudnitski.not_hot_dog

import com.abudnitski.not_hot_dog.domain.Label
import com.abudnitski.not_hot_dog.domain.LabelMapper
import com.google.mlkit.vision.label.ImageLabel
import org.junit.Test

import org.junit.Assert.*

class LabelMapperTest {
    private val sut = LabelMapper()

    @Test
    fun `map from list of Image labels to list of labels`() {
        val data: List<ImageLabel> =
            listOf(
                ImageLabel("hot dog", 0.1F, 1),
                ImageLabel("pizza", 0.02F, 2),
                ImageLabel("food", 0.003F, 3)
            )
        val result = sut.mapImageLabels(data)

        val expected: List<Label> = listOf(
            Label("hot dog", (0.1F).toDouble()), Label("pizza", (0.02F).toDouble()),
            Label("food", (0.003F).toDouble())
        )

        assertEquals(expected, result)
    }
}
