package com.pride.testprgroup.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.pride.testprgroup.R

class NoConnection : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_no_connection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isConnected()
    }

    private fun isConnected() {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.placeholder,MainFragment())?.commit()
            }
        })
    }

}
