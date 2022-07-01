package com.pride.testprgroup.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.pride.testprgroup.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webViewSetup()
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        activity?.finish()
                    }
                }
            })
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(binding.webView, url)
                saveUrl(url)
            }
        }
    }

    fun saveUrl(url: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {
            with(sharedPref.edit()) {
                putString("LastUrl", url)
                apply()
            }
        }
    }

    private fun webViewSetup() {
        val url: String?
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        url = if (sharedPref != null) {
            sharedPref.getString("LastUrl", "https://www.google.com/")
        } else {
            "https://www.google.com/"
        }
        with(binding.webView) {
            webViewClient = WebViewClient()
            loadUrl(url!!)
        }
    }

}
