Introduction
----------------------------------
This sample application is built to demonstrate offline support using MVVM (Model-View-ViewModel), It uses Google's new Architecture components ViewModel, LiveData, LifecycleObserver, etc. It has been designed using with Room persistent library for offline data caching. 

Basically app fetches the information from the server and saves them in Database locally when it is opened for the first time. When the user opens it next time, the app loads app information from the Database first and then makes a network request in the background, once the network request is complete, the information on the screen are swapped with the fresh information of the server. Doing this gives the app a snappy user experience.

In the app there are two sources of data :

- a Local Database(implemented through Room)
- a Server(implemented through Retrofit)

So the results(data) that come out of the Database are very quick because the Database is locally available on your device. But the problem with having the results purely in the local Database is that they tend to be stale. The newest form of data is available from the Server. So typically what we also want to do is to execute a network request and that network request, typically network requests take a longer time to complete, but once it completes we want to take those results and swap it out with the Local Database results. Doing this gives our app a snapy experience. So the user opens the app and immediately sees the results from the local database and the network request happens in the background and as the network request completes we swap the results. So if we want to do it in rxjava, how do we do it.

What are the Functionality of the app and what are we trying to do ? 
--------------------------------------

This demo sample is Sport app, it displays the daily goals in your app and integrate with the Google Fit using the Google
Fit SDK to track the user’s physical activity.

In this app, you will retrieve a list of daily goals from an endpoint: GET
https://thebigachallenge.appspot.com/_ah/api/myApi/v1/goals

The app is composed of 2 main screens.
1) A main screen to display goals as a list.
2) A detail screen to display goal progress and other interesting information.

App has been integrated with the Google Fit using the Google Fit SDK to track the user’s physical activity. It display below three activities at dashboard
 1. Number of steps
 2. Calories Burn
 3. Distance Covered


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
h) Used JetPack software components.
