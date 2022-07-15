package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.CfSilagem
import com.ufs.balancoenergetico.CfTiposDeSemente
import com.ufs.balancoenergetico.MainActivity
import com.ufs.balancoenergetico.MainActivity2
import com.ufs.balancoenergetico.databinding.ActivityProducaoMilhoBinding
import com.ufs.balancoenergetico.db.Datamilho
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class ProducaoMilhoActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var binding: ActivityProducaoMilhoBinding? = null
    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProducaoMilhoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.salveDados.setOnClickListener {
            saveDados()
        }


    }

    fun saveDados() {
        val prodmilho = binding!!.producaoMilho.text.toString()
        val Doubleprodmilho = prodmilho.toDouble()
        val CalProdMilho = (Doubleprodmilho * CfTiposDeSemente)
        val rounded1 = df.format(CalProdMilho).toDouble()

        val grao = binding!!.grao.text.toString()
        val DoubleGrao = grao.toDouble()
        val CalGrao = (DoubleGrao * CfTiposDeSemente)
        val rounde2 = df.format(CalGrao).toDouble()

        val silagem = binding!!.silagem.text.toString()
        val DoubleSilagem = silagem.toDouble()
        val CalSilagem = (DoubleSilagem * CfSilagem)
        val rounded3 = df.format(CalSilagem).toDouble()



        database = FirebaseDatabase.getInstance().getReference("Produção do Milho Dados")
        val dados = Datamilho(
            Doubleprodmilho,
            DoubleGrao,
            DoubleSilagem
        )
        database.setValue(dados).addOnSuccessListener {}

        database = FirebaseDatabase.getInstance().getReference("Produção do Milho")
        val promilho = Datamilho(
            rounded1,
            rounde2,
            rounded3
        )
        database.setValue(promilho).addOnSuccessListener {


            binding!!.producaoMilho.text.clear()
            binding!!.grao.text.clear()
            binding!!.silagem.text.clear()
        }
        intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
        finish()


    }
}