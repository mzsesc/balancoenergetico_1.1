package com.ufs.balancoenergetico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityObterBalancoBinding

class ObterBalancoActivity : AppCompatActivity() {

    private var binding: ActivityObterBalancoBinding? = null
    private lateinit var database: DatabaseReference

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

        var teste: Double? = 1.0
        val teste1 = "Mario"

        database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
        database.get().addOnSuccessListener {

            if (it.exists()) {
                val soma = it.child("soma").value

                teste = soma as Double?

                binding?.textView3?.text = teste.toString()
            }

        }
        database = FirebaseDatabase.getInstance().getReference("Produção do Milho")
        database.get().addOnSuccessListener {

            if (it.exists()) {
                val soma = it.child("produçaodomilho").value

                teste = soma as Double?

                binding?.textView5?.text = teste.toString()
            }

        }
    }

    fun CalBalancoEnegetico() {

        val a = binding?.textView3?.text.toString()
        val b = a.toDouble()
        val c = binding?.textView5?.text.toString()
        val d = c.toDouble()

        if (b != 0.0){
            val e = b / d
            val f = d / b
            binding?.textView6?.text = e.toString()
            binding?.textView7?.text = f.toString()
        }else{
            Toast.makeText(
            baseContext,
            "Falha na autenticação.",
            Toast.LENGTH_SHORT
        ).show()


        }

    }

}
