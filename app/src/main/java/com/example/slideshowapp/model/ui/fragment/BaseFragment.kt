package com.example.slideshowapp.model.ui.fragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.slideshowapp.model.ui.activity.BaseActivity
import com.example.slideshowapp.model.ui.base
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    abstract val layoutId: Int
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        Log.d("Egor", "BaseFragment viewVodel()")
        val vm = ViewModelProvider(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }
    inline fun base(block: BaseActivity.() -> Unit) = activity.base(block) // activity== getActivity()
}