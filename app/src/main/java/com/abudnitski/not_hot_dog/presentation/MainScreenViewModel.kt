package com.abudnitski.not_hot_dog.presentation

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import com.abudnitski.not_hot_dog.domain.LabelMapper
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions


@HiltViewModel
@OptIn(ExperimentalGetImage::class)
class MainScreenViewModel @Inject constructor(private val labelMapper: LabelMapper) : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState(listOf(Label(text = "Scan something!", confidence = 0.1))))
    val uiState: StateFlow<MainScreenUiState> = _uiState

    fun analyzeImage(imageProxy: ImageProxy, checked: Boolean, stop: () -> Unit) {
        val imageLabeling = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
        val hotDog = "Hot dog"

        imageProxy.image?.let { image ->
            val img = InputImage.fromMediaImage(
                image,
                imageProxy.imageInfo.rotationDegrees
            )

            val process = imageLabeling.process(img)
            process.addOnSuccessListener { labels ->
                val data = labelMapper.mapImageLabels(labels)
                if (checked) {
                    if (data.any {
                            it.text == hotDog
                        }) {
                        _uiState.value = _uiState.value.copy(
                            list = listOf(
                                Label("It's Hot dog!", 0.9999999),
                                Label("It's not a Hot dog!", 0.0001111)
                            ), color = true
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            list = listOf(
                                Label("It's not a Hot dog!", 0.999999),
                                Label("It's Hot dog!", 0.0001111)
                            ), color = false
                        )
                    }
                } else {
                    _uiState.value = _uiState.value.copy(list = data, color = true)
                }
                stop()
            }

            process.addOnCompleteListener {
                imageProxy.close()
            }
        }
    }
}
