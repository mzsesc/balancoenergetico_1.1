package com.ufs.balancoenergetico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityMainBinding
import com.ufs.balancoenergetico.databinding.ActivityRelatorioBinding
import java.text.DecimalFormat

class RelatorioActivity : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    val df = DecimalFormat.getInstance()

    private var binding: ActivityRelatorioBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRelatorioBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        binding!!.button7.setOnClickListener {
            balacoenergetico()
            balacofinanceiro()
        }
    }

    fun balacofinanceiro(){
        db = FirebaseDatabase.getInstance().getReference("Balanço Financeiro")
        db.child("Soma").get().addOnSuccessListener {

            if (it.exists()){
                val financeiro = it.child("Balanço Financeiro").value.toString()
                val rd = financeiro.toDouble()
                binding!!.textView7.text = getString(R.string.RelatorioFinanceiro,financeiro)
                if (rd > 0.0 ){
                    binding!!.textView8.text = getString(R.string.FinanceiroPositivo)
                    binding!!.textView12.text = getString(R.string.saudação)
                }else{
                    binding!!.textView8.text = getString(R.string.FinanceiroNegativo)
                    binding!!.textView12.text = getString(R.string.saudação)

                }
            }

        }
    }

    fun balacoenergetico(){
        db = FirebaseDatabase.getInstance().getReference("Eficacia Energetica,Razao Energetica,Balanco Energetico")
        db.get().addOnSuccessListener {

            if (it.exists()){
                val energetico = it.child("BE").value.toString()
                val rd = energetico.toDouble()
                binding!!.textView5.text = getString(R.string.RelatorioEnergetico,energetico)

                if (rd > 0.0 ){
                    binding!!.textView6.text = getString(R.string.EnergeticoPositivo)
                    binding!!.textView12.text = getString(R.string.saudação)
                }else{
                    binding!!.textView6.text = getString(R.string.EnergeticoNegativo)
                    binding!!.textView12.text = getString(R.string.saudação)

                }
            }

        }
    }
}