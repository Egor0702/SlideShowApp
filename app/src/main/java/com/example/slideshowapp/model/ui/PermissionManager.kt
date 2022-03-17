package com.example.slideshowapp.model.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.github.kayvannj.permission_utils.Func
import com.github.kayvannj.permission_utils.PermissionUtil

class PermissionManager () {
    companion object {
        //        const val REQUEST_READ_EXTERNAL_PERMISSION = 10004
//        const val REQUEST_READ_INTERNAL_PERMISSION = 10005
        const val readExternal = Manifest.permission.READ_EXTERNAL_STORAGE
        const val REQUEST_CODE = 1
    }

    //var requestObject: PermissionUtil.PermissionRequestObject? = null

    fun getPermission(context: Context, activity: AppCompatActivity) {
        try {
            if (ActivityCompat.checkSelfPermission(context, readExternal) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(activity, arrayOf(readExternal), REQUEST_CODE)
        } catch (e: Exception) {
            Log.d("Egor", "Error permission: ${e.message}")
        }
    }

//    fun checkReadExternalPermission(activity: AppCompatActivity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestObject = PermissionUtil.with(activity)
//                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
////                    .ask(REQUEST_READ_EXTERNAL_PERMISSION) {
////                        body()
////                    }
////        } else {
////            body()
//        }
//    }
//
//    fun checkReadInternalPermission(activity: AppCompatActivity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestObject = PermissionUtil.with(activity)
//                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            //.ask(REQUEST_READ_INTERNAL_PERMISSION) {
//            //   body()
//        }
////        } else {
////            body()
////        }
//    }
//
//
//    fun PermissionUtil.PermissionRequestObject.ask(code: Int, granted: () -> Unit): PermissionUtil.PermissionRequestObject? {
//        return this.onAllGranted(object : Func() {
//            override fun call() {
//                granted()
//            }
//        }).ask(code)
//    }
//}
}