# Quizzie: A Quiz App

## Overview
Quizzie is an Android application designed for users who have a keen interest in attempting quizzes to foster their knowledge. It features an impressive UI and a proper authentication system for registered users. The app allows users to attempt quizzes posted on specific dates by selecting them via a calendar.

## Features and Functionalities

### User Accounts
- **Signup and Login:** Users can create new accounts or log in to existing ones using Firebase Firestore Authentication.

### Quiz Management
- **Date Selection:** Users can choose the date for specific quizzes posted on selected dates using a date picker.
- **Quiz Display:** Quizzes are displayed using RecyclerView. The mainActivity sets up the quiz layout, and the questionActivity sets up the options layout.
- **Result Evaluation:** The resultActivity evaluates and shows the total score obtained in the attempted quiz.

### Navigation
- **Navigation Drawer:** Implemented using navigationView and materialToolbar for easy navigation within the app.

## Technologies Used

### Frontend
- **Kotlin:** The app is fully developed using Kotlin, providing modern language features and improved performance.
- **XML:** Used for designing the user interface.
- **Android Material Design:** Used for selecting specific layout designs for the application.

### Backend
- **Firebase Firestore:** For authentication and storing quiz data updated datewise.
- **Google JSON Library:** For parsing JSON data from the Cloudstore database.

### Implementations/Usages
- **View Binding:** For binding functionality with layout resources in XML files.
- **RecyclerView:** For displaying the list of quizzes and options.
- **DatePicker:** For choosing dates for specific quizzes.
- **Navigation Drawer:** For implementing the navigation drawer in the application layout.

