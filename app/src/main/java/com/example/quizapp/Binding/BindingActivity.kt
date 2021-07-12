package com.example.quizapp.Binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityBindingBinding

class BindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binding)

        val mainViewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)

        DataBindingUtil.setContentView<ActivityBindingBinding>(
            this,
            R.layout.activity_binding
        ).apply {
            this.setLifecycleOwner(this@BindingActivity)
            this.viewmodel = mainViewModel;
        }
    }
}