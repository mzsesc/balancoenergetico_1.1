package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.MainActivity
import com.ufs.balancoenergetico.databinding.ActivityObterBalancoBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class ObterBalancoActivity : AppCompatActivity() {

    private var binding: ActivityObterBalancoBinding? = null
    private lateinit var database: DatabaseReference
    val df = DecimalFormat.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityObterBalancoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        ObterBalancoEnergetico()

        binding?.button9?.setOnClickListener {
            CalBalancoEnegetico()
        }


    }

    fun ObterBalancoEnergetico() {


        database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
        database.get().addOnSuccessListener {

            if (it.exists()) {
                val soma = it.child("soma").value
                val rounded1 = df.format(soma)
                val stringsoma = soma.toString()
                val sm = stringsoma.toDouble()

                binding?.textViewTotalEnergetico?.text = rounded1
                binding?.salve1?.text = sm.toString()
            }

        }
        database = FirebaseDatabase.getInstance().getReference("Produção do Milho")
        database.get().addOnSuccessListener {

            if (it.exists()) {
                val soma = it.child("produçaodomilho").value
                val rounded1 = df.format(soma)
                val stringsoma = soma.toString()

                val sm = stringsoma.toDouble()

                binding?.textViewTotalEnergeticoProduzido?.text = rounded1.toString()
                binding?.salve2?.text = sm.toString()

            }

        }
    }


    fun CalBalancoEnegetico() {

        val entrada = binding?.salve1?.text.toString()
        val doubleentrada = entrada.toDouble()
        val saida = binding?.salve2?.text.toString()
        val doublesaida = saida.toDouble()

        if (doubleentrada != 0.0) {
            val EE = doublesaida / doubleentrada
            val rounded1 = df.format(EE)
            val RE = doubleentrada / doublesaida
            val rounded2 = df.format(RE)
            val BE = doubleentrada - doublesaida
            val rounded3 = df.format(BE)



            binding?.textViewTotalEficaciaEnergetica?.text = rounded1.toString()
            binding?.textViewRazOEnergTica?.text = rounded2.toString()
            binding?.textViewBalanOEnergTico?.text = rounded3.toString()

            database = FirebaseDatabase.getInstance()
                .getReference("Eficacia Energetica,Razao Energetica,Balanco Energetico")
            database.get().addOnSuccessListener {

                database.child("EE").setValue(EE)
                database.child("RE").setValue(RE)
                database.child("BE").setValue(BE)

            }
        } else {
            Toast.makeText(
                baseContext,
                "Falha na autenticação.",
                Toast.LENGTH_SHORT
            ).show()


        }


    }

}
