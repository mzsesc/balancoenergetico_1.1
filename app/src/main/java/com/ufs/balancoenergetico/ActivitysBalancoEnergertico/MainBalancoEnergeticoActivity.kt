package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityMainBalancoEnergeticoBinding
import com.ufs.balancoenergetico.db.Datasavesoma

class MainBalancoEnergeticoActivity : AppCompatActivity() {

    private var binding: ActivityMainBalancoEnergeticoBinding? = null
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBalancoEnergeticoBinding.inflate(layoutInflater)

        setContentView(binding!!.root)


        binding!!.btnSaveDados.setOnClickListener{
            startActivity(Intent(this,BalancoEnergeticoActivity::class.java))
        }


        soma()
        readData()


    }


    private fun readData() {
        db = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
        db.child("CH").get().addOnSuccessListener {

            if (it.exists()) {

                val colheitadeira = it.child("colheitadera").value
                val maodeobra = it.child("maodeobra").value
                val ensilhadeira = it.child("ensiladeira").value

                binding?.textViewColheitadeira?.text = ("$colheitadeira MJ/KG").toString()
                binding?.textViewMaoDeObra?.text = ("$maodeobra  MJ/H").toString()
                binding?.textViewEnsiladeira?.text = ("$ensilhadeira  MJ/KG").toString()
            }
        }
        db.child("DM").get().addOnSuccessListener {

            if (it.exists()) {
                val fungicida = it.child("fungicida").value
                val herbicida = it.child("herbicida").value
                val inseticidas = it.child("inseticida").value

                binding?.textViewFertilizante?.text = ("$fungicida MJ/KG").toString()
                binding?.textViewInseticidas?.text = ("$herbicida MJ/KG").toString()
                binding?.textViewPesticidas?.text = ("$inseticidas MJ/KG").toString()

            }

        }
        db.child("PS").get().addOnSuccessListener {

            if (it.exists()) {
                val oleodissel = it.child("lubrificante").value
                val lubrificante = it.child("oleodissel").value
                val trator = it.child("trator").value

                binding?.textViewOleoDissel?.text = ("$oleodissel").toString()
                binding?.textViewLubrificante?.text = lubrificante.toString()
                binding?.textViewTrator?.text = trator.toString()
            }
        }
        db.child("SA").get().addOnSuccessListener {

            if (it.exists()) {

                val tiposdesemente = it.child("tipodesemente").value
                val fertilizanteazotado = it.child("fertilizanteazotado").value
                val fertilizantepotassico = it.child("fertilizantepotassico").value
                val fertilizantefosfatado = it.child("fertilizantefosfatado").value


                binding?.textViewTipoDeSemente?.text = tiposdesemente.toString()
                binding?.textViewFertilizanteAzotado?.text = fertilizanteazotado.toString()
                binding?.textViewFertilizantePotassico?.text = fertilizantepotassico.toString()
                binding?.textViewFertilizanteFosfatato?.text = fertilizantefosfatado.toString()

            }
        }
    }



    private fun soma() {
        db = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
        db.get().addOnSuccessListener { it ->

            if (it.exists()) {
                db.child("CH").get().addOnSuccessListener { it ->
                    if (it.exists()) {

                        val colheitadera = it.child("colheitadera").value.toString()
                        val ensiladeira = it.child("ensiladeira").value.toString()
                        val maodeobra = it.child("maodeobra").value.toString()
                        val CH = colheitadera.toDouble()
                        val SA = ensiladeira.toDouble()
                        val MO = maodeobra.toDouble()

                        if (it.exists()) {
                            db.child("DM").get().addOnSuccessListener { it ->

                                val fungicida = it.child("fungicida").value.toString()
                                val herbicida = it.child("herbicida").value.toString()
                                val inseticidas = it.child("inseticida").value.toString()

                                val FC = fungicida.toDouble()
                                val HC = herbicida.toDouble()
                                val IT = inseticidas.toDouble()

                                if (it.exists()) {
                                    db.child("SA").get().addOnSuccessListener { it ->
                                        if (it.exists()) {

                                            val tiposdesemente =
                                                it.child("tipodesemente").value.toString()
                                            val fertilizanteazotado =
                                                it.child("fertilizanteazotado").value.toString()
                                            val fertilizantepotassico =
                                                it.child("fertilizantepotassico").value.toString()
                                            val fertilizantefosfatado =
                                                it.child("fertilizantefosfatado").value.toString()

                                            val TS = tiposdesemente.toDouble()
                                            val FTA = fertilizanteazotado.toDouble()
                                            val FTP = fertilizantepotassico.toDouble()
                                            val FTF = fertilizantefosfatado.toDouble()


                                            db.child("PS").get().addOnSuccessListener { it ->

                                                if (it.exists()) {
                                                    val oleodissel =
                                                        it.child("lubrificante").value.toString()
                                                    val lubrificante =
                                                        it.child("oleodissel").value.toString()
                                                    val trator = it.child("trator").value.toString()

                                                    val OD = oleodissel.toDouble()
                                                    val LB = lubrificante.toDouble()
                                                    val TT = trator.toDouble()


                                                    val soma =
                                                        CH + SA + MO + HC + IT  + FC + TS +
                                                                FTA + FTP + FTF + OD + LB + TT
                                                    val sm = ("$soma MJ")
                                                    binding?.textViewTotalEnergetico?.text = sm
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}