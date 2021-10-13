package com.yuezhi.myfragmentnavigatordemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphNavigator
import androidx.navigation.NavigatorProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yuezhi.myfragmentnavigatordemo.fragment.*
import com.yuezhi.myfragmentnavigatordemo.utils.FixFragmentNavigator


class MainActivity : AppCompatActivity() {
    var myFragment: Fragment? = null
    var menuNavigation : BottomNavigationView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuNavigation = findViewById<BottomNavigationView>(R.id.menu_navigation)
        myFragment = supportFragmentManager.findFragmentById(R.id.fragment_main)
        //fragment的重复加载问题和NavController有关
        val navController = NavHostFragment.findNavController(myFragment!!)


        setFixNavigator(navController)
//        setNomalNavigator(navController)
    }

    /**
     * 正常的设置
     */
    private fun setNomalNavigator(navController: NavController) {

        //将BottomNavigationView和NaviGraph关联起来
        NavigationUI.setupWithNavController(menuNavigation!!, navController)
    }

    /**
     * FixFragmentNavigator的使用
     */
    private fun setFixNavigator(navController: NavController) {
        val provider = navController.navigatorProvider
        //设置自定义的navigator
        val fixFragmentNavigator =
            FixFragmentNavigator(this, myFragment!!.getChildFragmentManager(), myFragment!!.getId())
        provider.addNavigator(fixFragmentNavigator)

        val navDestinations = initNavGraph(provider, fixFragmentNavigator)
        navController.graph = navDestinations!!

        menuNavigation?.setOnNavigationItemSelectedListener { item ->
            navController.navigate(item.getItemId())
            true
        }
    }


    private fun initNavGraph(
        provider: NavigatorProvider,
        fragmentNavigator: FixFragmentNavigator
    ): NavGraph? {
        val navGraph = NavGraph(NavGraphNavigator(provider))

        //用自定义的导航器来创建目的地
        val destination1 = fragmentNavigator.createDestination()
        destination1.id = R.id.AFragment
        destination1.className = AFragment::class.java.getCanonicalName()
        navGraph.addDestination(destination1)
        val destination2 = fragmentNavigator.createDestination()
        destination2.id = R.id.BFragment
        destination2.className = BFragment::class.java.getCanonicalName()
        navGraph.addDestination(destination2)
        val destination3 = fragmentNavigator.createDestination()
        destination3.id = R.id.CFragment
        destination3.className = CFragment::class.java.getCanonicalName()
        navGraph.addDestination(destination3)
        val destination4 = fragmentNavigator.createDestination()
        destination4.id = R.id.DFragment
        destination4.className = DFragment::class.java.getCanonicalName()
        navGraph.addDestination(destination4)
        val destination5 = fragmentNavigator.createDestination()
        destination5.id = R.id.EFragment
        destination5.className = EFragment::class.java.getCanonicalName()
        navGraph.addDestination(destination5)
        navGraph.startDestination = destination1.id
        return navGraph
    }

    /**
     * 重写返回键
     */
    override fun onBackPressed() {
        finish()
    }
}