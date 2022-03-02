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
        val screenNumber = 3

        val screens = JSONParser.parseJSON(applicationContext, "scenes")

        screens.forEach { screen ->
            if (screen.id == screenNumber) {
                binding.header.text = screen.header
                when (screen.arrayOfVariants.size) {
                    1 -> {
                        binding.firstButton.visibility = View.INVISIBLE
                        binding.secondButton.visibility = View.INVISIBLE
                        binding.thirdButton.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.firstButton.visibility = View.INVISIBLE
                        binding.secondButton.visibility = View.VISIBLE
                        binding.thirdButton.visibility = View.VISIBLE
                    }
                    3-> {
                        binding.firstButton.visibility = View.VISIBLE
                        binding.secondButton.visibility = View.VISIBLE
                        binding.thirdButton.visibility = View.VISIBLE
                    }
                }

                if (binding.thirdButton.visibility == View.VISIBLE) {
                    binding.thirdButton.setOnClickListener {
                        Toast.makeText(this, "THIRD", Toast.LENGTH_SHORT).show()
                    }
                }
                if (binding.secondButton.visibility == View.VISIBLE) {
                    binding.secondButton.setOnClickListener {
                        Toast.makeText(this, "SECOND", Toast.LENGTH_SHORT).show()
                    }
                }
                if (binding.firstButton.visibility == View.VISIBLE) {
                    binding.firstButton.setOnClickListener {
                        Toast.makeText(this, "FIRST", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

    }

}