package com.example.material3motion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.material3motion.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var mBinding:FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding=FragmentSettingsBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    companion object {
       @JvmStatic
       fun newInstance() = SettingsFragment()

    }
}