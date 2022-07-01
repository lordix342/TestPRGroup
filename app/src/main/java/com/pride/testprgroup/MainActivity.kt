package com.pride.testprgroup

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pride.testprgroup.fragments.MainFragment
import com.pride.testprgroup.fragments.NoConnection
import com.pride.testprgroup.fragments.StartFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val openItem = sharedPref?.getInt("OpenItem", 0)
        if ((openItem!=0)&&(openItem!=null)) {
            if(isConnected()) {
                supportFragmentManager.beginTransaction().replace(R.id.placeholder, MainFragment()).commit()
            } else {
                supportFragmentManager.beginTransaction().replace(R.id.placeholder, NoConnection()).commit()
            }
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.placeholder, StartFragment()).commit()
        }
    }
    private fun isConnected():Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}