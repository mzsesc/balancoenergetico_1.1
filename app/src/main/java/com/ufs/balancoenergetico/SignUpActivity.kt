package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.ufs.balancoenergetico.databinding.ActivitySignUpBinding
import com.ufs.balancoenergetico.db.DataUsuario
import com.ufs.balancoenergetico.db.datacolheita


class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null


    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        auth = Firebase.auth



        binding!!.singupbtn.setOnClickListener(View.OnClickListener {

            when {
                TextUtils.isEmpty(binding!!.emailSingUp.text) -> {

                    binding!!.emailSingUp.error = "Campo de usuario não pode estar em branco"

                }
                TextUtils.isEmpty(binding!!.passwordSingUp.text) -> {

                    binding!!.passwordSingUp.error = "Campo de senha não pode estar em branco"

                }
                else -> {

                    inscreverse(
                        binding!!.emailSingUp.text.toString(),
                        binding!!.passwordSingUp.text.toString()
                    )




                }
            }
        })

    }

    private fun inscreverse(email: String, senha: String) {

        auth.let {
            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "signInWithCustomToken:success")
                        //val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "Autenticação bem-sucedida.",
                            Toast.LENGTH_LONG
                        ).show()
                        abrePricipal()
                        //updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Falha na autenticação.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI()
                    }
                }
        }



    }

    private fun abrePricipal() {

        binding!!.emailSingUp.text.clear()
        binding!!.passwordSingUp.text.clear()

        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}