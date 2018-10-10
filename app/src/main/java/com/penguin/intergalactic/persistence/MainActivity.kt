package com.penguin.intergalactic.persistence

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var settingsPerfs: SharedPreferences? = null
    val CUSTOM_PREFS_FILENAME = "preferences"
    val SHOW_MORE_KEY = "custom_key_more"
    var customPerfs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settingsPerfs = PreferenceManager.getDefaultSharedPreferences(this)
        customPerfs = this.getSharedPreferences(CUSTOM_PREFS_FILENAME, 0) //0 or combination of MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE or MODE_MULTI_PROCESS

        if (settingsPerfs!!.getBoolean(KEY_PREF_TOAST, false)){
            Toast.makeText(this,"Toasted start!!!",Toast.LENGTH_LONG).show()
        }

        moreSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            val editor = customPerfs!!.edit()
            editor.putBoolean(SHOW_MORE_KEY, isChecked)
            editor.apply()
            updateUI()
        }

        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    fun updateUI() {
        val showMore = customPerfs!!.getBoolean(SHOW_MORE_KEY, false)
        moreSwitch!!.isChecked = showMore

        if (showMore) {
            settingsButton.visibility = View.VISIBLE
        } else {
            settingsButton.visibility = View.GONE
        }

        if (settingsPerfs!!.contains(KEY_PREF_NAME)) {
            if (settingsPerfs!!.contains(KEY_PERF_JOB_STRING)){
                greetingsText!!.text = "Its you " + settingsPerfs!!.getString(KEY_PREF_NAME, "Nameless")+ " the " + settingsPerfs!!.getString(KEY_PERF_JOB_STRING,"Unemployed")
            } else {
                greetingsText!!.text = "Its you " + settingsPerfs!!.getString(KEY_PREF_NAME, "Nameless")
            }

        } else {
            greetingsText!!.text = "Hello World!"
        }

        if (settingsPerfs!!.getBoolean(KEY_PREF_BONUS_BUTTON, false)) {
            bonusButton.visibility = View.VISIBLE
        } else {
            bonusButton.visibility = View.GONE
        }
    }
}
