package com.handoyo.ojekonlineapi

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Component

@Component
class DatabaseComponent {

    companion object {
        private const val DATABASE_URL = "mongodb+srv://handoyo:1234@cluster0.mdd9y.mongodb.net/?retryWrites=true&w=majority"
    }

    val database: MongoClient = KMongo.createClient(DATABASE_URL)
}