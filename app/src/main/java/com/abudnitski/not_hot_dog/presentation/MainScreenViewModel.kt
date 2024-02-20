package com.abudnitski.not_hot_dog.presentation

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

@HiltViewModel
@OptIn(ExperimentalGetImage::class)
class MainScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState(listOf(Label(text = "Wyszukaj cos", confidence = 0.0))))
    val uiState: StateFlow<MainScreenUiState> = _uiState

    fun analyzeImage(imageProxy: ImageProxy, stop: () -> Unit) {
        val imageLabeling = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        imageProxy.image?.let { image ->
            val img = InputImage.fromMediaImage(
                image,
                imageProxy.imageInfo.rotationDegrees
            )

            val process = imageLabeling.process(img)
            process.addOnSuccessListener { labels ->
                val data = mapImageLabels(labels)
                _uiState.value = _uiState.value.copy(list = data)
                stop()
            }
            process.addOnCompleteListener {
                imageProxy.close()
            }
        }
    }

    private fun map(data: ImageLabel): Label {
        return Label(text = data.text, confidence = data.confidence.toDouble())
    }

    private fun mapImageLabels(data: List<ImageLabel>): List<Label> {
        return data.map { map(it) }
    }


}