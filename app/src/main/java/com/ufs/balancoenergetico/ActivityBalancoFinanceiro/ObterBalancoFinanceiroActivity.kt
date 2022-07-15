package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.R
import com.ufs.balancoenergetico.databinding.ActivityObterBalancoFinanceiroBinding
import java.text.DecimalFormat

class ObterBalancoFinanceiroActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    val df = DecimalFormat.getInstance()
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

                val SMIN = it.child("SMIN").value
                val rounded1 = df.format(SMIN)
                val smin = SMIN.toString()
                val input = smin.toDouble()

                binding?.textViewTotalEnergetico?.text = rounded1

                val SMOUT = it.child("SMOUT").value
                val rounded2 = df.format(SMOUT)
                val smout = SMOUT.toString()

                binding?.textViewTotalEnergeticoProduzido?.text = rounded2
                val output = smout.toDouble()

                val soma = output - input
                val rounded3 = df.format(soma)
                binding?.textViewTotalEficaciaEnergetica?.text = rounded3.toString()
                db.child("Soma").child("Balanço Financeiro").setValue(soma)


            }
        }

    }

}