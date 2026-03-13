# IntentMail – Android Email Dispatcher

A simple Android application that allows users to compose and send emails using any installed email client on their device.

---

## Overview

This app provides a clean, minimal interface for composing emails. It collects a recipient address, subject, and message body, then delegates the actual sending to any email app installed on the user's Android device (e.g., Gmail, Outlook, etc.) via an Android Intent.

---

## Features

- Input fields for **To**, **Subject**, and **Message**
- **Validation** — alerts the user if any field is left empty
- Launches the device's **email app chooser** so the user can pick their preferred client
- Handles the case where **no email app is installed** gracefully with a toast message

---

## Tech Stack

| Component        | Details                        |
|------------------|-------------------------------|
| Language         | Java                          |
| Min SDK          | Android (AppCompat)           |
| UI Theme         | Material Components (DayNight)|
| Email Mechanism  | `Intent.ACTION_SEND` (RFC 822)|

---

## Project Structure

```
com.example.emailapp/
├── MainActivity.java       # Main activity with email compose logic
└── res/
    └── layout/
        └── activity_main.xml   # UI layout (EditTexts + Button)
AndroidManifest.xml             # App configuration and activity declaration
```

---

## Getting Started

### Prerequisites

- Android Studio (latest recommended)
- Android SDK installed
- A physical device or emulator running Android

### Installation

1. **Clone or download** the project.
2. **Open** the project in Android Studio.
3. **Sync** Gradle dependencies.
4. **Run** the app on a device or emulator.

---

## How It Works

1. The user fills in the **recipient email address**, **subject**, and **message**.
2. On tapping **Send**, the app validates that no fields are empty.
3. An `Intent` of type `message/rfc822` is created and populated with the email data.
4. Android's **app chooser** opens, letting the user select an email client.
5. The chosen email app opens with all fields pre-filled — ready to send.

### Core Intent Setup

```java
Intent emailIntent = new Intent(Intent.ACTION_SEND);
emailIntent.setType("message/rfc822");
emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
emailIntent.putExtra(Intent.EXTRA_SUBJECT, subj);
emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
startActivity(Intent.createChooser(emailIntent, "Choose Email App"));
```

---

## Error Handling

| Scenario                     | Behavior                                      |
|------------------------------|-----------------------------------------------|
| Any field is empty           | Toast: `"Please fill all fields"`             |
| No email app installed       | Toast: `"No email app found"`                 |
| Email app selected and opened| Toast: `"Sent Mail Successfully"`             |

> **Note:** The "Sent Mail Successfully" toast fires when the email chooser opens, not when the email is actually sent — since the app hands off control to the external email client at that point.

---

## Manifest Highlights

- The app uses `Theme.MaterialComponents.DayNight.DarkActionBar` for Material Design support.
- `MainActivity` is set as the launcher activity via `android.intent.action.MAIN`.
- No special permissions are required since sending is delegated to an external email app.

---

## Possible Enhancements

- Add email format validation (e.g., check for `@` and `.`)
- Support multiple recipients (CC / BCC fields)
- Add attachment support
- Persist draft using `SharedPreferences` or a local database
- Add a dark/light mode toggle

---

## 📜 License

This project is open-source and available for personal and educational use.
