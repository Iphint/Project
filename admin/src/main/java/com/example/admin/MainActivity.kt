package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener{
            val i = Intent(this@MainActivity, UploadActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.mainDelete.setOnClickListener{
            val i = Intent(this@MainActivity,Delete::class.java)
            startActivity(i)
            finish()
        }

        binding.mainUpdate.setOnClickListener{
            val i = Intent(this@MainActivity,Update::class.java)
            startActivity(i)
            finish()
        }
    }
}