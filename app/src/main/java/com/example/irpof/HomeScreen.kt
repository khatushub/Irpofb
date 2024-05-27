package com.example.irpof

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
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

        val uniqueText = binding.uniqueText

        binding.text1.setOnClickListener {
            uniqueText.text = "Indian Railways Promotee Officers Federation (IRPOF) Is An Organization Of Promotee Officers Having Its Head Quarter At New Delhi. It Functions Through Its Member Associations. Zonal Associations Functions Through Divisional Bodies Across All The 69 Divisions Of Indian Railways. The Aim Of The Federation Is To Safeguard Interests Of Promotee Officers And Provide Better Career Opportunities And Working Conditions. It Works For Development And Promoting Overall Efficiency In The Working Of Railways In The Best Interest Of Common People, The Nation And The Railway Men.\n" +
                    "\n" +
                    "IRPOF Has Launched This Site To Keep Its Member Informed About The, Latest Activities And Share Views On Topics Of Importance. All The Members Are Requested To Kindly Contribute To Make The Site Useful And Meaningful."
            uniqueText.visibility = View.VISIBLE
        }

        binding.text2.setOnClickListener {
            uniqueText.text = "Indian Railways Promotee Officers Federation (IRPOF) Is An Organization Of Promotee Officers Having Its Head Quarter At New Delhi. It Functions Through Its Member Associations. Zonal Associations Functions Through Divisional Bodies Across All The 69 Divisions Of Indian Railways."
            uniqueText.visibility = View.VISIBLE
        }

        binding.text3.setOnClickListener {
            uniqueText.text = "Indian Railways Promotee Officers Federation (IRPOF) Is An Organization Of Promotee Officers Having Its Head Quarter At New Delhi."
            uniqueText.visibility = View.VISIBLE
        }

        bottomNavigationView.selectedItemId = R.id.navigation_home

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_circular -> {
                    startActivity(Intent(applicationContext, CircularActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_home -> true
                else -> false
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)

    }

}