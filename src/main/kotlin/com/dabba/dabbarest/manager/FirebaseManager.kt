package com.dabba.dabbarest.manager

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream


class FirebaseManager {
    val serviceAccount=FileInputStream("/Users/macbookair/Downloads/dabba-ru-firebase-adminsdk-bycsl-5f0094a7f5.json")

    val options= FirebaseOptions.builder().setCredentials(GoogleCredentials.getApplicationDefault())
            .setDatabaseUrl("https://dabba-ru.firebaseio.com")
            .build()

    val u=FirebaseApp.initializeApp(options)
}