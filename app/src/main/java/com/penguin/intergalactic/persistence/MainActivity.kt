package com.penguin.intergalactic.persistence

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var perfs : SharedPreferences? = null
    val PREFS_FILENAME = "preferences"

    var gtext: TextView? = null
    var gswitch: Switch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gswitch = findViewById(R.id.greetingsSwitch)
        gtext = findViewById(R.id.greetingsText)

        perfs = this.getSharedPreferences(PREFS_FILENAME,0) //0 or combination of MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE or MODE_MULTI_PROCESS

        redrawText()

        gswitch!!.setOnCheckedChangeListener { buttonView, isChecked ->
            val editor = perfs!!.edit()
            editor.putBoolean(PREFS_FILENAME, isChecked)
            editor.apply()
            redrawText()
        }
    }

    fun redrawText() {
        if (perfs!!.getBoolean(PREFS_FILENAME, false)) {
            gtext!!.text = "Different text 👋"
        } else {
            gtext!!.text = "Hello World!"
        }
    }
}
