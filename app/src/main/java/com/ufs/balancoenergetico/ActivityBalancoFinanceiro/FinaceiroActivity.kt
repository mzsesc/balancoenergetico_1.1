package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityFinaceiroBinding

class FinaceiroActivity : AppCompatActivity() {

    private var binding: ActivityFinaceiroBinding? = null
    private lateinit var db: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinaceiroBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.btnSaveFinaceiro.setOnClickListener {
            startActivity(Intent(this, BalancoFinanceiroActivity::class.java))
        }
        readfinanceiro()
        readtotalfinanceiro()

    }

    private fun readfinanceiro() {
        db = FirebaseDatabase.getInstance().getReference("Balanço Financeiro")
        db.child("Financeiro").get().addOnSuccessListener {

            if (it.exists()) {

                val colheitadeira = it.child("colheitadera").value
                val maodeobra = it.child("maodeobra").value
                val ensilhadeira = it.child("ensiladeira").value

                binding?.textViewColheitadeira?.text = ("$colheitadeira").toString()
                binding?.textViewMaoDeObra?.text = ("$maodeobra").toString()
                binding?.textViewEnsiladeira?.text = ("$ensilhadeira").toString()

                val fungicida = it.child("fungicida").value
                val herbicida = it.child("herbicida").value
                val inseticidas = it.child("inseticida").value

                binding?.textViewFungicida?.text = ("$fungicida").toString()
                binding?.textViewHerbicida?.text = ("$herbicida").toString()
                binding?.textViewInseticidas?.text = ("$inseticidas").toString()

                val oleodissel = it.child("lubrificante").value
                val lubrificante = it.child("oleodissel").value
                val trator = it.child("trator").value

                binding?.textViewOleoDissel?.text = ("$oleodissel").toString()
                binding?.textViewLubrificante?.text = ("$lubrificante").toString()
                binding?.textViewTrator?.text = ("$trator").toString()


                val tiposdesemente = it.child("tipodesemente").value
                val fertilizanteazotado = it.child("fertilizanteazotado").value
                val fertilizantepotassico = it.child("fertilizantepotassico").value
                val fertilizantefosfatado = it.child("fertilizantefosfatado").value


                binding?.textViewTipoDeSemente?.text = ("$tiposdesemente").toString()
                binding?.textViewFertilizanteAzotado?.text = ("$fertilizanteazotado").toString()
                binding?.textViewFertilizantePotassico?.text = ("$fertilizantepotassico").toString()
                binding?.textViewFertilizanteFosfatato?.text = ("$fertilizantefosfatado").toString()

                val graxa = it.child("graxa").value
                val semeadora = it.child("semeadora").value
                val pulverizador = it.child("pulverizador").value
                val gradagem = it.child("gradagem").value
                val transporteforagem = it.child("transporteforagem").value


                binding!!.textViewGraxa.text = ("$graxa").toString()
                binding!!.textViewSemeadora.text = ("$semeadora").toString()
                binding!!.textViewPulverizador.text = ("$pulverizador").toString()
                binding!!.textViewGradagem.text = ("$gradagem").toString()
                binding!!.textViewTrasporteForragem.text = ("$transporteforagem").toString()


            }
        }


    }

    private fun readtotalfinanceiro() {
        db = FirebaseDatabase.getInstance().getReference("Balanço Financeiro")
        db.child("Financeiro").get().addOnSuccessListener {

            if (it.exists()) {
                val colheitadera = it.child("colheitadera").value.toString()
                val ensiladeira = it.child("ensiladeira").value.toString()
                val maodeobra = it.child("maodeobra").value.toString()
                val CH = colheitadera.toDouble()
                val SA = ensiladeira.toDouble()
                val MO = maodeobra.toDouble()

                val fungicida = it.child("fungicida").value.toString()
                val herbicida = it.child("herbicida").value.toString()
                val inseticidas = it.child("inseticida").value.toString()
                val FC = fungicida.toDouble()
                val HC = herbicida.toDouble()
                val IT = inseticidas.toDouble()

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

                val oleodissel =
                    it.child("lubrificante").value.toString()
                val lubrificante =
                    it.child("oleodissel").value.toString()
                val trator = it.child("trator").value.toString()
                val OD = oleodissel.toDouble()
                val LB = lubrificante.toDouble()
                val TT = trator.toDouble()

                val graxa = it.child("graxa").value.toString()
                val gx = graxa.toDouble()

                val semeadora = it.child("semeadora").value.toString()
                val sd = semeadora.toDouble()

                val pulverizador = it.child("pulverizador").value.toString()
                val pv = pulverizador.toDouble()

                val gradagem = it.child("gradagem").value.toString()
                val gd = gradagem.toDouble()

                val transporteforagem = it.child("transporteforagem").value.toString()
                val tf = transporteforagem.toDouble()


                val soma =
                    CH + SA + MO + HC + IT + FC + TS +
                            FTA + FTP + FTF + OD + LB + TT + gx + pv + gd + tf + sd
                val sm = ("$soma")
                binding?.textViewTotalEnergetico?.text = sm
                db.child("Soma").child("SMIN").setValue(soma)
            }


        }
    }
}