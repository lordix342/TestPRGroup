package com.pride.testprgroup.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pride.testprgroup.R
import com.pride.testprgroup.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            bExit.setOnClickListener {
                activity?.finish()
            }
            bAgree.setOnClickListener {
                openFragment()
            }
        }
    }

    private fun openFragment() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref!=null) {
            with(sharedPref.edit()) {
                putInt("OpenItem", 1)
                apply()
            }
        }
        if (isConnected()) {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.placeholder, MainFragment())?.commit()
        } else {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.placeholder, NoConnection())?.commit()
        }
    }

    private fun isConnected():Boolean {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}