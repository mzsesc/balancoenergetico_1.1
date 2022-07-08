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
    val df = DecimalFormat("#.###", DecimalFormatSymbols(Locale.ENGLISH))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityObterBalancoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        ObterBalancoEnergetico()

        binding?.button9?.setOnClickListener {
            CalBalancoEnegetico()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }

    fun ObterBalancoEnergetico() {


        database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
        database.get().addOnSuccessListener {

            if (it.exists()) {
                val soma = it.child("soma").value.toString()

               val sm = soma.toDouble()

                binding?.textViewTotalEnergetico?.text = sm.toString()
            }

        }
        database = FirebaseDatabase.getInstance().getReference("Produção do Milho")
        database.get().addOnSuccessListener {

            if (it.exists()) {
                val soma = it.child("produçaodomilho").value.toString()

               val sm = soma.toDouble()

                binding?.textViewTotalEnergeticoProduzido?.text = sm.toString()
            }

        }
    }


    fun CalBalancoEnegetico() {

        val entrada = binding?.textViewTotalEnergetico?.text.toString()
        val doubleentrada = entrada.toDouble()
        val saida = binding?.textViewTotalEnergeticoProduzido?.text.toString()
        val doublesaida = saida.toDouble()

        if (doubleentrada != 0.0) {
            val EE = doublesaida / doubleentrada
            val rounded1 = df.format(EE).toDouble()
            val RE = doubleentrada / doublesaida
            val rounded2 = df.format(RE).toDouble()
            val BE = doublesaida - doubleentrada
            val rounded3 = df.format(BE).toDouble()



            binding?.textViewTotalEficaciaEnergetica?.text = rounded1.toString()
            binding?.textViewRazOEnergTica?.text = rounded2.toString()
            binding?.textViewBalanOEnergTico?.text = rounded3.toString()

            database = FirebaseDatabase.getInstance()
                .getReference("Eficacia Energetica,Razao Energetica,Balanco Energetico")
            database.get().addOnSuccessListener {

                database.child("EE").setValue(rounded1)
                database.child("RE").setValue(rounded2)
                database.child("BE").setValue(rounded3)

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
