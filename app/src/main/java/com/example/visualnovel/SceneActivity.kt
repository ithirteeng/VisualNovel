package com.example.visualnovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.visualnovel.databinding.ActivitySceneBinding

class SceneActivity : AppCompatActivity() {

    companion object {
        const val ID = "intent_id"
        const val backgroundPath = "R.drawable."
    }

    private lateinit var binding: ActivitySceneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySceneBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentScreenId = intent.getIntExtra(ID, 0)

        val jsonParser = JSONParser()
        val listOfScreens: List<Screen> = jsonParser.parseJSON(this, "screens")

        for (screen in listOfScreens) {
            if (screen.id == currentScreenId) {

                if (currentScreenId == 3) {
                    screen.header = screen.header.replace("/NAME!", "YOUR NAME")
                }
                binding.sceneHeader.text = screen.header
                binding.sceneBackgroundImage.setImageResource(resources.getIdentifier(screen.background, "drawable", packageName))

                for(currentButton in binding.sceneButtonsGroup.referencedIds) {
                    val button = binding.root.findViewById<Button>(currentButton)
                    makeButtonInvisible(button)
                }

                for((counter, currentButton) in binding.sceneButtonsGroup.referencedIds.withIndex()) {
                    if (counter == screen.arrayOfVariants.size) {
                        break
                    } else {
                        val button = binding.root.findViewById<Button>(currentButton)
                        makeButtonVisible(button)
                        button.text = screen.arrayOfVariants[counter].variantText
                    }
                }

                for ((counter, currentButton) in binding.sceneButtonsGroup.referencedIds.withIndex()) {
                    val button = binding.root.findViewById<Button>(currentButton)
                    if (button.isEnabled) {
                        button.setOnClickListener {
                            val toNextActivityIntent = Intent(this, SceneActivity::class.java)
                            toNextActivityIntent.putExtra(ID, screen.arrayOfVariants[counter].nextId)
                            startActivity(toNextActivityIntent)
                            finish()
                        }
                    }
                }


            }
        }



    }

    private fun makeButtonInvisible(button: Button) {
        button.visibility = View.GONE
        button.isEnabled = false
    }

    private fun makeButtonVisible(button: Button) {
        button.visibility = View.VISIBLE
        button.isEnabled = true
    }

}