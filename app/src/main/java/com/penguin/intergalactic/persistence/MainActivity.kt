package com.penguin.intergalactic.persistence

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var perfs: SharedPreferences? = null
    val PREFS_FILENAME = "preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        perfs = this.getSharedPreferences(PREFS_FILENAME, 0) //0 or combination of MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE or MODE_MULTI_PROCESS

        updateUI()

        greetingsSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            val editor = perfs!!.edit()
            editor.putBoolean(PREFS_FILENAME, isChecked)
            editor.apply()
            updateUI()
        }

        settingsButton.setOnClickListener {
            val intent = Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    fun updateUI() {
        val checked = perfs!!.getBoolean(PREFS_FILENAME, false)
        if (checked) {
            greetingsText!!.text = "Different text 👋"
        } else {
            greetingsText!!.text = "Hello World!"
        }
        greetingsSwitch!!.isChecked = checked
    }
}
