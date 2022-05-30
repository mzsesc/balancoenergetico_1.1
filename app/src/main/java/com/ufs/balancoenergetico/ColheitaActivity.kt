package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityColheitaBinding
import com.ufs.balancoenergetico.databinding.ActivityDesenvolvimentoMilhoBinding
import com.ufs.balancoenergetico.db.dataDesenvolvimentomilho
import com.ufs.balancoenergetico.db.datacolheita

class ColheitaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColheitaBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityColheitaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button7.setOnClickListener {

            val colheitadeira = binding.textView20.text.toString()
           // val ch = colheitadeira.toInt()
           // val chh = ch * cfch
            val maodeobra = binding.textView22.text.toString()
            val ensiladeira = binding.textView21.text.toString()
            val colheita = binding.textView19.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
            val User = datacolheita(colheitadeira, maodeobra, ensiladeira)
            database.child(colheita).setValue(User).addOnSuccessListener {

                binding.textView20.text.clear()
                binding.textView22.text.clear()
                binding.textView21.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()



        }
    }
}