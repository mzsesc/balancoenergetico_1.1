package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.R
import com.ufs.balancoenergetico.databinding.ActivityObterBalancoFinanceiroBinding

class ObterBalancoFinanceiroActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference

    private var binding: ActivityObterBalancoFinanceiroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityObterBalancoFinanceiroBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.button9?.setOnClickListener {
            calBalancoFinanceiro()

        }
    }

    fun calBalancoFinanceiro() {
        db = FirebaseDatabase.getInstance().getReference("Balanço Financeiro")
        db.child("Soma").get().addOnSuccessListener {

            if (it.exists()) {

                val SMIN = it.child("SMIN").value.toString()
                val input = SMIN.toDouble()
                binding?.textViewTotalEnergetico?.text = SMIN
                val SMOUT = it.child("SMOUT").value.toString()
                binding?.textViewTotalEnergeticoProduzido?.text = SMOUT
                val output = SMOUT.toDouble()

                val soma = output - input

                binding?.textViewTotalEficaciaEnergetica?.text = soma.toString()
                db.child("Soma").child("Balanço Financeiro").setValue(soma)


            }
        }

    }

}