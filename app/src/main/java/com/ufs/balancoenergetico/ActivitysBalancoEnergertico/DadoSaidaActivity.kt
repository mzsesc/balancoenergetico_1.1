package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityDadoSaindaBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class DadoSaidaActivity : AppCompatActivity() {
    private var binding: ActivityDadoSaindaBinding? = null
    private lateinit var db: DatabaseReference
    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDadoSaindaBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.btnSaveDados.setOnClickListener {
            startActivity(Intent(this, InputProducaoMilhoActivity::class.java))
        }
        readDataProdMilho()
    }

    fun readDataProdMilho() {

        db = FirebaseDatabase.getInstance().getReference("Produção do Milho Dados")
        db.get().addOnSuccessListener {
            if (it.exists()) {

                val grao = it.child("grao").value
                val rounded1 = df.format(grao).toDouble()

                val produçaodomilho = it.child("produçaodomilho").value
                val rounded2 = df.format(produçaodomilho).toDouble()

                val silagem = it.child("silagem").value
                val rounded3 = df.format(silagem).toDouble()

                binding?.textViewGraos?.text = ("$rounded1 kg").toString()
                binding?.textViewSementes?.text = ("$rounded2 kg").toString()
                binding?.textViewSilagem?.text = ("$rounded3 kg").toString()

            }
        }
    }
}


