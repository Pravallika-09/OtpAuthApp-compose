package com.example.otpauthapp

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.otpauthapp.viewmodel.AuthViewModel

@Composable
fun App() {
    val authViewModel: AuthViewModel = viewModel()

    var screen by rememberSaveable { mutableStateOf("login") }
    var email by rememberSaveable { mutableStateOf("") }
    var otpVersion by rememberSaveable { mutableStateOf(0) }

    when (screen) {
        "login" -> LoginScreen(
            onSendOtp = {
                email = it
                authViewModel.generateOtp(it)
                otpVersion++
                screen = "verify"
            }
        )

        "verify" -> VerifyOtpScreen(
            email = email,
            expiryTime = authViewModel.getOtpExpiry(email),
            otpVersion = otpVersion,
            onVerify = { otp ->
                val success = authViewModel.verifyOtp(email, otp)
                if (success) screen = "session"
                success
            },
            onResend = {
                authViewModel.generateOtp(email)
                otpVersion++
            }
        )

        "session" -> SessionScreen(
            authViewModel = authViewModel,
            email = email,
            onLogout = {
                authViewModel.logout()
                screen = "login"
            }
        )
    }
}
