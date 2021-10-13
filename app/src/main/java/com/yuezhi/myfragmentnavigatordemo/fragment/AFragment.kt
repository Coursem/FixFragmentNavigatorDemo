package com.yuezhi.myfragmentnavigatordemo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yuezhi.myfragmentnavigatordemo.R

/**
 *
 * @author Cui Haoyue
 * @create 2021/10/11
 */
class AFragment : Fragment() {
    val TAG = "AFragmnet--->"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("chy", "${TAG}onViewCreated: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e("chy", "${TAG}onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e("chy", "${TAG}onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("chy", "${TAG}onDestroy: ")
    }
}