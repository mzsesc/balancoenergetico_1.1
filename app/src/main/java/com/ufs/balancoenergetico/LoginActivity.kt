package com.ufs.balancoenergetico

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ufs.balancoenergetico.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        val view = binding!!.root
        setContentView(view)

        auth = Firebase.auth

        binding!!.loginbtn.setOnClickListener(View.OnClickListener {

            if (TextUtils.isEmpty(binding!!.emailSingUp.text)) {

                binding!!.emailSingUp.error = "Campo de usuario não pode estar em branco"

            } else if (TextUtils.isEmpty(binding!!.passwordSingUp.text)) {

                binding!!.passwordSingUp.error = "Campo de senha não pode estar em branco"

            } else {
                logar(
                    binding!!.emailSingUp.text.toString(),
                    binding!!.passwordSingUp.text.toString(),
                )
            }


        })

    }

    private fun logar(email: String, senha: String) {

        auth.let {
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "signInWithCustomToken:success")
                        //val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "Autenticação bem-sucedida.",
                            Toast.LENGTH_SHORT
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

        val intent = Intent(this, MainActivity4::class.java)
        startActivity(intent)

        finish()

    }
}

