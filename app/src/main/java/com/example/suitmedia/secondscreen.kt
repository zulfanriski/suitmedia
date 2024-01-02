package com.example.suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class secondscreen : AppCompatActivity() {
    private var nameValue: String? = null

    companion object {
        private const val NAME_KEY = "name_key"
        private const val REQUEST_CODE_THIRD_SCREEN = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondscreen)
        val nameIntent = intent.getStringExtra("hola")
        nameValue = savedInstanceState?.getString(NAME_KEY) ?: nameIntent

        val name = findViewById<TextView>(R.id.secondName)
        name.text = nameValue

        val selectedIntent = intent.getStringExtra("m")
        val selectedName = findViewById<TextView>(R.id.selectedName)
        selectedName.text =selectedIntent

        val back = findViewById<ImageButton>(R.id.back1)

        back.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        val next = findViewById<Button>(R.id.ChooseUser)

        next.setOnClickListener {
            val intent = Intent(this, thirdscreen::class.java)
            startActivityForResult(intent, REQUEST_CODE_THIRD_SCREEN)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, nameValue)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        nameValue = savedInstanceState.getString(NAME_KEY)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_THIRD_SCREEN && resultCode == RESULT_OK) {
            val selectedUserName = data?.getStringExtra("selectedUserName")
            val selectedName = findViewById<TextView>(R.id.selectedName)
            selectedName.text = selectedUserName
        }
    }
}