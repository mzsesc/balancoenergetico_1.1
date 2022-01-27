package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonacessnow = findViewById<Button>(R.id.btn_login)
            buttonacessnow.setOnClickListener{
                val acessnow = Intent(this, LoginActivity::class.java)
                startActivity(acessnow)

            }

        val buttonnewaccount = findViewById<Button>(R.id.button_new_account)
        buttonnewaccount.setOnClickListener{
            val newaccount = Intent(this, SignUpActivity::class.java)
            startActivity(newaccount)

        }

    }


}
