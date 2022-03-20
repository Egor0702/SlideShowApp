package com.example.slideshowapp.model.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TimePicker
import com.example.slideshowapp.R
import com.example.slideshowapp.data.SharedPreferencesManager
import com.example.slideshowapp.data.service.SlideShowService
import com.example.slideshowapp.databinding.SettingsLayoutBinding
import com.example.slideshowapp.model.ui.App
import java.util.*
import javax.inject.Inject


class SettingFragment : BaseFragment() {
    companion object {
        const val TIMESTART = "timestart"
        const val TIMEFINISH = "timefinish"
    }

    override val layoutId: Int = R.layout.settings_layout
    private lateinit var settingsLayoutBinding: SettingsLayoutBinding
    var timeStartUserClick = false // переменная для отслеживания изменял ли пользователь время старта приложения
    var timeFinishUserClick = false // переменная для отслеживания изменял ли пользователь время финиша приложения
    var hourStart = "0"
    var minStart = "0"
    var hourFinish = "0"
    var minFinish = "0"
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
                SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                settingsLayoutBinding.textViewSeekBar.text = seek.progress.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {
                seek.progress.also {
                    sharedPreferencesManager.setTime((it.toLong()) * 1000)
                    settingsLayoutBinding.textViewSeekBar.text = it.toString()
                }
            }
        })

        settingsLayoutBinding.timePick1.setIs24HourView(true)
        settingsLayoutBinding.timePick1.setOnTimeChangedListener(object :
                TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
                timeStartUserClick = true
                hourStart = validateTime(hourOfDay)
                minStart = validateTime(minute)
            }
        })
        settingsLayoutBinding.timePick2.setIs24HourView(true)
        settingsLayoutBinding.timePick2.setOnTimeChangedListener(object :
                TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
                timeFinishUserClick = true
                hourFinish = validateTime(hourOfDay)
                minFinish = validateTime(minute)
            }
        })
        settingsLayoutBinding.saving.setOnClickListener {
            if (timeStartUserClick == true){
                base { startService(
                    (Intent(context, SlideShowService::class.java)
                            .apply {
                                putExtra(TIMESTART, "$hourStart:$minStart".trim())
                            })
                )
                }
                timeStartUserClick = false
            }
            if (timeFinishUserClick == true){
                base { startService(
                        (Intent(context, SlideShowService::class.java)
                                .apply {
                                    putExtra(TIMEFINISH, "$hourFinish:$minFinish".trim())
                                })
                )
                }
                timeFinishUserClick = false
            }
        }
    }
    fun validateTime(int:Int): String{
       return when(int){
           0 -> "0".plus(int.toString())
            1 -> "0".plus(int.toString())
            2 -> "0".plus(int.toString())
           3 -> "0".plus(int.toString())
           4 -> "0".plus(int.toString())
           5 -> "0".plus(int.toString())
           6 -> "0".plus(int.toString())
           7 -> "0".plus(int.toString())
           8 -> "0".plus(int.toString())
           9 -> "0".plus(int.toString())
           else -> int.toString()
       }
    }
}