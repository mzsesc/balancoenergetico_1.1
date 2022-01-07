package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonacessnow = findViewById<Button>(R.id.button_acess_now)
            buttonacessnow.setOnClickListener{
                val acessnow = Intent(this, MainActivity4::class.java)
                startActivity(acessnow)
            }
        val buttonnewaccount = findViewById<Button>(R.id.button_new_account)
        buttonnewaccount.setOnClickListener{
            val newaccount = Intent(this, MainActivity3::class.java)
            startActivity(newaccount)
        }
    }
}