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
    val df = DecimalFormat.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDadoSaindaBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.btnSaveDados.setOnClickListener {
            startActivity(Intent(this, ProducaoMilhoActivity::class.java))
        }
        readDataProdMilho()
    }

    fun readDataProdMilho() {

        db = FirebaseDatabase.getInstance().getReference("Produção do Milho Dados")
        db.get().addOnSuccessListener {
            if (it.exists()) {

                val grao = it.child("grao").value
                val rounded1 = df.format(grao)

                val produçaodomilho = it.child("produçaodomilho").value
                val rounded2 = df.format(produçaodomilho)

                val silagem = it.child("silagem").value
                val rounded3 = df.format(silagem)

                binding?.textViewGraos?.text = ("$rounded1 kg").toString()
                binding?.textViewSementes?.text = ("$rounded2 kg").toString()
                binding?.textViewSilagem?.text = ("$rounded3 kg").toString()

            }
        }
    }
}


