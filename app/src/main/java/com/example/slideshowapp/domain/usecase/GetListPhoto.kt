package com.example.slideshowapp.domain.usecase

import android.net.Uri
import android.util.Log
import com.example.slideshowapp.domain.ImageRepository
import com.example.slideshowapp.domain.None
import com.example.slideshowapp.model.presenters.viewmodel.ViewModelPhoto
import com.example.slideshowapp.model.ui.App
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GetListPhoto @Inject constructor(private val imageRep: ImageRepository) : UseCase() {

    fun returnList(viewModelPhoto:ViewModelPhoto){
        unsubscribe()
        Log.d("Eg", "returnList  до launch")
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            Log.d("Eg", "returnList launch")
            val result = withContext(backgroundContext) {
                imageRep.getImageList()
            }
           if (result==null) viewModelPhoto.failureProcess(None())
            else viewModelPhoto.sucsessProcessList(result!!)
            Log.d("Eg", "returnList() result = $result")
        }
        //return result
    }

    override fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

}