package com.example.otpauthapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.otpauthapp.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SessionScreen(
    authViewModel: AuthViewModel,
    email: String,
    onLogout: () -> Unit
) {
    val startTime by authViewModel.sessionStartTime.collectAsState()

    var elapsed by remember { mutableStateOf(0L) }

    LaunchedEffect(startTime) {
        while (startTime != null) {
            elapsed = (System.currentTimeMillis() - startTime!!) / 1000
            delay(1000)
        }
    }

    val minutes = elapsed / 60
    val seconds = elapsed % 60

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Session Active", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("Email: $email")
        Spacer(Modifier.height(8.dp))
        Text(String.format("Duration: %02d:%02d", minutes, seconds))
        Spacer(Modifier.height(24.dp))
        Button(onClick = onLogout) {
            Text("Logout")
        }
    }
}
