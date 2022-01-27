package com.ufs.balancoenergetico

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val btn_login = findViewById<Button>(R.id.loginbtn)
        val email_login = findViewById<EditText>(R.id.email_sing_up)
        val password_login = findViewById<EditText>(R.id.password_sing_up)

        btn_login.setOnClickListener{

            when{
                TextUtils.isEmpty(email_login.text.toString().trim{ it <=' '})->{
                    Toast.makeText(
                        this,
                        "Por favor, digite o e-mail",
                        Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(password_login.text.toString().trim{ it <=' '}) ->{
                    Toast.makeText(
                        this,
                        "Por favor, digite a senha",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else ->{

                    val email:String = email_login.text.toString().trim{ it <=' '}
                    val password:String = password_login.text.toString().trim{ it <=' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { Task ->

                            if (Task.isSuccessful) {

                                val firebaseUser: FirebaseUser = Task.result!!.user!!

                                Toast.makeText(
                                    this,
                                    "Login efetuado com sucesso.",
                                    Toast.LENGTH_SHORT
                                ).show()


                                val intent = Intent(this, MainActivity4::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    Task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
    }

}

