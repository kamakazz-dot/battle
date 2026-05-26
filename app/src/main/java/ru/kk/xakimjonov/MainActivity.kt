package ru.kk.xakimjonov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.view.KeyEvent.KEYCODE_DPAD_UP
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import ru.kk.xakimjonov.Direction.UP
import ru.kk.xakimjonov.Direction.DOWN
import ru.kk.xakimjonov.Direction.LEFT
import ru.kk.xakimjonov.Direction.RIGHT
import ru.kk.xakimjonov.databinding.ActivityMainBinding

const val CELL_SIZE = 50

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private val gridDrawer by lazy {
       GridDrawer( context = this)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Menu"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                gridDrawer.drawGrid()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean { // было OnkeyDown с большой буквы
        when (keyCode) {
            KEYCODE_DPAD_UP -> move(UP)
            KEYCODE_DPAD_DOWN -> move(DOWN)
            KEYCODE_DPAD_LEFT -> move(LEFT)
            KEYCODE_DPAD_RIGHT -> move(RIGHT)
        }
        return super.onKeyDown(keyCode, event) // было OnKeyDown с большой буквы
    }

    private fun move(direction: Direction) {
        when (direction) {
            UP -> {
                binding.myTank.rotation = 0f
                if (binding.myTank.marginTop > 0)
                (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
            }
            DOWN -> {
                binding.myTank.rotation = 180f
                if (binding.MyTank.MarginTop + binding.myTank.height < binding.container.height / CELL_SIZE * CELL_SIZE) {
                (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
                }
            }
            LEFT -> {
                binding.myTank.rotation = 270f
                if (binding.myTank.marginLeft > 0)
                (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE
            }
            RIGHT -> {
                binding.myTank.rotation = 90f
                if (binding.MyTank.MarginLeft + binding.myTank.width < binding.container.width / CELL_SIZE * CELL_SIZE) {
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE
                }
            }
        }
        binding.container.removeView(binding.myTank)
        binding.container.addView(binding.myTank)
    }
}