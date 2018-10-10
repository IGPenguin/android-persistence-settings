package com.penguin.intergalactic.persistence

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.ListPreference
import android.preference.PreferenceActivity
import android.preference.PreferenceManager

const val KEY_PREF_NAME = "pref_key_name"
const val KEY_PERF_JOB = "pref_key_job"
const val KEY_PERF_JOB_STRING = "pref_key_job_string"
const val KEY_PREF_BONUS_BUTTON = "pref_key_bonus_button"
const val KEY_PREF_TOAST = "pref_key_toast_start"

var settingsPerfs: SharedPreferences? = null
var namePreference: EditTextPreference? = null
var jobPreference: ListPreference? = null


class SettingsActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)

        settingsPerfs = PreferenceManager.getDefaultSharedPreferences(this)
        namePreference = findPreference(KEY_PREF_NAME) as EditTextPreference
        jobPreference = findPreference(KEY_PERF_JOB) as ListPreference

        namePreference!!.summary = settingsPerfs!!.getString(KEY_PREF_NAME,"Nameless")
        jobPreference!!.summary = jobPreference!!.entries.get(settingsPerfs!!.getString(KEY_PERF_JOB,"0").toInt())

        namePreference!!.setOnPreferenceChangeListener { preference, value ->
            preference.summary = value.toString()
            true
        }

        jobPreference!!.setOnPreferenceChangeListener { preference, value ->
            if (preference is ListPreference) {
                val index = preference.findIndexOfValue(value.toString())
                val job = preference.entries.get(index)
                preference.summary = job
                val editor = settingsPerfs!!.edit()
                editor.putString(KEY_PERF_JOB_STRING, job.toString())
                editor.apply()
            }
            true
        }

    }

}