package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var editText: TextView
    private lateinit var nicknameTextView: TextView
    private lateinit var buttonDone: Button
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Syekh Syihabuddin Azmil Umri")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        editText = binding.nicknameEdit
        nicknameTextView = binding.nicknameText
        buttonDone = binding.doneButton
        binding.myName = myName
        buttonDone.setOnClickListener {
            addNickname(it)
        }
        nicknameTextView.setOnClickListener {
            updateNickname(it)
        }
    }

    private fun addNickname(view: View){
        myName?.nickname = editText.text.toString()
        binding.invalidateAll()
        editText.visibility = View.GONE
        binding.doneButton.visibility = View.GONE
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
        binding.doneButton.visibility = View.GONE
        editText.requestFocus()
        showKeyboard(editText)
    }
}