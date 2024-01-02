package com.example.suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val check = findViewById<Button>(R.id.buttoncheck)
        check.setOnClickListener {
            val userinput = findViewById<EditText>(R.id.palindrom).text.toString()
            if (isPalindrome(userinput)){
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "not   palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        val next = findViewById<Button>(R.id.nextbutton)

        next.setOnClickListener {
            val name = findViewById<EditText>(R.id.name).text.toString()
            val intent = Intent(this, secondscreen::class.java)
            intent.putExtra("hola", name)
            startActivity(intent)
        }


    }
    fun isPalindrome(input : String): Boolean {
        val cleanInput = input.toLowerCase().replace("\\s+".toRegex(), "")
        val reversedInput = cleanInput.reversed()
        return cleanInput == reversedInput
    }
}