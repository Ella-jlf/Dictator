package android.bloodynation

import android.bloodynation.databinding.ActivityGameBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class GameActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityGameBinding

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initBottomNavView()

    }



    private fun initBottomNavView() {
        bottomNavigationView = mBinding.bottomNavigationView
        navController = findNavController(R.id.fragment_container)

        bottomNavigationView.setupWithNavController(navController)
    }




}