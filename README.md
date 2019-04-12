Introduction
----------------------------------
This sample app is constructed with keeping in mind of Reliability, Scalability, and Maintainability.
For the same, I have used latest android architecture components, Kotlin language.


Functionality

The app is composed of 2 main screens.
1) A main screen to display goals as a list.
2) A detail screen to display goal progress and other interesting information.

 App has been integrated with the Google Fit using the Google Fit SDK to track the user’s physical activity. It display below three activities at dashboard
 1. Number of steps
 2. Calories Burn
 3. Distance Covered

PLEASE NOTE : Details screen display totally number of steps in a day against goal count set for any of the Goal category. it means that there
will not be any logic to figure out Medium walk, Easy walk, walk some distance, quick run and so on.

Please note that, App needs internet connection for the first time to take user authentication and also to fetch server content.


Programming Practices Followed
-----------------------------------------
a) Android Architectural Components (Lifecycle, LiveData, ViewModel, Room Persistence)
b) Koin for Dependency Injection
        A pragmatic lightweight dependency injection framework for Kotlin developers: no proxy, no code generation, no reflection!
        For more information - https://insert-koin.io/
c) MVVM
d) Retrofit with Okhttp
e) Room for data caching
f) JUnit and Mockito for Unit testing
g) Stetho as debug bridge


The application has been built with offline support. It has been designed using Android Architecture components with Room
for offline data caching. The application is built in such a way that whenever there is a service call, the result will be stored in
local database.


How to build ?
------------------------------------------
Open terminal and type the below command to generate debug build
./gradlew assembleDebug
Open terminal and type the below command to generate release build
./gradlew assembleRelease