package com.example.material3motion.ui

import android.content.Context
import android.graphics.Color
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.Slide
import com.example.material3motion.MainActivity
import com.example.material3motion.R
import com.example.material3motion.databinding.FragmentListDetailBinding
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform

class ListDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentListDetailBinding

    private var transition: String? = null
    private var textTransition: String? = null
    private var text: String? = null
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity= MainActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transition = arguments?.getString("text")
        textTransition = arguments?.getString("textView")
        text = arguments?.getString("texts")

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.root_layout
            duration=resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentListDetailBinding.inflate(layoutInflater, container, false)
        mBinding.card.transitionName = transition
        mBinding.textView.transitionName = textTransition
        mBinding.textView.text = text

        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }



        enterTransition = MaterialContainerTransform().apply {
            startView = requireActivity().findViewById(R.id.bt_floating)
            endView = mBinding.card
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
//            containerColor = requireContext().themeColor(R.attr.colorSurface)
//            startContainerColor = requireContext().themeColor(R.attr.colorSecondary)
//            endContainerColor = requireContext().themeColor(R.attr.colorSurface)
        }
        returnTransition = Slide().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_medium).toLong()
            addTarget(R.id.card)
        }
    }


}