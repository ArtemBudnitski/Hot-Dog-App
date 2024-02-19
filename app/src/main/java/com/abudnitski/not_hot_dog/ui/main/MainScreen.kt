package com.abudnitski.not_hot_dog.ui.main

import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.abudnitski.not_hot_dog.R
import com.abudnitski.not_hot_dog.presentation.MainScreenViewModel
import com.example.compose.AppTheme
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Text", color: Int = 0
) {
    val viewModel = hiltViewModel<MainScreenViewModel>()
    val screenUiState = viewModel.uiState.collectAsState().value

    val context = LocalContext.current
    val previewView: PreviewView = remember { PreviewView(context) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
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
                .align(Alignment.TopCenter),
            colors = if (color == 0) {
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            } else {
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            },
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Row {
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = text,
                    modifier
                        .wrapContentSize()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = modifier.weight(1f))
            }
        }
        IconButton(
            onClick = onClick,
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp)
                .size(72.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.shutter), contentDescription = "shutter",
                modifier
                    .background(Color.White)
                    .padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    AppTheme {
        MainScreen()
    }
}