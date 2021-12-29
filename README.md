# WeatherAppViktor

//Libs Used


   //Image Processing
    implementation 'jp.wasabeef:glide-transformations:4.3.0'
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    
     //Network
   def retrofit = "2.9.0"
   implementation "com.squareup.retrofit2:retrofit:$retrofit"
   implementation "com.squareup.retrofit2:converter-gson:$retrofit"
   implementation "com.google.code.gson:gson:2.8.6"
   implementation "com.squareup.okhttp3:logging-interceptor:4.9.0"
   
   
   //DI Framework
    implementation "com.google.dagger:hilt-android:2.38.1"
    kapt "com.google.dagger:hilt-android-compiler:2.38.1"
    
    //Test Libraires
    testImplementation 'junit:junit:4.12'
    testImplementation "org.mockito:mockito-core:3.9.0"
    testImplementation "org.mockito:mockito-inline:2.13.0"
    testImplementation "org.mockito.kotlin:mockito-kotlin:3.2.0"
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"
    testImplementation 'app.cash.turbine:turbine:0.7.0'

    
    
    
