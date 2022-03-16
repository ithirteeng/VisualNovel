package com.example.visualnovel.tools

import android.content.Context
import android.widget.Toast
import com.example.visualnovel.Screen
import com.google.gson.Gson
import org.json.JSONObject
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.Charset

class JSONParser {
    companion object {
        fun parseJSON(context: Context, jsonSeparator: String): List<Screen> {
            val json = JSONObject(loadJSONFromAssets(context) ?: "").getJSONArray(jsonSeparator)

            return Gson().fromJson(json.toString(), Array<Screen>::class.java).toList()
        }

        private fun loadJSONFromAssets(context: Context): String? {
            var json: String? = null

            try {
                val input: InputStream = context.assets.open("screens.json")

                val amountOFBytes = input.available()
                val bufferArray = ByteArray(amountOFBytes)

                input.read(bufferArray)
                input.close()

                json = String(bufferArray, Charset.defaultCharset())
            } catch (exception: Exception) {
                Toast.makeText(context, "JSON file is empty!", Toast.LENGTH_SHORT).show()
            }

            return json
        }
    }
}