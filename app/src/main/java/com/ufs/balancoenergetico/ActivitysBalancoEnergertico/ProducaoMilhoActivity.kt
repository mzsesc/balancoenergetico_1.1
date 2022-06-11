package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.MainActivity
import com.ufs.balancoenergetico.databinding.ActivityProducaoMilhoBinding
import com.ufs.balancoenergetico.db.Datamilho

class ProducaoMilhoActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var binding: ActivityProducaoMilhoBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProducaoMilhoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.button8.setOnClickListener {
            val prodmilho = binding!!.producaoMilho.text.toString()
            val Doubleprodmilho = prodmilho.toDouble()


            database = FirebaseDatabase.getInstance().getReference("Produção do Milho")
            val promilho = Datamilho(Doubleprodmilho)
            database.child("prodmilho").setValue(promilho).addOnSuccessListener {


                binding!!.producaoMilho.text.clear()
            }
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}