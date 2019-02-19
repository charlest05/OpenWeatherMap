# OpenWeatherMap
OpenWeatherMap is an Android Application that fetches Weather data for a specific location using  https://openweathermap.org/api. The app was created using MVVM programming pattern.  

## Setup Instruction
- If you encounter build error regarding ApiKey, add the code snippet below inside your **gradle.properties** located in **/home/{user}/.gradle** see full [tutorial](https://medium.com/code-better/hiding-api-keys-from-your-android-repository-b23f5598b906)
```
OpenWeatherMapApiKey="{YourApiKeyJHere}"
```

## Libraries Used
- **Dagger 2** dependency injection library
- **EventBus** publisher/subscriber pattern for loose coupling of callbacks
- **RxJava2/RxAndroid** used for handling network requests with retrofit 2 
- **Retrofit 2** network calls and JSON parsing using **Gson** library
- **Glide** for effecient loading and offline persistence of images
- **Androidx** for replacement of support libraries
- **Navigation Architecture Component** for fragment management and navigation
- **Lifecycle  Architecture Component** for **ViewModel** and **LiveData** programming approach
- **Room Database** for offline persistence of Weather data
- **Google Play Service Location** for location tracking

## App Screens
- **Splash Screen**
    - When you launch the app it shows first the splash screen for 2 seconds, also it has different design for each build variant, such as Production, Development and Testing build variant
- **Main Screen**
    - The Screen shows the list of Weather information from London, Prague and San Francisco, each item contains location, actual weather and temperature. 
    - It also has refresh button for reloading the Weather information of the said locations
- **Weather Detail Screen**
    - The weather detail screen is shown when you select an item in main screen
    - It shows more data about the weather of the selected location
    - also, it has refresh button for reloading the Weather information of the selected location
   
   
