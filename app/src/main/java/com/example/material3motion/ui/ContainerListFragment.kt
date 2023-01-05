package com.example.material3motion.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.material3motion.R
import com.example.material3motion.databinding.FragmentContainerListBinding
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough

class ContainerListFragment : Fragment(), ItemAdapter.ClickListener {

    private lateinit var mBinding: FragmentContainerListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentContainerListBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }


    private fun setUpRecyclerView() {
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter =
                ItemAdapter(
                    requireContext().resources.getStringArray(R.array.items).toList(),
                    this@ContainerListFragment
                )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ContainerListFragment()
    }

    override fun onClick(view: View, text: String, position: Int) {
        val bundle = Bundle()
        val transitionName = getString(R.string.container_item_card, position)
        val textTransitionName = getString(R.string.shared_elements_item_text, position)
        bundle.putString("text", transitionName)
        bundle.putString("textView", textTransitionName)
        bundle.putString("texts", text)

       /* exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }*/
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        val detailFragment = ListDetailFragment().apply {
            arguments = bundle
        }
        activity?.supportFragmentManager?.beginTransaction()
            ?.addSharedElement(view, view.transitionName)?.replace(R.id.root_layout, detailFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

}