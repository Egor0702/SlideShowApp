package com.example.slideshowapp.model.ui

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.slideshowapp.domain.None
import com.example.slideshowapp.model.ui.activity.BaseActivity

fun <T : Any, L : LiveData<T>> LifecycleOwner.onSuccess(liveData: L?, body: (T?) -> Unit) {
    liveData?.observe(this, Observer(body))
}

fun <L : LiveData<None>> LifecycleOwner.onFailure(liveData: L, body: (None?) -> Unit) {
    liveData.observe(this, Observer(body))
}
inline fun FragmentActivity?.base (block: BaseActivity.() -> Unit) = (this as? BaseActivity)?.let(block)