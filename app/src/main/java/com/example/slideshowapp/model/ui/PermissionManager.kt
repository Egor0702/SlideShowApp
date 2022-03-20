package com.example.slideshowapp.model.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.slideshowapp.domain.None
import com.example.slideshowapp.domain.usecase.UseCase
import com.example.slideshowapp.model.presenters.viewmodel.ViewModelPhoto
import kotlinx.coroutines.*


class PermissionManager () : UseCase() {
    companion object {
        //        const val REQUEST_READ_EXTERNAL_PERMISSION = 10004
//        const val REQUEST_READ_INTERNAL_PERMISSION = 10005
        const val readExternal = Manifest.permission.READ_EXTERNAL_STORAGE
        const val REQUEST_CODE = 1
    }

    fun request(context:Context, activity: AppCompatActivity, viewModelPhoto: ViewModelPhoto) {
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            if (ActivityCompat.checkSelfPermission(context, PermissionManager.readExternal) == PackageManager.PERMISSION_GRANTED) {
                viewModelPhoto.getListPhoto()
                return@launch
            } else{ ActivityCompat.requestPermissions(activity, arrayOf(PermissionManager.readExternal), PermissionManager.REQUEST_CODE)}
            withContext(backgroundContext) {
                while (true) {
                    if (ActivityCompat.checkSelfPermission(context, PermissionManager.readExternal) == PackageManager.PERMISSION_GRANTED) {
                        viewModelPhoto.getListPhoto()
                        return@withContext
                    }
                }
            }
            return@launch
        }
    }
    override fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}