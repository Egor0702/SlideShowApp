package com.example.slideshowapp.model.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.slideshowapp.R
import com.example.slideshowapp.data.SharedPreferencesManager
import com.example.slideshowapp.databinding.SettingsLayoutBinding
import com.example.slideshowapp.model.ui.App
import javax.inject.Inject


class SettingFragment : BaseFragment() {
    override val layoutId: Int = R.layout.settings_layout
    private lateinit var settingsLayoutBinding: SettingsLayoutBinding
    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        settingsLayoutBinding = SettingsLayoutBinding.inflate(inflater, container, false)
        val view = settingsLayoutBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsLayoutBinding.textViewSeekBar.text = ((sharedPreferencesManager.getTime().toInt()) / 1000).toString()

        settingsLayoutBinding.seekBar.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar){
                        seek.progress.also {
                            sharedPreferencesManager.setTime((it.toLong()) * 1000)
                            settingsLayoutBinding.textViewSeekBar.text = it.toString()
                        }
                    }
                })

        settingsLayoutBinding.timePick1.setIs24HourView(true)
    }

}