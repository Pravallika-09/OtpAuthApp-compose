# OTP Authentication App (Jetpack Compose)

## Overview
This Android application demonstrates a secure OTP-based authentication flow using **Jetpack Compose** and **MVVM architecture**.

The app supports OTP generation, validation, expiry handling, session tracking, and logout functionality.

---

## Features Implemented

### 1. Email / Phone + OTP Login
- User enters an email or phone number
- A **6-digit OTP** is generated locally
- OTP is validated before login

---

### 2. OTP Rules
- OTP length: **6 digits**
- OTP expiry: **60 seconds**
- Maximum attempts: **3**
- Generating a new OTP:
    - Invalidates previous OTP
    - Resets attempt count
- OTP data is stored **per email/phone**

---

### 3. Session Screen
After successful login:
- Displays **session start time**
- Shows **live session duration (mm:ss)**
- Provides a **Logout** button

Session timer:
- Survives recompositions
- Stops immediately on logout

---

### 4. External SDK Integration
**Timber** is used for logging.

Logged events:
- OTP generated
- OTP validation success
- OTP validation failure
- Logout

---

## Architecture & Technical Details

### Jetpack Compose
- `@Composable` functions for UI
- `remember` and `rememberSaveable` for state
- `LaunchedEffect` for session timer
- State hoisting between screens

---

### ViewModel
- `AuthViewModel` manages all business logic
- Uses `StateFlow` for session state
- UI observes state in a one-way data flow

---

### Data Structures Used
- `MutableMap<String, OtpData>`  
  → Stores OTP per email/phone
- `StateFlow<Long?>`  
  → Tracks session start time

---

## Edge Cases Handled
- Expired OTP
- Incorrect OTP
- Exceeded OTP attempts
- OTP resend
- Screen rotation without breaking state

---

## AI Usage Declaration
- Used GPT to:
    - Understand OTP logic design
    - Improve Compose best practices
- All code was:
    - Written
    - Tested
    - Understood
    - Modified manually by me

---

## How to Run
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Run on emulator or physical device

---

## Author
Built as part of an Android authentication assignment using Jetpack Compose.
