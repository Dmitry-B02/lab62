package com.example.image

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.image.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import java.net.URL


class MainActivity: AppCompatActivity() {
    private lateinit var executor: ExecutorService
    private lateinit var binding: ActivityMainBinding
    private val bitmapData = MutableLiveData<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (binding.image.drawable == null) {
            loadImage()
        }

        bitmapData.observe(this) { value ->
            if (value != null) {
                binding.image.setImageBitmap(value)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }

    private fun loadImage() {
        val app = App()
        executor = app.executor
        executor.execute {
            val newurl = URL("https://i.ibb.co/LCGjry8/Screenshot-20211121-231052-Gallery.jpg")
            val stream = newurl.openConnection().getInputStream()
            stream.use {
                val mIcon_val = BitmapFactory.decodeStream(stream)
                bitmapData.postValue(mIcon_val)
            }
        }
    }
}