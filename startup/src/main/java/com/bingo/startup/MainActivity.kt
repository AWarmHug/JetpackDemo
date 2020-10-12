package com.bingo.startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.startup.AppInitializer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btInitLibraryD).setOnClickListener {
            AppInitializer.getInstance(this@MainActivity)
                .initializeComponent(LibraryDInitializer::class.java)
        }
    }

}
