## Faysal Hossain - Assessment

![SCREENSHOTS](https://github.com/fsfaysalcse/FaysalAssessment/blob/main/app_screenshots.png?raw=true)

[Demo APK](#Link)

### Project Overview

This project is an Android application that demonstrates the use of Clean Architecture principles.
The application is structured to separate concerns into different layers, making the codebase more
maintainable and testable.

### Clean Architecture

The project follows Clean Architecture, which divides the code into different layers:

- **Domain Layer**: Contains business logic and domain models.
- **Data Layer**: Handles data operations, including network requests and local database
  interactions.
- **Presentation Layer**: Manages UI and user interactions.

### Third-Party Libraries

The project utilizes several third-party libraries to enhance functionality and simplify
development:

- **Dagger Hilt**: Used for dependency injection to manage and provide dependencies across the
  application.
- **Retrofit**: A type-safe HTTP client for making network requests to the API.
- **Gson**: A library for converting JSON strings to Java/Kotlin objects and vice versa.
- **Kotlin Coroutines**: Used for asynchronous programming to handle background tasks.

### Why These Libraries?

- **Dagger Hilt**: Simplifies dependency injection, making the code more modular and easier to test.
- **Retrofit**: Provides a straightforward way to handle network operations, including making API
  calls and parsing responses.
- **Gson**: Efficiently handles JSON serialization and deserialization, which is essential for
  working with API responses.
- **Kotlin Coroutines**: Facilitates writing asynchronous code in a more readable and maintainable
  way, avoiding callback hell.

### How to Build and Run

1. Clone the repository:
   ```sh
   git clone https://github.com/fsfaysalcse/FaysalAssessment.git