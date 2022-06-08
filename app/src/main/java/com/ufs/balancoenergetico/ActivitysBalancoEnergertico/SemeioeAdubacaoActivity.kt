package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.*
import com.ufs.balancoenergetico.databinding.ActivitySemeioeAdubacaoBinding
import com.ufs.balancoenergetico.db.datasemeioadubacao

class SemeioeAdubacaoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySemeioeAdubacaoBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySemeioeAdubacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val tipodesemente = binding.textView11.text.toString()
            val toDoubleTipoDeSemente = tipodesemente.toDouble()
            val CalTipoDeSemente = (toDoubleTipoDeSemente * CfTiposDeSemente)

            val fertilizanteazotado = binding.textView12.text.toString()
            val toDoubleFertilizanteAzotado = fertilizanteazotado.toDouble()
            val CalFertilizanteAzotado = (toDoubleFertilizanteAzotado * CfFertilizanteAzotados)

            val fertilizantepotassico = binding.textView10.text.toString()
            val toDoubleFertilizantePotassico = fertilizantepotassico.toDouble()
            val CalFertilizantePotassico = (toDoubleFertilizantePotassico * CfFertilizantePotassico)

            val fertilizantefosfatado = binding.textView13.text.toString()
            val toDoubleFertilizanteFosfatado = fertilizantefosfatado.toDouble()
            val CalFertilizanteFosfatado = (toDoubleFertilizanteFosfatado * CfFertilizanteFosfotado)


            val semeioeAdubacao = "SA"

            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
            val User = datasemeioadubacao(
                CalTipoDeSemente,
                CalFertilizanteAzotado,
                CalFertilizantePotassico,
                CalFertilizanteFosfatado
            )
            database.child(semeioeAdubacao).setValue(User).addOnSuccessListener {

                binding.textView10.text.clear()
                binding.textView12.text.clear()
                binding.textView11.text.clear()
                binding.textView13.text.clear()


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