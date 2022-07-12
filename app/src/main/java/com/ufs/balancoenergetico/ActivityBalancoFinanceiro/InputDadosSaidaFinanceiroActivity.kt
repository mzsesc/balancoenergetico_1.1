package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.CfSilagem
import com.ufs.balancoenergetico.CfTiposDeSemente
import com.ufs.balancoenergetico.MainActivity
import com.ufs.balancoenergetico.R
import com.ufs.balancoenergetico.databinding.ActivityInputDadosSaidaFinanceiroBinding
import com.ufs.balancoenergetico.db.Datamilho
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class InputDadosSaidaFinanceiroActivity : AppCompatActivity() {
    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
    private lateinit var database: DatabaseReference
    private var binding:ActivityInputDadosSaidaFinanceiroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputDadosSaidaFinanceiroBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.button.setOnClickListener {
            val prodmilho = binding!!.finaceiroSemente.text.toString()
            val PD = prodmilho.toDouble()

            val grao = binding!!.finaceiroGrao.text.toString()
            val GR = grao.toDouble()


            val silagem = binding!!.finaceiroSilagem.text.toString()
            val SG = silagem.toDouble()




            database = FirebaseDatabase.getInstance().getReference("Balanço Financeiro")
            val dados = Datamilho(
                PD,
                GR,
                SG
            )
            database.child("Produção do Milho").setValue(dados).addOnSuccessListener {

                binding!!.finaceiroSemente.text.clear()
                binding!!.finaceiroGrao.text.clear()
                binding!!.finaceiroSilagem.text.clear()
            }
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}