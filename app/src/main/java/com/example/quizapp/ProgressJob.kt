package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_progress_job.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class ProgressJob : AppCompatActivity() {
    private val progressMax = 100;
    private val progressStart = 0;
    private val jobTime = 4000;

    private lateinit var job:CompletableJob


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_job)

        job_button.setOnClickListener {
            if(!::job.isInitialized){
                initJob()
            }
            job_progress_bar.startOrCancel(job);
        }

    }

    fun ProgressBar.startOrCancel(job:Job){
        if(this.progress>0){
            println("${job} is already active. Cancelling")
            resetJob();
        }else{
            job_button.setText("Cancel job #1");
            CoroutineScope(IO+job).launch {
                println("coroutine ${this} is activated with job ${job}")

                for(i in progressStart..progressMax){
                    delay((jobTime/progressMax).toLong())
                    this@startOrCancel.progress = i;
                }
                updateJobCompleteTextView("Job is complete");
            }
        }
    }

    private fun updateJobCompleteTextView(text:String){
        GlobalScope.launch(Main){
            job_complete_text.text = text;
        }
    }

    private fun resetJob(){
        if(job.isActive||job.isCompleted){
            job.cancel(CancellationException("Resetting job"));
        }
        initJob()
    }

    fun initJob(){
        findViewById<Button>(R.id.job_button).setText("Start job #1");
        job_complete_text.setText("")
        job = Job();
        job.invokeOnCompletion{
            it?.message.let {
                var msg = it;
                if(msg.isNullOrBlank()){
                    msg = "Unknown cancellation error";
                }
                println("${job} was cancelled. Reason $msg")
                showMessage(msg);
            }
        }
        job_progress_bar.max = progressMax;
        job_progress_bar.progress = progressStart;

    }

    fun showMessage(text:String){
        GlobalScope.launch(Main){
            Toast.makeText(this@ProgressJob,text,Toast.LENGTH_LONG).show()
        }
    }

}