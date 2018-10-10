package com.penguin.intergalactic.persistence

import android.os.Bundle
import android.preference.PreferenceActivity

const val KEY_PREF_BONUS_BUTTON = "pref_key_bonus_button"
const val KEY_PREF_TOAST = "pref_key_toast_start"

class SettingsActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
    }

}