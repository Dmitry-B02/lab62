package com.example.image

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.image.databinding.ActivityMainBinding
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.Future


class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var download: Button
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        download = findViewById(R.id.download)
        download.setOnClickListener {
            if (binding.image.drawable == null) {
                viewModel.future = viewModel.loadImage((applicationContext as App).executor)
            }

            viewModel.bitmapData.observe(this) { value ->
                if (value != null) {
                    binding.image.setImageBitmap(value)
                }
            }
            download.visibility = View.GONE
        }
    }
}