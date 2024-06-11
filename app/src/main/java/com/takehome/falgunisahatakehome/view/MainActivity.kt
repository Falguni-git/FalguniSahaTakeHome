package com.takehome.falgunisahatakehome.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.takehome.falgunisahatakehome.R
import com.takehome.falgunisahatakehome.view.screens.FragmentDetailsScreen
import com.takehome.falgunisahatakehome.view.screens.FragmentUserInformationScreen
import com.takehome.falgunisahatakehome.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    companion object{
        const val INFORMATION_SCREEN_TAG = "User Screen"
        const val DETAIL_SCREEN_TAG = "Detail Screen"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentUserInformationScreen(), INFORMATION_SCREEN_TAG)
            .addToBackStack(INFORMATION_SCREEN_TAG)
            .commit()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun replaceWithDetailScreen(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentDetailsScreen(), DETAIL_SCREEN_TAG)
            .addToBackStack(DETAIL_SCREEN_TAG)
            .commit()
    }
}

