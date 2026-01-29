


## 🔑 OTP Logic & Data Handling

- OTPs are stored in a `MutableMap<String, OtpData>`
- Key: Email
- Value:
  - OTP value
  - Expiry timestamp
  - Remaining attempts

### Why this approach?
- Supports multiple users
- Easy OTP invalidation
- Clear separation of concerns
- Efficient lookup and validation

---

## ⏱️ Countdown & State Management

- Countdown implemented using:
  - `LaunchedEffect`
  - `rememberSaveable`
- Timer survives:
  - Recomposition
  - Screen rotation
- Timer stops correctly on:
  - OTP expiry
  - Logout

---

## ⚠️ Edge Cases Handled

- Incorrect OTP
- Expired OTP
- Maximum attempts exceeded
- Resend OTP flow
- Screen rotation safety
- Logout resets session state

---

## 🤖 AI Usage Declaration

This project allows AI assistance.

**Used ChatGPT for:**
- Understanding OTP flow design
- Compose best practices
- Debugging and architecture guidance

**What I implemented and understood myself:**
- OTP generation & validation logic
- Countdown timer
- ViewModel and state handling
- UI implementation
- Error handling
- GitHub setup and push

All code was:
- Written
- Tested
- Understood
- Modified manually

---

## ▶️ How to Run

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Run on emulator or physical device

---

## 👩‍💻 Author

Built as part of an **Android Authentication Assignment**  
using **Jetpack Compose and MVVM architecture**.
