package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.MainActivity
import com.ufs.balancoenergetico.databinding.ActivityBalancoFinanceiroBinding
import com.ufs.balancoenergetico.databinding.ActivityMainBinding
import com.ufs.balancoenergetico.db.DataFinanceiro

class BalancoFinanceiroActivity : AppCompatActivity() {

    private var binding: ActivityBalancoFinanceiroBinding? = null
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBalancoFinanceiroBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.button.setOnClickListener {
            financeiro()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun financeiro() {

        val oleodiesel = binding!!.oleodissel.text.toString()
        val od = oleodiesel.toDouble()

        val lubrificante = binding!!.lubrificante.text.toString()
        val lb = lubrificante.toDouble()

        val maodeobra = binding!!.maobra.text.toString()
        val mb = maodeobra.toDouble()

        val trator = binding!!.trator.text.toString()
        val tt = trator.toDouble()

        val sementes = binding!!.sementes.text.toString()
        val st = sementes.toDouble()

        val fertilizanteNitogenado = binding!!.fertilizanteNitogenado.text.toString()
        val fn = fertilizanteNitogenado.toDouble()

        val fertilizantePotassico = binding!!.fertilizantePotassico.text.toString()
        val fp = fertilizantePotassico.toDouble()

        val fertilizanteFosfatado = binding!!.fertilizanteFosfatado.text.toString()
        val ff = fertilizanteFosfatado.toDouble()

        val fungicida = binding!!.fungicida.text.toString()
        val fg = fungicida.toDouble()

        val herbicida = binding!!.herbicida.text.toString()
        val hb = herbicida.toDouble()

        val inseticida = binding!!.inseticida.text.toString()
        val it = inseticida.toDouble()

        val colhedora = binding!!.colhedora.text.toString()
        val cd = colhedora.toDouble()

        val ensiladeira = binding!!.ensiladeira.text.toString()
        val el = ensiladeira.toDouble()

        val graxa = binding!!.graxa.text.toString()
        val gx = graxa.toDouble()

        val semeadora = binding!!.semeadora.text.toString()
        val sd = semeadora.toDouble()

        val pulverizador = binding!!.pulverizador.text.toString()
        val pv = pulverizador.toDouble()

        val gradagem = binding!!.gradagem.text.toString()
        val gd = gradagem.toDouble()

        val transporteforagem = binding!!.transporteforagem.text.toString()
        val tf = transporteforagem.toDouble()







        database = FirebaseDatabase.getInstance().getReference("Balanço Financeiro")
        val financeiro = DataFinanceiro(od, lb, mb, tt, st, fn, fp, ff, fg, hb, it, cd, el,gx,sd,pv,gd,tf)
        database.child("Financeiro").setValue(financeiro).addOnSuccessListener {

            binding!!.oleodissel.text.clear()
            binding!!.lubrificante.text.toString()
            binding!!.maobra.text.clear()
            binding!!.trator.text.clear()
            binding!!.sementes.text.clear()
            binding!!.fertilizanteNitogenado.text.clear()
            binding!!.fertilizantePotassico.text.clear()
            binding!!.fertilizanteFosfatado.text.clear()
            binding!!.fungicida.text.clear()
            binding!!.herbicida.text.clear()
            binding!!.inseticida.text.clear()
            binding!!.colhedora.text.clear()
            binding!!.ensiladeira.text.clear()

        }



    }
}