package com.ufs.balancoenergetico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivitySemeioeAdubacaoBinding
import com.ufs.balancoenergetico.db.dataSemeioeadubacao

class SemeioeAdubacaoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySemeioeAdubacaoBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySemeioeAdubacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val tipodesemente = binding.textView10.text.toString()
            val fertilizantesintetico = binding.textView12.text.toString()
            val materiaorganica = binding.textView13.text.toString()
            val semeioeAdubacao = binding.textView9.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
            val User= dataSemeioeadubacao(tipodesemente, fertilizantesintetico, materiaorganica)
            database.child(semeioeAdubacao).setValue(User).addOnSuccessListener {

                binding.textView10.text.clear()
                binding.textView12.text.clear()
                binding.textView13.text.clear()


                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            }

    }
}