package com.example.findaconference.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findaconference.R
import java.lang.reflect.Array.set

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        AppPrefs.init(requireContext())

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    object AppPrefs {
        private const val APPNAME = R.string.app_name.toString()
        private const val MODE = Context.MODE_PRIVATE
        private lateinit var preferences: SharedPreferences

        private val IS_LOGIN = Pair("is_login", false)
        private val NAME = Pair("name", "")

        fun init(context: Context) {
            preferences = context.getSharedPreferences(APPNAME, MODE)
        }

        private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
            val editor = edit()
            operation(editor)
            editor.apply()
        }

        var isLogin: Boolean
            get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
            set(value) = preferences.edit {
                it.putBoolean(IS_LOGIN.first, value)
            }

        var name: String
            get() = preferences.getString(NAME.first, NAME.second) ?: ""
            set(value) = preferences.edit {
                it.putString(NAME.first, value)
            }
    }

}