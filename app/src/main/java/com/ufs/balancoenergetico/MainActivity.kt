package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ufs.balancoenergetico.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        auth = Firebase.auth

        updateUI()

        binding!!.logoutbtn.setOnClickListener{
            auth.signOut()

            startActivity(Intent(this,InitialMainActivity::class.java))
            finish()

        }
    }

    private fun updateUI() {
        val usuario: FirebaseUser? = auth.currentUser
        try {
            binding?.idemail?.text = usuario?.email //ou displayName
        } catch (e: Exception) {
            binding?.idemail?.text = ""
        }
    }
}