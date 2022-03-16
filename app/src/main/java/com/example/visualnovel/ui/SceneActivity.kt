@file:Suppress("NAME_SHADOWING")

package com.example.visualnovel.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.visualnovel.*
import com.example.visualnovel.model.Constants
import com.example.visualnovel.model.Storage
import com.example.visualnovel.tools.JSONParser
import com.example.visualnovel.databinding.ActivitySceneBinding

class SceneActivity : AppCompatActivity() {

    companion object {
        const val ID = "intent_id"
    }

    private lateinit var binding: ActivitySceneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySceneBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentScreenId = intent.getIntExtra(ID, 0)
        val storage = Storage()

        val sceneButtonGroup = arrayOf(R.id.firstButton, R.id.secondButton, R.id.thirdButton)

        val listOfScreens: List<Screen> = JSONParser.parseJSON(this, Constants.JSON_SEPARATOR)

        for (screen in listOfScreens) {
            if (screen.id == currentScreenId) {
                if (currentScreenId == 3) {
                    screen.header = screen.header.replace(
                        "/NAME",
                        storage.takeSharedPreference(this, SecondActivity.NICKNAME).toString()
                    )
                }

                if (currentScreenId == 14) {
                    binding.sceneHeader.setTextAppearance(R.style.firstAndLastHeaderStyle)
                    binding.sceneHeader.setBackgroundColor(
                        resources.getColor(
                            R.color.firstMainColor,
                            this.theme
                        )
                    )
                    binding.root.findViewById<Button>(sceneButtonGroup[0])
                        .setBackgroundColor(resources.getColor(R.color.secondMainColor, this.theme))
                }

                for (currentButton in sceneButtonGroup) {
                    val button = binding.root.findViewById<Button>(currentButton)
                    makeButtonInvisible(button)
                }

                binding.sceneHeader.text = screen.header
                binding.sceneBackgroundImage.setImageResource(
                    resources.getIdentifier(
                        screen.background,
                        "drawable",
                        packageName
                    )
                )

                for ((counter, currentButton) in sceneButtonGroup.withIndex()) {
                    val size = screen.arrayOfVariants.size
                    if (counter == size) {
                        break
                    } else {
                        val button = binding.root.findViewById<Button>(currentButton)
                        makeButtonVisible(button)
                        button.text = screen.arrayOfVariants[size - counter - 1].variantText
                    }
                }

                for ((counter, currentButton) in sceneButtonGroup.withIndex()) {
                    val button = binding.root.findViewById<Button>(currentButton)
                    val size = screen.arrayOfVariants.size
                    if (button.isEnabled) {
                        button.setOnClickListener {
                            val toNextActivityIntent = Intent(
                                this,
                                if (screen.arrayOfVariants[size - counter - 1].nextId == 1) {
                                    storage.clearSharedPreferences(this)
                                    MainActivity::class.java
                                } else SceneActivity::class.java
                            )
                            toNextActivityIntent.putExtra(
                                ID,
                                screen.arrayOfVariants[size - counter - 1].nextId
                            )
                            startActivity(toNextActivityIntent)
                            finish()
                        }
                    }
                }


            }
        }


    }

    private fun makeButtonInvisible(button: Button) {
        button.visibility = View.INVISIBLE
        button.isEnabled = false
    }

    private fun makeButtonVisible(button: Button) {
        button.visibility = View.VISIBLE
        button.isEnabled = true
    }

}