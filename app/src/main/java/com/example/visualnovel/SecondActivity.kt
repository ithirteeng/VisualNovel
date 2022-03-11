package com.example.visualnovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.visualnovel.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    companion object {
        const val NICKNAME = "nickname"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.secondScreenButton.setOnClickListener {
            if (binding.nicknameEditText.text.toString() == "") {
                Toast.makeText(this, this.getString(R.string.name_error), Toast.LENGTH_SHORT).show()
            } else {
                Storage().makeSavedPreference(this, NICKNAME, binding.nicknameEditText.text.toString())
                val secondIntent = Intent(this, SceneActivity::class.java)
                secondIntent.putExtra(SceneActivity.ID, 3)
                startActivity(secondIntent)
            }
        }

    }


}