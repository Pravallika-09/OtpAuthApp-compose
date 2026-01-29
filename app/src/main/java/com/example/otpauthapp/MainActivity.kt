package com.example.otpauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.otpauthapp.App
import com.example.otpauthapp.ui.theme.OtpAuthAppTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        setContent {
            OtpAuthAppTheme {
                App()
            }
        }
    }
}