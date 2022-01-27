package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val logoutbtn = findViewById<Button>(R.id.logoutbtn)

        logoutbtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()

            val intent = Intent (this,MainActivity::class.java)
            startActivity(intent)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK


            //OU pode ser assim
            // startActivity(Intent(this, MainActivity::class.java))
        }

        val uidusuario = findViewById<TextView>(R.id.uidusuario)
        val idemail = findViewById<TextView>(R.id.idemail)

        val userID = intent.getStringExtra("user id")
        val emailID = intent.getStringExtra("email_id")

        uidusuario.text = "User ID :: $userID"
        idemail.text = "Email ID:: $emailID"




    }
}