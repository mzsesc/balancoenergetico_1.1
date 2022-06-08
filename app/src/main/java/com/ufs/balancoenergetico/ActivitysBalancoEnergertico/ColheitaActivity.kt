package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.ufs.balancoenergetico.CfMaoDeObra
import com.ufs.balancoenergetico.CfMaquinasAgricolas
import com.ufs.balancoenergetico.MainActivity
import com.ufs.balancoenergetico.databinding.ActivityColheitaBinding
import com.ufs.balancoenergetico.db.datacolheita

class ColheitaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColheitaBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityColheitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        binding.button7.setOnClickListener {

            val colheitadeira = binding.textView20.text.toString()
            val toDoubleColheitadeira = colheitadeira.toDouble()
            val CalColheita = (toDoubleColheitadeira * CfMaquinasAgricolas)

            val maodeobra = binding.textView22.text.toString()
            val toDoubleMaoDeObra = maodeobra.toDouble()
            val CalMaoDeObra = (toDoubleMaoDeObra * CfMaoDeObra)

            val ensiladeira = binding.textView21.text.toString()
            val toDoubleEnsilhadeira = ensiladeira.toDouble()
            val CalEnsilhadeira = (toDoubleEnsilhadeira * CfMaquinasAgricolas)
            val colheita = "CH"


            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
            val User = datacolheita(CalColheita, CalMaoDeObra, CalEnsilhadeira)
            database.child(colheita).setValue(User).addOnSuccessListener {

                binding.textView20.text.clear()
                binding.textView22.text.clear()
                binding.textView21.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}