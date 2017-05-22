package com.example.ktproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.person

class MainktActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainkt)
        var jake = person("jake", 19, "hello,world")
        jake.show()
        println("")
    }
}
