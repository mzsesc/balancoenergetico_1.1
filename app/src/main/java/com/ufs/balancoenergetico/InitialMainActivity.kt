package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class InitialMainActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_main)

        auth = Firebase.auth


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

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if (currentUser != null)
            if (currentUser.email?.isNotEmpty() == true) {
                Toast.makeText(
                    baseContext,
                    "usuario" + currentUser.email + "logado",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        //updateUI()
    }



}
