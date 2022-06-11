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
            val tipodesemente = binding.sementes.text.toString()
            val toDoubleTipoDeSemente = tipodesemente.toDouble()
            val CalTipoDeSemente = (toDoubleTipoDeSemente * CfTiposDeSemente)

            val fertilizanteNitogenado = binding.fertilizanteNitogenado.text.toString()
            val toDoubleFertilizanteNitogenado = fertilizanteNitogenado.toDouble()
            val CalFertilizanteNitogenado = (toDoubleFertilizanteNitogenado * CfFertilizanteAzotados)

            val fertilizantepotassico = binding.fertilizantePotassico.text.toString()
            val toDoubleFertilizantePotassico = fertilizantepotassico.toDouble()
            val CalFertilizantePotassico = (toDoubleFertilizantePotassico * CfFertilizantePotassico)

            val fertilizantefosfatado = binding.fertilizanteFosfatado.text.toString()
            val toDoubleFertilizanteFosfatado = fertilizantefosfatado.toDouble()
            val CalFertilizanteFosfatado = (toDoubleFertilizanteFosfatado * CfFertilizanteFosfotado)


            val semeioeAdubacao = "SA"

            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
            val User = datasemeioadubacao(
                CalTipoDeSemente,
                CalFertilizanteNitogenado,
                CalFertilizantePotassico,
                CalFertilizanteFosfatado
            )
            database.child(semeioeAdubacao).setValue(User).addOnSuccessListener {

                binding.fertilizanteFosfatado.text.clear()
                binding.fertilizanteNitogenado.text.clear()
                binding.fertilizantePotassico.text.clear()
                binding.sementes.text.clear()


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