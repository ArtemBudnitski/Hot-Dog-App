package com.abudnitski.not_hot_dog.ui.cameraPermissionScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abudnitski.not_hot_dog.R
import com.example.compose.AppTheme

@Composable
fun CameraPermissionRequestScreen(modifier: Modifier = Modifier, onSettingsClick: () -> Unit) {
    Box(modifier.fillMaxSize()) {
        Card(
            modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
                .padding(horizontal = 12.dp, vertical = 12.dp)
        )
        {
            Column(modifier.padding(horizontal = 4.dp, vertical = 4.dp)) {
                Row {
                    Spacer(modifier = modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.hot_dog),
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier.padding(top = 12.dp)
                    )
                    Spacer(modifier = modifier.weight(1f))
                }
                Text(
                    text = stringResource(R.string.the_app_needs_access_to_your_camera_to_work),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center
                )
            }
        }

        ElevatedButton(
            onClick = {
                onSettingsClick()
            },
            modifier
                .align(Alignment.Center)
                .wrapContentSize()
        ) {
            Text(text = stringResource(R.string.click_here), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Image(
            painter = painterResource(id = R.drawable.cursor_click_svgrepo_com),
            contentDescription = "",
            modifier
                .align(Alignment.Center)
                .padding(start = 150.dp, top = 60.dp)
                .size(50.dp)
        )

        Card(
            modifier
                .align(Alignment.BottomCenter)
                .padding(start = 12.dp, end = 12.dp, bottom = 120.dp)
        ) {
            Column(modifier.padding(horizontal = 4.dp, vertical = 4.dp)) {
                Row {
                    Spacer(modifier = modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.or), fontSize = 50.sp, fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = modifier.weight(1f))
                }
                Text(
                    text = stringResource(R.string.go_to_the_settings_than_choose_not_hot_dog_app_permissions_and_grant_a_camera_permission),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CameraPermissionRequestScreenPreview() {
    AppTheme {
        CameraPermissionRequestScreen(onSettingsClick = {})
    }
}