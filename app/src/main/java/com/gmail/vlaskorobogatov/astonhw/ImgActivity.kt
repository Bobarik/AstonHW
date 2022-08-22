package com.gmail.vlaskorobogatov.astonhw

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gmail.vlaskorobogatov.astonhw.databinding.ActivityImgBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL

class ImgActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImgBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE || event?.action == KeyEvent.ACTION_DOWN
                    && event.keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    val inputMethodManager =
                        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                    lifecycleScope.launch {
                        getImgFromURL()
                    }
                    return true
                }
                return false
            }
        })
    }

    suspend fun getImgFromURL() {
        withContext(Dispatchers.IO) {
            try {
                val v: InputStream =
                    URL("${binding.editText.text}").content as InputStream
                val drawable: Drawable = Drawable.createFromStream(v, "name")
                runOnUiThread {
                    binding.imageView.setImageDrawable(drawable)
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@ImgActivity,
                        e.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}