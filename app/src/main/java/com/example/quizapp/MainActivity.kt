package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        var btn:Button = findViewById(R.id.button);

        btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var name:String = (findViewById(R.id.input) as AppCompatEditText).text.toString();
                if(name.toString().isEmpty()){
                    Toast.makeText(this@MainActivity,"Enter you Name",Toast.LENGTH_LONG).show();
                }else{
                    var intent:Intent = Intent(this@MainActivity,QuestionActivity::class.java)
                    intent.putExtra(setData.name,name);
                    startActivity(intent)
                }
            }
        });
    }

}




