package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class CourotineActivity : AppCompatActivity() {

    private var result1 ="Result#1";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courotine)

        findViewById<Button>(R.id.button).setOnClickListener {
            var job = CoroutineScope(IO).launch {
                fakeApiResult()
            }
        };

    }

    private suspend fun fakeApiResult(){
        var result = getApiFromResult();
        setTextOnMainThread(result);
        println("debug $result")
    }

    private suspend fun getApiFromResult():String{
        logThread("get result from API");
        delay(1000);
        return result1;
    }

    private fun logThread(methodName:String){
        println("debug : ${methodName}: ${Thread.currentThread().name}")
    }

    private suspend fun setTextOnMainThread(text:String){
        withContext(Main){
            //communicate with view
        }
    }


}