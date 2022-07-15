package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityFinaceiroBinding
import java.text.DecimalFormat

class FinaceiroActivity : AppCompatActivity() {
    val df = DecimalFormat.getInstance()
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
                val rounded1 = df.format(colheitadeira)
                val maodeobra = it.child("maodeobra").value
                val rounded2 = df.format(maodeobra)
                val ensilhadeira = it.child("ensiladeira").value
                val rounded3 = df.format(ensilhadeira)

                binding?.textViewColheitadeira?.text = (rounded1)
                binding?.textViewMaoDeObra?.text = (rounded2)
                binding?.textViewEnsiladeira?.text = (rounded3)

                val fungicida = it.child("fungicida").value
                val rounded4 = df.format(fungicida)
                val herbicida = it.child("herbicida").value
                val rounded5 = df.format(herbicida)
                val inseticidas = it.child("inseticida").value
                val rounded6= df.format(inseticidas)

                binding?.textViewFungicida?.text = (rounded4)
                binding?.textViewHerbicida?.text = (rounded5)
                binding?.textViewInseticidas?.text = (rounded6)

                val oleodissel = it.child("lubrificante").value
                val rounded7 = df.format(oleodissel)
                val lubrificante = it.child("oleodissel").value
                val rounded8 = df.format(lubrificante)
                val trator = it.child("trator").value
                val rounded9 = df.format(trator)

                binding?.textViewOleoDissel?.text = (rounded7)
                binding?.textViewLubrificante?.text = (rounded8)
                binding?.textViewTrator?.text = (rounded9)


                val tiposdesemente = it.child("tipodesemente").value
                val rounded10 = df.format(tiposdesemente)
                val fertilizanteazotado = it.child("fertilizanteazotado").value
                val rounded11 = df.format(fertilizanteazotado)
                val fertilizantepotassico = it.child("fertilizantepotassico").value
                val rounded12 = df.format(fertilizantepotassico)
                val fertilizantefosfatado = it.child("fertilizantefosfatado").value
                val rounded13 = df.format(fertilizantefosfatado)


                binding?.textViewTipoDeSemente?.text = (rounded10)
                binding?.textViewFertilizanteAzotado?.text = (rounded11)
                binding?.textViewFertilizantePotassico?.text = (rounded12)
                binding?.textViewFertilizanteFosfatato?.text = (rounded13)

                val graxa = it.child("graxa").value
                val rounded14 = df.format(graxa)
                val semeadora = it.child("semeadora").value
                val rounded15 = df.format(semeadora)
                val pulverizador = it.child("pulverizador").value
                val rounded16 = df.format(pulverizador)
                val gradagem = it.child("gradagem").value
                val rounded17 = df.format(gradagem)
                val transporteforagem = it.child("transporteforagem").value
                val rounded18 = df.format(transporteforagem)


                binding!!.textViewGraxa.text = (rounded14)
                binding!!.textViewSemeadora.text = (rounded15)
                binding!!.textViewPulverizador.text = (rounded16)
                binding!!.textViewGradagem.text = (rounded17)
                binding!!.textViewTrasporteForragem.text = (rounded18)


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
                val sm2 = df.format(soma)
                binding?.textViewTotalEnergetico?.text = sm2
                db.child("Soma").child("SMIN").setValue(soma)
            }


        }
    }
}