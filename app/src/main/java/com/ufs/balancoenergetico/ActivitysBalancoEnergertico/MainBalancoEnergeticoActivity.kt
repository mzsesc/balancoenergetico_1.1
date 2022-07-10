package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityMainBalancoEnergeticoBinding
import com.ufs.balancoenergetico.db.Datasavesoma
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class MainBalancoEnergeticoActivity : AppCompatActivity() {

    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))

    private var binding: ActivityMainBalancoEnergeticoBinding? = null
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBalancoEnergeticoBinding.inflate(layoutInflater)

        setContentView(binding!!.root)


        binding!!.btnSaveDados.setOnClickListener{
            startActivity(Intent(this,BalancoEnergeticoActivity::class.java))
        }



        readData()
        soma()


    }


    private fun readData() {

        db = FirebaseDatabase.getInstance().getReference("Balanço Energetico Dados")
        db.child("CH").get().addOnSuccessListener {
            if (it.exists()) {

                val colheitadeira = it.child("colheitadera").value
                val rounded1 = df.format(colheitadeira).toDouble()
                val maodeobra = it.child("maodeobra").value
                val rounded2 = df.format(maodeobra).toDouble()
                val ensilhadeira = it.child("ensiladeira").value
                val rounded3 = df.format(ensilhadeira).toDouble()
                val forragem = it.child("transporteforagem").value
                val rounded4 = df.format(forragem).toDouble()



                binding?.textViewColheitadeira?.text = ("$rounded1 h").toString()
                binding?.textViewMaoDeObra?.text =  ("$rounded2 h").toString()
                binding?.textViewEnsiladeira?.text = ("$rounded3 h").toString()
                binding?.textViewTrasporteForragem?.text = ("$rounded4 h").toString()


            }
        }
        db.child("DM").get().addOnSuccessListener {

            if (it.exists()) {
                val fungicida = it.child("fungicida").value
                val rounded1 = df.format(fungicida).toDouble()
                val herbicida = it.child("herbicida").value
                val rounded2 = df.format(herbicida).toDouble()
                val inseticidas = it.child("inseticida").value
                val rounded3 = df.format(inseticidas).toDouble()

                binding?.textViewHerbicida?.text = ("$rounded1 kg").toString()
                binding?.textViewInseticidas?.text = ("$rounded2 kg").toString()
                binding?.textViewPesticidas?.text = ("$rounded3 kg").toString()

            }

        }
        db.child("PS").get().addOnSuccessListener {

            if (it.exists()) {
                val oleodissel = it.child("lubrificante").value
                val rounded1 = df.format(oleodissel).toDouble()

                val lubrificante = it.child("oleodissel").value
                val rounded2 = df.format(lubrificante).toDouble()

                val trator = it.child("trator").value
                val rounded3 = df.format(trator).toDouble()

                val graxa = it.child("graxa").value
                val rounded4 = df.format(graxa).toDouble()

                val semeadora = it.child("semeadora").value
                val rounded5 = df.format(semeadora).toDouble()

                val pulverizador = it.child("pulverizador").value
                val rounded6 = df.format(pulverizador).toDouble()

                val gradagem = it.child("gradagem").value
                val rounded7 = df.format(gradagem).toDouble()


                binding?.textViewOleoDissel?.text = ("$rounded1 L").toString()
                binding?.textViewLubrificante?.text = ("$rounded2 L").toString()
                binding?.textViewTrator?.text = ("$rounded3 h").toString()
                binding?.textViewGraxa?.text = ("$rounded4 kg").toString()
                binding?.textViewSemeadora?.text = ("$rounded5 h").toString()
                binding?.textViewPulverizador?.text = ("$rounded6 h").toString()
                binding?.textViewGradagem?.text = ("$rounded7 h").toString()
            }
        }
        db.child("SA").get().addOnSuccessListener {

            if (it.exists()) {

                val tiposdesemente = it.child("tipodesemente").value
                val rounded1 = df.format(tiposdesemente).toDouble()
                val fertilizanteazotado = it.child("fertilizanteazotado").value
                val rounded2 = df.format(fertilizanteazotado).toDouble()
                val fertilizantepotassico = it.child("fertilizantepotassico").value
                val rounded3 = df.format(fertilizantepotassico).toDouble()
                val fertilizantefosfatado = it.child("fertilizantefosfatado").value
                val rounded4 = df.format(fertilizantefosfatado).toDouble()


                binding?.textViewTipoDeSemente?.text = ("$rounded1 kg").toString()
                binding?.textViewFertilizanteAzotado?.text = ("$rounded2 kg").toString()
                binding?.textViewFertilizantePotassico?.text = ("$rounded3 kg").toString()
                binding?.textViewFertilizanteFosfatato?.text = ("$rounded4 kg").toString()

            }
        }

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
                                                    val oleodissel = it.child("lubrificante").value.toString()
                                                    val lubrificante = it.child("oleodissel").value.toString()
                                                    val gradagem = it.child("gradagem").value.toString()
                                                    val graxa = it.child("graxa").value.toString()
                                                    val pulverizador = it.child("pulverizador").value.toString()
                                                    val semeadora = it.child("semeadora").value.toString()
                                                    val trator = it.child("trator").value.toString()
                                                    val maodeobra = it.child("maodeobra").value.toString()



                                                    val OD = oleodissel.toDouble()
                                                    val LB = lubrificante.toDouble()
                                                    val GD = gradagem.toDouble()
                                                    val GX = graxa.toDouble()
                                                    val SD = semeadora.toDouble()
                                                    val PV = pulverizador.toDouble()
                                                    val TT = trator.toDouble()
                                                    val MDB = maodeobra.toDouble()



                                                    val soma =
                                                        CH + MDB + SA + MO + TF + HC + IT  + FC + TS + FTA + FTP + FTF + OD + LB + GD + GX + SD + PV + TT
                                                    val sm = soma
                                                    val rounded = df.format(sm).toDouble()

                                                    binding?.textViewTotalEnergetico?.text = rounded.toString()

                                                   db.child("soma").setValue(rounded)
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