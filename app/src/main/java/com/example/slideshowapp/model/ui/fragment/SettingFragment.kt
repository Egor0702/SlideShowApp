package com.example.slideshowapp.model.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.slideshowapp.R
import com.example.slideshowapp.databinding.HomeFragmentLayoutBinding
import com.example.slideshowapp.databinding.SettingsLayoutBinding

class SettingFragment : BaseFragment() {
    override val layoutId: Int = R.layout.settings_layout
    private lateinit var settingsLayoutBinding: SettingsLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        settingsLayoutBinding = SettingsLayoutBinding.inflate(inflater, container, false)
        val view = settingsLayoutBinding.root
        return view
    }
}