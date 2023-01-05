package com.example.material3motion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.TransitionManager
import com.example.material3motion.databinding.ActivityMainBinding
import com.example.material3motion.ui.ContainerListFragment
import com.example.material3motion.ui.HomeFragment
import com.example.material3motion.ui.ListDetailFragment
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var containerListFragment: ContainerListFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var activeFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        mBinding.bottomNavigation.selectedItemId = R.id.homeFragment
        initVar()
        setUpBottomNav()
        setFloating()
    }


    private fun initVar() {
        homeFragment = HomeFragment.newInstance()
        containerListFragment = ContainerListFragment.newInstance()
        settingsFragment = SettingsFragment.newInstance()
        activeFragment = homeFragment


        supportFragmentManager.beginTransaction().apply {
            add(R.id.root_layout, containerListFragment, "Container")
                .hide(containerListFragment)
            add(R.id.root_layout, settingsFragment, "Settings")
                .hide(settingsFragment)
            add(R.id.root_layout, homeFragment, "Home")

        }.commit()

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.root_layout, fragment)
//            .hide(activeFragment)
            .show(fragment)
            .addToBackStack("Home")
            .commit()
    }

    private fun setUpBottomNav() {
        mBinding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    replaceFragment(homeFragment)
                    activeFragment = homeFragment
                    true
                }

                R.id.containerFragment -> {
                    replaceFragment(containerListFragment)
                    activeFragment = containerListFragment
                    true
                }

                R.id.settingsFragment -> {
                    replaceFragment(settingsFragment)
                    activeFragment = settingsFragment
                    true
                }

                else -> false
            }
        }
    }

    private fun setFloating() {
        mBinding.btFloating.setOnClickListener {
            val transition =buildContainerTransform()

            transition.startView =mBinding.btFloating
            transition.endView = findViewById(R.id.card)
            transition.addTarget(R.id.card)
            TransitionManager.beginDelayedTransition(findViewById(android.R.id.content), transition)
            replaceFragment(ListDetailFragment())
            hideBottomNav()
            mBinding.btFloating.hide()
        }
    }

    private fun hideBottomNav(){
        mBinding.bottomNavigation.animate()
            .translationY(mBinding.bottomNavigation.height.toFloat())
            .setInterpolator(FastOutSlowInInterpolator())
            .setDuration(200)
            .start()
    }

    private fun showBottomNav(){
        mBinding.bottomNavigation.animate()
            .translationY(0f)
            .setInterpolator(FastOutSlowInInterpolator())
            .setDuration(200)
            .start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (homeFragment.isVisible){
            showBottomNav()
            mBinding.btFloating.show()
        }
    }

    private fun buildContainerTransform() =
        MaterialContainerTransform().apply {
            addTarget(R.id.card)
            setAllContainerColors(MaterialColors.getColor(mBinding.root, com.google.android.material.R.attr.colorSurface))
            setPathMotion( MaterialArcMotion())
            duration = 500
            interpolator = FastOutSlowInInterpolator()
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
        }
}