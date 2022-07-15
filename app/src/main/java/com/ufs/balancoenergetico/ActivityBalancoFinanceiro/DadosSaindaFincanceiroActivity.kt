package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityDadosSaindaFincanceiroBinding
import java.text.DecimalFormat
import java.util.*

class DadosSaindaFincanceiroActivity : AppCompatActivity() {

    val df = DecimalFormat.getInstance()
    private var binding: ActivityDadosSaindaFincanceiroBinding? = null
    private lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosSaindaFincanceiroBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        readdata()

        binding!!.btnSaveFinaceiro.setOnClickListener {
            startActivity(Intent(this, InputDadosSaidaFinanceiroActivity::class.java))
        }

    }

    fun readdata() {

        db = FirebaseDatabase.getInstance().getReference("Balanço Financeiro")
        db.child("Produção do Milho").get().addOnSuccessListener {

            if (it.exists()) {

                val grao = it.child("grao").value.toString()
                val grao1 = it.child("grao").value
                val rounded1 = df.format(grao1)
                val GR = grao.toDouble()
                val prodmilho = it.child("produçaodomilho").value.toString()
                val prodmilho2 = it.child("produçaodomilho").value
                val rounded2 = df.format(prodmilho2)
                val PD = prodmilho.toDouble()
                val silagem = it.child("silagem").value.toString()
                val silagem3 = it.child("silagem").value
                val rounded3 = df.format(silagem3)
                val SG = silagem.toDouble()

                binding?.textViewGraos?.text = ("$rounded1").toString()
                binding?.textViewSementes?.text = ("$rounded2").toString()
                binding?.textViewSilagem?.text = ("$rounded3").toString()

                val sm = GR + PD + SG
                val sm2 = df.format(sm)
                binding?.textViewTotalEnergetico?.text = sm2.toString()
                db.child("Soma").child("SMOUT").setValue(sm)

            }
        }
    }
}