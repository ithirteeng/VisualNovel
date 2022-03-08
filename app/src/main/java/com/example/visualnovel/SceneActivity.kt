package com.example.visualnovel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.visualnovel.databinding.ActivitySceneBinding

class SceneActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySceneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySceneBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)





    }

}