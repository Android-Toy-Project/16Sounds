package com.example.a16sounds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.example.a16sounds.databinding.ActivityInitBinding


class InitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_init)

        val binding = ActivityInitBinding.inflate(layoutInflater) // ViewBinding
        val init_activity_title = AnimationUtils.loadAnimation(this, R.anim.init_activity_title_animation)
        val init_activity_startbutton = AnimationUtils.loadAnimation(this, R.anim.init_activity_startbutton_animation)
        val init_activity_shineEffect1 = AnimationUtils.loadAnimation(this, R.anim.init_activity_shinebar1_animation)
        val init_activity_shineEffect2 = AnimationUtils.loadAnimation(this, R.anim.init_activity_shinebar2_animation)
        //init_activity_startbutton.repeatCount()
        binding.TitleImage.startAnimation(init_activity_title)
        binding.startButton.startAnimation(init_activity_startbutton)
        binding.shineEffect1.startAnimation(init_activity_shineEffect1)
        binding.shineEffect2.startAnimation(init_activity_shineEffect2)
        setContentView(binding.root)
        /*
        //For animation
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        binding.TitleImage.alpha = 0f
        binding.TitleImage.animate().alpha(1f).duration = 1500
        binding.TitleImage.translationY = 100.0f
        binding.TitleImage.animate().alpha(1f).translationYBy(-100.0f).duration = 1000
        binding.TitleImage.animate().apply {
            duration = 1000
        }
        //Vector
        val vector : AnimatedVectorDrawable? = ContextCompat.getDrawable(this, R.drawable.play_animation) as AnimatedVectorDrawable?
        binding.TitleImage.setImageDrawable(vector)
        vector!!.start()
        */
    }

    fun goMainActivity(v : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}