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
