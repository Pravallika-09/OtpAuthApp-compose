package com.example.otpauthapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

data class OtpData(
    val otp: String,
    val expiryTime: Long,
    var attemptsLeft: Int
)

class AuthViewModel : ViewModel() {

    private val otpStore = mutableMapOf<String, OtpData>()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _sessionStartTime = MutableStateFlow<Long?>(null)
    val sessionStartTime: StateFlow<Long?> = _sessionStartTime

    fun generateOtp(email: String): String {
        val otp = (100000..999999).random().toString()
        val expiry = System.currentTimeMillis() + 60_000

        otpStore[email] = OtpData(
            otp = otp,
            expiryTime = expiry,
            attemptsLeft = 3
        )

        Timber.d("OTP generated for $email : $otp")
        return otp
    }

    fun verifyOtp(email: String, inputOtp: String): Boolean {
        val data = otpStore[email] ?: return false

        if (System.currentTimeMillis() > data.expiryTime) {
            Timber.d("OTP expired for $email")
            otpStore.remove(email)
            return false
        }

        if (data.attemptsLeft <= 0) {
            Timber.d("OTP attempts exceeded for $email")
            otpStore.remove(email)
            return false
        }

        if (data.otp == inputOtp) {
            Timber.d("OTP validation success for $email")
            _isLoggedIn.value = true
            _sessionStartTime.value = System.currentTimeMillis()
            otpStore.remove(email)
            return true
        } else {
            data.attemptsLeft--
            Timber.d("OTP validation failed for $email. Attempts left=${data.attemptsLeft}")
            return false
        }
    }

    fun logout() {
        Timber.d("User logged out")
        _isLoggedIn.value = false
        _sessionStartTime.value = null
    }
    fun getOtpExpiry(email: String): Long {
        return otpStore[email]?.expiryTime ?: 0L
    }

}
