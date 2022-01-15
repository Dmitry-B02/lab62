package com.example.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.CancellationException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class ViewModel : ViewModel() {
    val bitmapData = MutableLiveData<Bitmap>()
    lateinit var future: Future<*>

    fun loadImage(executorService: ExecutorService): Future<*> {
        return executorService.submit {
            val newurl = URL("https://i.ibb.co/LCGjry8/Screenshot-20211121-231052-Gallery.jpg")
            val stream = newurl.openConnection().getInputStream()
            stream.use {
                val mIcon_val = BitmapFactory.decodeStream(stream)
                bitmapData.postValue(mIcon_val)
            }
        }
    }

    override fun onCleared() {
        future.cancel(true)
        super.onCleared()
    }
}