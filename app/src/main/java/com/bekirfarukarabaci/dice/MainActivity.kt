package com.bekirfarukarabaci.dice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bekirfarukarabaci.dice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnRoll.setOnClickListener{
                rollDice()
            }
        }
    }

    private fun getRandomImageResource(): Int {
        return when((1..6).random()){
            1 -> R.drawable.dice_one
            2 -> R.drawable.dice_two
            3 -> R.drawable.dice_three
            4 -> R.drawable.dice_four
            5 -> R.drawable.dice_five
            6 -> R.drawable.dice_six
            else -> R.drawable.dice_one

        }
    }

    private fun rollDice(){
        binding.apply {
            btnRoll.isEnabled = false

            val diceRoller = object : Runnable{
                var counter = 0
                override fun run() {
                    counter++
                    if (counter>10){
                        diceImg1.setImageResource(getRandomImageResource())
                        btnRoll.isEnabled = true
                    }else{
                        diceImg1.setImageResource(getRandomImageResource())
                        Handler(Looper.getMainLooper()).postDelayed(this,100)
                    }
                }
            }
            Handler(Looper.getMainLooper()).postDelayed(diceRoller,0)
        }
    }
}