package ru.kk.xakimjonov

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.view.KeyEvent.KEYCODE_DPAD_UP
import ru.kk.xakimjonov.Direction.UP
import ru.kk.xakimjonov.Direction.DOWN
import ru.kk.xakimjonov.Direction.LEFT
import ru.kk.xakimjonov.Direction.RIGHT
import ru.kk.xakimjonov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }
}

    override fun OnkeyDown(KeyCode: Int, event: KeyEvent?): Boolean {
        when (KeyCode) {
            KEYCODE_DPAD_UP -> move(UP)
            KEYCODE_DPAD_DOWN -> move(DOWN)
            KEYCODE_DPAD_LEFT -> move(LEFT)
            KEYCODE_DPAD_RIGHT -> move(RIGHT)
        }
        return super.OnKeyDown(KeyCode, event)
    }

private fun move(direction: Direction) {
    when (direction) {
        UP -> {
            binding.myTank.rotation = 0f
            (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin -= 50
        }

        DOWN -> {
            binding.myTank.rotation = 180f
            (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += 50
        }

        LEFT -> {
            binding.myTank.rotation = 270f
            (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin -= 50
        }

        RIGHT -> {
            binding.myTank.rotation = 90f
            (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin += 50
        }
    }
    binding.container.removeView(binding.myTank)
    binding.container.addView(binding.myTank)
}