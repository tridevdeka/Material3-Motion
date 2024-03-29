package com.example.material3motion.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.material3motion.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var mBinding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    companion object {
       @JvmStatic
       fun newInstance()= HomeFragment()
    }
}