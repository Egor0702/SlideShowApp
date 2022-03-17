package com.example.slideshowapp.model.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.slideshowapp.R
import com.example.slideshowapp.databinding.HomeFragmentLayoutBinding
import com.example.slideshowapp.domain.None
import com.example.slideshowapp.model.presenters.viewmodel.ViewModelPhoto
import com.example.slideshowapp.model.ui.App
import com.example.slideshowapp.model.ui.PermissionManager
import com.example.slideshowapp.model.ui.onFailure
import com.example.slideshowapp.model.ui.onSuccess
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    override val layoutId: Int = R.layout.home_fragment_layout
    private lateinit var viewModelPhoto: ViewModelPhoto
    private lateinit var homeFragmentLayoutBinding: HomeFragmentLayoutBinding
    @Inject
    lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        viewModelPhoto = viewModel {
            onSuccess(listPhoto, ::updateListPhoto)
            onSuccess(currentPhoto, ::updateScreen)
            onFailure(failure, ::onFailureFragment)
        }
        viewModelPhoto.getListPhoto()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentLayoutBinding = HomeFragmentLayoutBinding.inflate(inflater, container, false)
        val view = homeFragmentLayoutBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           showPermission()

    }
    private fun updateScreen(image : Uri?) {
        Log.d ("Eg", "updateScreen : ${image}")
        homeFragmentLayoutBinding.imageview.setImageURI(image)
        viewModelPhoto.setImageOnScreen()
    }

    private fun onFailureFragment(none: None?) {
        Log.d ("Eg", "onFailureFragment")
        Toast.makeText(activity,"error",Toast.LENGTH_SHORT).show()
    }

    private fun updateListPhoto(list: List<Uri>?) {
        Log.d ("Eg", "updateListPhoto")
        viewModelPhoto.setImageOnScreen()

    }

    fun showPermission() {
        base {
            permissionManager.getPermission(this, this)
        }
    }
    }
