package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityMainBalancoEnergeticoBinding
import java.text.DecimalFormat

class MainBalancoEnergeticoActivity : AppCompatActivity() {

    val df = DecimalFormat.getInstance()

    private var binding: ActivityMainBalancoEnergeticoBinding? = null
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBalancoEnergeticoBinding.inflate(layoutInflater)

        setContentView(binding!!.root)


        binding!!.btnSaveDados.setOnClickListener {
            startActivity(Intent(this, InputDadosActivity::class.java))
        }



        readData()
        soma()


    }


    private fun readData() {

        db = FirebaseDatabase.getInstance().getReference("Balanço Energetico Dados")
        db.child("PS").get().addOnSuccessListener {
            if (it.exists()) {

                val colheitadeira = it.child("colheitadera").value
                val rounded1 = df.format(colheitadeira)
                val maodeobra = it.child("maodeobra").value
                val rounded2 = df.format(maodeobra)
                val ensilhadeira = it.child("ensiladeira").value
                val rounded3 = df.format(ensilhadeira)
                val forragem = it.child("transporteforagem").value
                val rounded4 = df.format(forragem)
                val tiposdesemente = it.child("tipodesemente").value
                val rounded5 = df.format(tiposdesemente)
                val fertilizanteazotado = it.child("fertilizanteazotado").value
                val rounded6 = df.format(fertilizanteazotado)
                val fertilizantepotassico = it.child("fertilizantepotassico").value
                val rounded7 = df.format(fertilizantepotassico)
                val fertilizantefosfatado = it.child("fertilizantefosfatado").value
                val rounded8 = df.format(fertilizantefosfatado)
                val oleodissel = it.child("oleodissel").value
                val rounded9 = df.format(oleodissel)
                val lubrificante = it.child("lubrificante").value
                val rounded10 = df.format(lubrificante)
                val trator = it.child("trator").value
                val rounded11 = df.format(trator)
                val graxa = it.child("graxa").value
                val rounded12 = df.format(graxa)
                val semeadora = it.child("semeadora").value
                val rounded13 = df.format(semeadora)
                val pulverizador = it.child("pulverizador").value
                val rounded14 = df.format(pulverizador)
                val gradagem = it.child("gradagem").value
                val rounded15 = df.format(gradagem)
                val fungicida = it.child("fungicida").value
                val rounded16 = df.format(fungicida)
                val herbicida = it.child("herbicida").value
                val rounded17 = df.format(herbicida)
                val inseticidas = it.child("inseticida").value
                val rounded18 = df.format(inseticidas)

                binding?.textViewColheitadeira?.text = ("$rounded1 h").toString()
                binding?.textViewMaoDeObra?.text = ("$rounded2 h").toString()
                binding?.textViewEnsiladeira?.text = ("$rounded3 h").toString()
                binding?.textViewTrasporteForragem?.text = ("$rounded4 h").toString()
                binding?.textViewTipoDeSemente?.text = ("$rounded5 kg").toString()
                binding?.textViewFertilizanteAzotado?.text = ("$rounded6 kg").toString()
                binding?.textViewFertilizantePotassico?.text = ("$rounded7 kg").toString()
                binding?.textViewFertilizanteFosfatato?.text = ("$rounded8 kg").toString()
                binding?.textViewOleoDissel?.text = ("$rounded9 L").toString()
                binding?.textViewLubrificante?.text = ("$rounded10 L").toString()
                binding?.textViewTrator?.text = ("$rounded11 h").toString()
                binding?.textViewGraxa?.text = ("$rounded12 kg").toString()
                binding?.textViewSemeadora?.text = ("$rounded13 h").toString()
                binding?.textViewPulverizador?.text = ("$rounded14 h").toString()
                binding?.textViewGradagem?.text = ("$rounded15 h").toString()
                binding?.textViewFungicida?.text = ("$rounded16 kg").toString()
                binding?.textViewHerbicida?.text = ("$rounded17 kg").toString()
                binding?.textViewInseticidas?.text = ("$rounded18 kg").toString()


            }
        }
        /**
        db.child("DM").get().addOnSuccessListener {

            if (it.exists()) {


            }

        }
        db.child("PS").get().addOnSuccessListener {

            if (it.exists()) {


            }
        }
        db.child("SA").get().addOnSuccessListener {

            if (it.exists()) {


            }
        }
***/
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
                        val transpoteforragem = it.child("transporteforagem").value.toString()
                        val CH = colheitadera.toDouble()
                        val SA = ensiladeira.toDouble()
                        val MO = maodeobra.toDouble()
                        val TF = transpoteforragem.toDouble()

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
                                                    val gradagem =
                                                        it.child("gradagem").value.toString()
                                                    val graxa = it.child("graxa").value.toString()
                                                    val pulverizador =
                                                        it.child("pulverizador").value.toString()
                                                    val semeadora =
                                                        it.child("semeadora").value.toString()
                                                    val trator = it.child("trator").value.toString()
                                                    val maodeobra =
                                                        it.child("maodeobra").value.toString()


                                                    val OD = oleodissel.toDouble()
                                                    val LB = lubrificante.toDouble()
                                                    val GD = gradagem.toDouble()
                                                    val GX = graxa.toDouble()
                                                    val SD = semeadora.toDouble()
                                                    val PV = pulverizador.toDouble()
                                                    val TT = trator.toDouble()
                                                    val MDB = maodeobra.toDouble()


                                                    val soma =
                                                        CH + MDB + SA + MO + TF + HC + IT + FC + TS + FTA + FTP + FTF + OD + LB + GD + GX + SD + PV + TT
                                                    val rounded1 = df.format(soma)
                                                    binding?.textViewTotalEnergetico?.text = rounded1

                                                    db.child("soma").setValue(soma)
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