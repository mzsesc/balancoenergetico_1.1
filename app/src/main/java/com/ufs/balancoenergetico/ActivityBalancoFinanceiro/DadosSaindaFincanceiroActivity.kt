package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityDadosSaindaFincanceiroBinding

class DadosSaindaFincanceiroActivity : AppCompatActivity() {
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
                val GR = grao.toDouble()
                val prodmilho = it.child("produçaodomilho").value.toString()
                val PD = prodmilho.toDouble()
                val silagem = it.child("silagem").value.toString()
                val SG = silagem.toDouble()

                binding?.textViewGraos?.text = ("$grao").toString()
                binding?.textViewSementes?.text = ("$prodmilho").toString()
                binding?.textViewSilagem?.text = ("$silagem").toString()

                val sm = GR + PD + SG
                binding?.textViewTotalEnergetico?.text = sm.toString()
                db.child("Soma").child("SMOUT").setValue(sm)

            }
        }
    }
}