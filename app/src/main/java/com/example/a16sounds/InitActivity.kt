package com.example.a16sounds

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.SoundPool
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import com.example.a16sounds.databinding.ActivityInitBinding
import java.io.File
import java.util.*



class InitActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityInitBinding.inflate(layoutInflater) // ViewBinding
        val anim_view = mutableListOf<Pair<Int, View>>()

        //add animation id & view
        anim_view.add(Pair(R.anim.init_activity_title_animation, binding.TitleImage))
        anim_view.add(Pair(R.anim.init_activity_shinebar1_animation, binding.shineEffect1))
        anim_view.add(Pair(R.anim.init_activity_shinebar2_animation, binding.shineEffect2))
        anim_view.add(Pair(R.anim.init_activity_startbutton_animation, binding.startButton))

        for(temp in anim_view) {
            val anim = AnimationUtils.loadAnimation(this, temp.first)
            temp.second.startAnimation(anim)
        }

        //play background music
        val init_bgm: MediaPlayer = MediaPlayer.create(this, R.raw.init_bgm)
        val sound_pool: SoundPool = SoundPool.Builder().build()
        val sound_id:Int = sound_pool.load(this, R.raw.start_sound, 1) ///soundID 생성
        val left_volume:Float = 0.7f; val right_volume:Float = 0.7f
        val priority:Int = 0; val loop:Int = 0; val rate:Float = 1f
        binding.TitleImage.setOnClickListener {
            init_bgm.start()
        }
        //BGM fade_out & release
        binding.startButton.setOnClickListener{
            fade_out_bgm(init_bgm,1f,0f)
            play_start_sound(sound_pool, sound_id, left_volume,right_volume,priority,loop, rate)
            goMainActivity()
        }
        setContentView(binding.root)
    }

    fun fade_out_bgm(init_bgm:MediaPlayer, from: Float, to: Float) {
        val animator = ValueAnimator.ofFloat(from, to)
        animator.duration = 500
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            val volume = it.animatedValue as Float
            try{
                init_bgm.setVolume(volume, volume)
            }catch(e: Exception){
                it.cancel()
            }
        }
        animator.start()
        init_bgm.release()
    }

    fun play_start_sound(sound_pool:SoundPool, sound_id:Int, left_volume:Float, right_volume:Float, priority:Int, loop: Int, rate: Float){
        sound_pool.play(sound_id, left_volume,right_volume,priority,loop, rate)
    }

    fun goMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.init_activity_fade_in, R.anim.init_activity_fade_out)
        finish()
    }
}
