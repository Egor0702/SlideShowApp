package com.example.slideshowapp.domain.usecase

import android.net.Uri
import android.util.Log
import com.example.slideshowapp.data.SharedPreferencesManager
import com.example.slideshowapp.domain.ImageRepository
import com.example.slideshowapp.domain.None
import com.example.slideshowapp.model.presenters.viewmodel.ViewModelPhoto
import kotlinx.coroutines.*
import javax.inject.Inject

class GetImage @Inject constructor(private val imageRepository: ImageRepository): UseCase() {
    var uriResult:Uri? = null
    fun getImage(listImage:List<Uri>?, currentImage: Uri?, viewModelPhoto: ViewModelPhoto){
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            uriResult = withContext(backgroundContext){
                imageRepository.getImage(listImage, currentImage)
            }
            if (uriResult==null) viewModelPhoto.failureProcess(None())
            else viewModelPhoto.sucsesssesImage(uriResult!!)
            Log.d("Eg", "returnList() result = $uriResult")
        }
    }
    override fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}