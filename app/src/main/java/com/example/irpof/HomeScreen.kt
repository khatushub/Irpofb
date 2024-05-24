package com.example.irpof

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.irpof.databinding.ActivityHomeScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var currentPage = 0
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPager
        bottomNavigationView = binding.bottomNavigation

        val imageList = listOf(R.drawable.homeimage1, R.drawable.homeimage2, R.drawable.homeimage3)
        val adapter = ImageSliderAdapter(imageList)
        viewPager.adapter = adapter
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            if (viewPager.adapter != null) {
                currentPage = (currentPage + 1) % imageList.size
                viewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(runnable, 3000)
            }
        }
        handler.postDelayed(runnable, 3000)

        binding.latestHeadline.setSelected(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)

    }

}