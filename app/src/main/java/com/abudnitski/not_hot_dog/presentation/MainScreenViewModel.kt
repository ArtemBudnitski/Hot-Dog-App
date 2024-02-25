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
class MainScreenViewModel @Inject constructor(private val labelMapper: LabelMapper, private val labelUiMapper: LabelUiMapper) :
    ViewModel() {
    private val _uiState =
        MutableStateFlow(MainScreenUiState(listOf(UiLabel(text = "Scan something!", textAndConfidence = "Scan something!"))))
    val uiState: StateFlow<MainScreenUiState> = _uiState

    fun analyzeImage(imageProxy: ImageProxy, stop: () -> Unit) {
        val imageLabeling = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        imageProxy.image?.let { image ->
            _uiState.value = _uiState.value.copy(isDataLoading = true)
            val img = InputImage.fromMediaImage(
                image,
                imageProxy.imageInfo.rotationDegrees
            )

            val process = imageLabeling.process(img)
            process.addOnSuccessListener { labels ->
                val data = labelMapper.mapImageLabels(labels)
                if (_uiState.value.checked) {
                    if (data.any {
                            it.text == HOT_DOG
                        }) {
                        _uiState.value = _uiState.value.copy(
                            labels = listOf(
                                UiLabel("✅ Hot dog!", "✅ Hot dog! = 99%"),
                                UiLabel("❌ Not hot dog!", "❌ Not hot dog! = 1%")
                            ), itIsHotDog = true, isDataLoading = false
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            labels = listOf(
                                UiLabel("❌Not hot dog!", "❌Not hot dog! = 99%"),
                                UiLabel("✅ Hot dog!", "✅ Hot dog! = 1%")
                            ), itIsHotDog = false, isDataLoading = false
                        )
                    }
                } else {
                    _uiState.value =
                        _uiState.value.copy(labels = labelUiMapper.map(data), itIsHotDog = true, isDataLoading = false)
                }
                stop()
            }

            process.addOnCompleteListener {
                imageProxy.close()
                _uiState.value = _uiState.value.copy(isDataLoading = false)
            }
        }
    }


    fun changeChecked(checked: Boolean) {
        _uiState.value = uiState.value.copy(
            checked = checked,
            labels = listOf(UiLabel(text = "Scan something!", textAndConfidence = "Scan something!")),
            itIsHotDog = true
        )

    }

    fun changeLabelText() {
        _uiState.value = _uiState.value.copy(labelText = !_uiState.value.labelText)
    }

    private companion object {
        const val HOT_DOG = "Hot dog"
    }
}
