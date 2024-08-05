# Reverse-Polish-Notation

## Solution
I used a stack to solve for Reverse Polish Notation (RPN) equations. A user can keep stacking numbers on to the stack till it hits a math operator (+, - *, /). When it hits a math operator it will pop off the last two numbers from the stack then set that as the top value. This new top number will be displayed to the user. If there is more than 2 numbers on the stack the user can keep adding math operators or adding numbers. Since Kotlin does not have a stack class, I chose to use a ArrayDeque. 

## Architect
I used the Model-View-ViewModel (MVVM) pattern to build the app. It is a good architect pattern to keep the view and data seperate via the ViewModel. This allows for better reuse, easier testing, and easier scaling the app. I accomplish this pattern by using LiveData and the ViewModel class. The View (Fragment) will observe any changes in the LiveData and then update the view accordingly. The ViewModel will reach out to the PolishNotationUtil (Model) for new data.

In addition I used one Activity and just changed out the fragments using Jetpack Navigation. This makes it simpler for navigation. Also went with view binding to limit boiler plater code due to not having to use findViewById for each view.

The code is mixed with Java/Kotlin just because I wanted to show that I know both Java and Kotlin. 

## With More Time

1. More testing and write some unit testing
2. Add some animation maybe like a blicking cursor or typing when its getting the stack. If I did would like a setting to turn it off
3. Add a light/dark mode.
4. Add a way to theme the app to like green text for example
5. New Launch Icon
6. If released to the store I would have some language translations. 

## How to run code?
2 Ways:
1. Download the apk below
2. Clone the repo and run the code in Android Studio

## Link to APK

Find the apk here at [Google Drive](https://drive.google.com/drive/folders/1F2vgGQ5BxB5hyoSvdr28gBCsMQRbKf4i?usp=sharing).
