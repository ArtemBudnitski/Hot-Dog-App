package com.abudnitski.not_hot_dog.ui.main

import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.abudnitski.not_hot_dog.R
import com.abudnitski.not_hot_dog.presentation.MainScreenViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.concurrent.Executors

@OptIn(ExperimentalGetImage::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<MainScreenViewModel>()
    val screenUiState = viewModel.uiState.collectAsState().value

    val context = LocalContext.current
    val previewView: PreviewView = remember { PreviewView(context) }
    val cameraController = remember { LifecycleCameraController(context) }
    val lifecycleOwner = LocalLifecycleOwner.current

    cameraController.bindToLifecycle(lifecycleOwner)
    cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    previewView.controller = cameraController
    val shutter = remember { Executors.newSingleThreadExecutor() }


    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AndroidView(
            factory = { previewView }, modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 12.dp, end = 12.dp)
                .align(Alignment.TopCenter)
                .clickable { viewModel.changeLabelText() },
            colors = if (screenUiState.itIsHotDog) {
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            } else {
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            },
            shape = MaterialTheme.shapes.extraLarge,

            ) {
            if (!screenUiState.labelText) {
                Row {
                    Spacer(modifier = modifier.weight(1f))
                    Text(
                        text = screenUiState.labels.first().text,
                        modifier
                            .wrapContentSize()
                            .padding(vertical = 12.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Spacer(modifier = modifier.weight(1f))
                }
            } else {
                screenUiState.labels.forEach {
                    Row {
                        Text(
                            text = it.textAndConfidence,
                            modifier
                                .wrapContentSize()
                                .padding(vertical = 12.dp)
                                .weight(1f),
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }

        Switch(
            checked = screenUiState.checked,
            onCheckedChange = {
                viewModel.changeChecked(it)
            },
            thumbContent = if (screenUiState.checked) {
                {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.hot_dog),
                        contentDescription = stringResource(R.string.hot_dog_mode),
                        modifier = Modifier
                            .size(SwitchDefaults.IconSize)
                            .wrapContentSize()
                            .padding(2.dp),
                        tint = Color.Unspecified
                    )
                }
            } else {
                {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.camera_with_flash),
                        contentDescription = stringResource(R.string.camera_mode),
                        modifier = Modifier
                            .size(SwitchDefaults.IconSize)
                            .wrapContentSize()
                            .padding(2.dp),
                        tint = Color.Unspecified
                    )
                }
            },
            modifier = modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter)
                .padding(bottom = 115.dp),
        )

        IconButton(
            onClick = {
                cameraController.setImageAnalysisAnalyzer(shutter) { imageProxy ->
                    viewModel.analyzeImage(imageProxy) {
                        cameraController.clearImageAnalysisAnalyzer()
                    }
                }
            },
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp)
                .size(72.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.shutter), contentDescription = stringResource(R.string.shutter),
                modifier
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .padding(4.dp)
            )
        }
    }
}
