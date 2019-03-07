package com.dev.joks.countrieslist.screens.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    var root: View? = null
    val TAG = BaseFragment::class.java.simpleName

    @LayoutRes
    abstract fun getLayoutRes(): Int
}