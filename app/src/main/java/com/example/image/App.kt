package com.example.image

import android.app.Application
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService

class App: Application() {
    val executor: ExecutorService = Executors.newSingleThreadExecutor()
}