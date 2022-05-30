package com.ufs.balancoenergetico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityDesenvolvimentoMilhoBinding
import com.ufs.balancoenergetico.databinding.ActivitySemeioeAdubacaoBinding
import com.ufs.balancoenergetico.db.dataDesenvolvimentomilho
import com.ufs.balancoenergetico.db.dataSemeioeadubacao

class DesenvolvimentoMilhoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDesenvolvimentoMilhoBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesenvolvimentoMilhoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            val fertilizante = binding.textView15.text.toString()
            val inseticidas = binding.textView16.text.toString()
            val pesticidas = binding.textView17.text.toString()
            val nitrogenio = binding.textView18.text.toString()
            val desenvolvimento = binding.textView14.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
            val User = dataDesenvolvimentomilho(fertilizante, inseticidas, pesticidas, nitrogenio)
            database.child(desenvolvimento).setValue(User).addOnSuccessListener {

                binding.textView15.text.clear()
                binding.textView16.text.clear()
                binding.textView17.text.clear()
                binding.textView18.text.clear()



                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

