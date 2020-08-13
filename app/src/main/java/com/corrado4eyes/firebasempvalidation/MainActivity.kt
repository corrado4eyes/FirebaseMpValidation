package com.corrado4eyes.firebasempvalidation

import FirestoreRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import createScreenMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var firestoreRepository: FirestoreRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestoreRepository = FirestoreRepository(applicationContext)

        Log.d(TAG, createScreenMessage())

        MainScope().launch {
            val data = firestoreRepository.getDocument("meetings/meeting")
            Log.d(TAG, data.toString())
        }

    }
}