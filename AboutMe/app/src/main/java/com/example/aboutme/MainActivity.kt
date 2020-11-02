package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var editText: TextView
    lateinit var nicknameTextView: TextView
    lateinit var buttonDone: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.nickname_edit)
        nicknameTextView = findViewById(R.id.nickname_text)
        buttonDone = findViewById(R.id.done_button)
        buttonDone.setOnClickListener {
            addNickname(it)
        }
        nicknameTextView.setOnClickListener {
            updateNickname(it)
        }
    }

    private fun addNickname(view: View){
        nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
        hideKeyboard(view)
    }

    private fun showKeyboard(view: View){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, 0)
    }

    private fun hideKeyboard(view: View){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View){
        editText.visibility = View.VISIBLE
        buttonDone.visibility = View.VISIBLE
        view.visibility = View.GONE
        editText.requestFocus()
        showKeyboard(editText)
    }
}