package com.example.slideshowapp.domain

import android.net.Uri

interface ImageRepository {
    suspend fun getImageList() : List<Uri>
    suspend fun getImage(listImage:List<Uri>?, currentImage: Uri?): Uri?
}