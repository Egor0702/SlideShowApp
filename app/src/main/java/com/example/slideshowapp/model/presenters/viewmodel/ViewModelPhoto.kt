package com.example.slideshowapp.model.presenters.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.slideshowapp.domain.None
import com.example.slideshowapp.domain.usecase.GetImage
import com.example.slideshowapp.domain.usecase.GetListPhoto
import com.example.slideshowapp.model.ui.App
import javax.inject.Inject

class ViewModelPhoto @Inject constructor(private val getListPhoto: GetListPhoto,
private val getImage: GetImage) : BaseViewModel() {

    var listPhoto: MutableLiveData<List<Uri>>? = MutableLiveData()
    var currentPhoto : MutableLiveData<Uri>? = MutableLiveData()
    var failure : MutableLiveData<None> = MutableLiveData()

    fun getListPhoto() {
        Log.d("Eg", "getListPhoto()")
        getListPhoto.returnList(this)
    }
    fun setImageOnScreen(){
        Log.d("Eg", "setImageOnScreen() : ${listPhoto?.value==null}, ${currentPhoto?.value == null}")
       getImage.getImage(listPhoto?.value, currentPhoto?.value, this)
    }
    fun sucsessProcessList(resultWork : List<Uri>) {
        Log.d("Eg", "sucsessProcessList()")
        listPhoto?.value = resultWork
    }
    fun sucsesssesImage (image: Uri) {
        Log.d("Eg", "sucsesssesImage()")
        currentPhoto?.value = image
        Log.d("Eg", "sucsesssesImage currentPhoto = ${currentPhoto?.value}")
    }
    fun  failureProcess(none: None) {
        Log.d("Eg", "failureProcess()")
        failure.value = none
    }
}