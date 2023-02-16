# VenuesAppDemo

VenuesAppDemo is aplication for show venues around predefined marker position. Venues can be sorted based on the favorite option. Data are fetched from the API and stored locally for display for the user.


Main Screen             |  Available Venues             |  Fetching Venues          |  Delete Local Venues
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
![](https://i.ibb.co/3SsT216/Main-Screen.jpg")|![](https://i.ibb.co/YXHkrvQ/Available-Venues.jpg)|![](https://i.ibb.co/f1yLP9z/Fetching-Venues.jpg) |![](https://i.ibb.co/znzzV72/Delete-Local-Venues.jpg)



 ### Functionality
 - User can click on predefined marker
 - List of venues will be shown for selected marker 
 - User can add venue as favorite
 - User can sort venues based on favorite
 - If there is same venue shown for multiple markers, venue favorite state will be visible for all venue appereances
 - Venues can be deleted from local db
 - Venues can be fetched back from the API

## Used
- Kotlin
- Jetpack Composable
- API
- Room Database
- Retrofit
- Google Maps
- Coil
- Dagger Hilt
- Coroutines
- Clean Architecture, MVVM, UseCases, ViewModels

## Support Links
- https://developer.android.com/training/data-storage/room
- https://dagger.dev/hilt/
- https://square.github.io/retrofit/


## Libraries used
- https://coil-kt.github.io/coil/ - Asynchronous image library

## Dependencies
Compose dependencies

```bash
    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-alpha02"
    implementation "androidx.navigation:navigation-compose:2.5.0-alpha02"
    implementation "androidx.compose.material:material-icons-extended:$compose_version"
    implementation "androidx.hilt:hilt-navigation-compose:1.0.0"
```

Material components

```bash
    implementation 'com.google.android.material:material:1.5.0'
```

Coroutines

```bash
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
```

Dagger - Hilt

```bash
    implementation "com.google.dagger:hilt-android:2.44.2"
    kapt "com.google.dagger:hilt-android-compiler:2.44.2"
    implementation 'androidx.hilt:hilt-navigation-compose:1.1.0-alpha01'
    implementation 'androidx.hilt:hilt-navigation-fragment:1.0.0'
    kapt 'androidx.hilt:hilt-compiler:1.0.0'
```
Room

```bash
    implementation "androidx.room:room-runtime:2.5.0"
    kapt "androidx.room:room-compiler:2.5.0"
```
Kotlin Extensions and Coroutines support for Room

```bash
    implementation "androidx.room:room-ktx:2.5.0"
```
Retrofit

```bash
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation "com.squareup.okhttp3:okhttp:5.0.0-alpha.2"
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
```
Google Maps

```bash
    implementation 'com.google.maps.android:maps-compose:2.8.0'
    implementation 'com.google.android.gms:play-services-maps:18.1.0'
```
Coil

```bash
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")
```

Plugins (Project)

```bash
plugins {
        id("com.google.dagger.hilt.android") version "2.44.2" apply false
}
```

Plugins (App)

```bash
plugins {
    id 'kotlin-kapt'
    id("com.google.dagger.hilt.android")
}
```
