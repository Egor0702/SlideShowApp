package com.example.slideshowapp.model.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.slideshowapp.R
import com.example.slideshowapp.model.ui.PermissionManager
import com.example.slideshowapp.model.ui.fragment.BaseFragment
import javax.inject.Inject
import androidx.fragment.app.FragmentTransaction
import com.example.slideshowapp.model.ui.App

abstract class BaseActivity : AppCompatActivity(){
    abstract var fragment: BaseFragment // var - чтобы обеспечить будущее изменение фрагмента
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var permissionManager: PermissionManager
    open val contentView = R.layout.base_layout_new

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContent()
        addFragment(savedInstanceState)
    }
    open fun setContent(){
        setContentView(contentView)
    }
    fun addFragment(savedInstanceState: Bundle?, fragment: BaseFragment = this.fragment) {
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment)
        }
        }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm: T = ViewModelProvider(this, viewModelFactory).get(T::class.java)
        vm.body()
        return vm
    }
    inline fun Activity?.base(block: BaseActivity.() -> Unit) = (this as? BaseActivity)?.let(block)
}