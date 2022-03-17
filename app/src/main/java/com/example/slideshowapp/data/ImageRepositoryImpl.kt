package com.example.slideshowapp.data

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.slideshowapp.domain.ImageRepository
import java.io.File
import java.util.*
import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import kotlinx.coroutines.delay
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val context: Context,
                                              private val sharPref: SharedPreferencesManager) : ImageRepository{
    override suspend fun getImageList(): List<Uri> {
        Log.d("Eg", "ImageRepositoryImpl getImageList()")
        val uriExternal: Uri? = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val uriInternal: Uri? = MediaStore.Images.Media.INTERNAL_CONTENT_URI
        val projection: Array<String> = arrayOf("_data")
        var cursorExternal: Cursor? = null
        var cursorInternal: Cursor? = null
        //var directories: Array<String?>? = null
        val dirList: SortedSet<String> = TreeSet<String>()

        if (uriExternal != null && uriInternal != null) {
            Log.d("Eg", "(uriExternal != null && uriInternal != null) = ${(uriExternal != null && uriInternal != null)}")
            cursorExternal = context.contentResolver.query(uriExternal, projection, null, null, null)
            cursorInternal = context.contentResolver.query(uriInternal,projection,null,null,null)
        }

        if (cursorExternal != null && cursorExternal.moveToFirst()) {
            Log.d("Eg", "(cursorExternal != null && cursorExternal.moveToFirst()) = ${(cursorExternal != null && cursorExternal.moveToFirst())}")
            while (cursorExternal.moveToNext()) {
                var tempDir: String = cursorExternal.getString(0).substringBeforeLast("/")
                dirList.add(tempDir)
            }
        }
        if (cursorInternal != null && cursorInternal.moveToFirst()) {
            Log.d("Eg", "(cursorInternal != null && cursorInternal.moveToFirst()) = ${(cursorInternal != null && cursorInternal.moveToFirst())}")
            while (cursorInternal.moveToNext()) {
                var tempDir: String = cursorInternal.getString(0).substringBeforeLast("/")
                Log.d("Eg", "tempDir = ${tempDir}")
                dirList.add(tempDir)
            }
        }
        Log.d("Eg", "tempDir size: ${dirList.size}")
        val resultIAV: List<Uri> = getPath(dirList)
        cursorExternal?.close()
        cursorInternal?.close()
        Log.d("Eg", "resultIAV : ${resultIAV.lastOrNull()}")
        return resultIAV
    }

        private fun getPath (directories:Set<String?>?) : List<Uri> {
            val resultArray: MutableList<Uri> = mutableListOf()
            for (i in 0 until directories?.size!!) {
                val imageDir = File(directories.elementAt(i))
                var imageList: Array<File> = imageDir.listFiles()
                if (imageList == null) continue
                for (imagePath in imageList!!) {
                    try {
                        if (imagePath.isDirectory()) {
                            imageList = imagePath.listFiles()
                        }
                        if (imagePath.getName().contains(".jpg") || imagePath.getName().contains(".JPG")
                                || imagePath.getName().contains(".jpeg") || imagePath.getName()
                                        .contains(".JPEG")
                                || imagePath.getName().contains(".png") || imagePath.getName()
                                        .contains(".PNG")
                        ) {
                            val path: Uri = imagePath.toUri()
                           // getAbsolutePath().toUri()
                            Log.d("Eg", "path: $path")
                            resultArray.add(path)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return resultArray
        }

    override suspend fun getImage(listImage :List<Uri> ?, currentImage: Uri?): Uri? {
        Log.d("Eg", "getImag() ImageRepImpl")
        if (currentImage == null) return listImage?.get(0).toString().substringAfter(",").toUri()
        Log.d("Eg", "getImag() ImageRepImpl after second if")

            var imageResult: Uri? = null
            delay(sharPref.getTime())
            if ((listImage?.indexOf(currentImage) == listImage?.size) || (listImage!!.indexOf(currentImage) > listImage!!.size)) {
                imageResult = listImage?.get(0)
            } else {
                imageResult = listImage?.get((listImage.indexOf(currentImage) + 1))
            }
            return imageResult
        }
}