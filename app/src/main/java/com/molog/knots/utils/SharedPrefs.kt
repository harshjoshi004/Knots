package com.molog.knots.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.molog.knots.models.UserModel

object SharedPrefs {
    fun storeData(user: UserModel, context:Context) {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", user.name)
        editor.putString("userName", user.userName)
        editor.putString("bio", user.bio)
        editor.putString("email", user.email)
        editor.putString("uri", user.uri)
        editor.apply()
    }

    fun getUserName(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("userName", null)
    }

    fun getBio(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("bio", null)
    }

    fun getName(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("name", null)
    }

    fun getEmail(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("email", null)
    }

    fun getImage(context:Context): String? {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("uri", null)
    }
}