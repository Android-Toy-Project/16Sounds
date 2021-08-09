package com.example.a16sounds

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity() {

    private var media_recorder: MediaRecorder? = null
    private val output_path: String? = Environment.getExternalStorageDirectory().absolutePath
    private var output_file: String? = null
    private var state: Boolean = false  //true = recording, false = not recording
    private val formatter = SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale("ko", "KR"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun goProfileActivity(v : View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    //recorder
    fun recordButtonListener(v : View) {    //check permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions,0)

            media_recorder = MediaRecorder()
            media_recorder?.setAudioSource(MediaRecorder.AudioSource.DEFAULT)
            media_recorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            media_recorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        } else {    //if user allowed permission, record
            if(state) {
                stopRecording()
                state = false;
            } else {
                //set filename
                var current_time = Date(System.currentTimeMillis())
                output_file = formatter.format(current_time)

                media_recorder?.setOutputFile(output_path + output_file)
                startRecording()
                state = true;
            }
        }
    }

    private fun startRecording() {
        try {
            media_recorder?.prepare()
            media_recorder?.start()
            Toast.makeText(this, "녹음 시작", Toast.LENGTH_SHORT).show()
        } catch (e : IllegalStateException) {
            e.printStackTrace()
        } catch (e : IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording() {
        media_recorder?.stop()
        media_recorder?.release()
        Toast.makeText(this, "녹음 끝", Toast.LENGTH_SHORT).show()
    }
}