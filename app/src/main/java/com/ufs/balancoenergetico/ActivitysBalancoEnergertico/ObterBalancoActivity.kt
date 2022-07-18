package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityObterBalancoBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class ObterBalancoActivity : AppCompatActivity() {

    private var binding: ActivityObterBalancoBinding? = null
    private lateinit var database: DatabaseReference
    val df: NumberFormat = DecimalFormat.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityObterBalancoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        ObterBalancoEnergetico()

        binding?.button9?.setOnClickListener {
            calBalancoEnegetico()
        }


    }

    private fun ObterBalancoEnergetico() {


        database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
        database.get().addOnSuccessListener {

            if (it.exists()) {
                val soma = it.child("soma").value
                val rounded1 = df.format(soma)
                val stringsoma = soma.toString()
                val sm = stringsoma.toDouble()

                binding?.textViewTotalEnergetico?.text = ("$rounded1 MJ")
                binding?.salve1?.text = sm.toString()
            }

        }
        database = FirebaseDatabase.getInstance().getReference("Produção do Milho")
        database.get().addOnSuccessListener {

            if (it.exists()) {
                val soma = it.child("produçaodomilho").value.toString()
                val sm1 = soma.toDouble()
                val soma2= it.child("grao").value.toString()
                val sm2 = soma2.toDouble()
                val soma3= it.child("silagem").value.toString()
                val sm3 = soma3.toDouble()
                val somatotal = sm1 + sm2 + sm3
                val rounded1 = df.format(somatotal)
                val stringsoma = somatotal.toString()

                val sm = stringsoma.toDouble()

                binding?.textViewTotalEnergeticoProduzido?.text = ("$rounded1 MJ")
                binding?.salve2?.text = sm.toString()

            }

        }
    }


    fun calBalancoEnegetico() {

        val entrada = binding?.salve1?.text.toString()
        val doubleentrada = entrada.toDouble()
        val saida = binding?.salve2?.text.toString()
        val doublesaida = saida.toDouble()

        if (doubleentrada != 0.0) {
            val EE = doublesaida / doubleentrada
            val rounded1 = df.format(EE)
            val RE = doubleentrada / doublesaida
            val rounded2 = df.format(RE)
            val BE = doublesaida - doubleentrada
            val rounded3 = df.format(BE)



            binding?.textViewTotalEficaciaEnergetica?.text = ("$rounded1 MJ")
            binding?.textViewRazOEnergTica?.text = ("$rounded2 MJ")
            binding?.textViewBalanOEnergTico?.text = ("$rounded3 MJ")

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
