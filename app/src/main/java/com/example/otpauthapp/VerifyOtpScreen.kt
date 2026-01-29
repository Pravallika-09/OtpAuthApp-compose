package com.example.otpauthapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun VerifyOtpScreen(
    email: String,
    expiryTime: Long,
    otpVersion: Int,
    onVerify: (String) -> Boolean,
    onResend: () -> Unit
) {
    var otp by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf("") }
    var timeLeft by rememberSaveable { mutableStateOf(0L) }

    // ⏱ Countdown logic (RESTARTS on resend)
    LaunchedEffect(expiryTime, otpVersion) {
        while (true) {
            val remaining = (expiryTime - System.currentTimeMillis()) / 1000
            if (remaining <= 0) {
                timeLeft = 0
                break
            }
            timeLeft = remaining
            delay(1000)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Verify OTP", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("OTP sent to $email")

        Spacer(Modifier.height(6.dp))
        Text(
            text = if (timeLeft > 0)
                "OTP expires in 00:${timeLeft.toString().padStart(2, '0')}"
            else
                "OTP expired",
            color = if (timeLeft > 0)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.error
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = {
                otp = it
                error = ""
            },
            label = { Text("Enter OTP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (error.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            enabled = otp.length == 6 && timeLeft > 0,
            onClick = {
                val success = onVerify(otp)
                if (!success) {
                    error = "Invalid or expired OTP"
                }
            }
        ) {
            Text("Verify")
        }

        Spacer(Modifier.height(12.dp))

        TextButton(
            enabled = timeLeft == 0L,
            onClick = {
                otp = ""
                error = ""
                timeLeft = 0
                onResend()
            }
        ) {
            Text("Resend OTP")
        }
    }
}
