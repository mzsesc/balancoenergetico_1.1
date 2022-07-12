package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufs.balancoenergetico.ActivitysBalancoEnergertico.DadoSaidaActivity
import com.ufs.balancoenergetico.ActivitysBalancoEnergertico.MainBalancoEnergeticoActivity
import com.ufs.balancoenergetico.databinding.ActivityEntradaeSaindaFinanceiroBinding

class EntradaeSaindaFinanceiroActivity : AppCompatActivity() {
    private var binding:ActivityEntradaeSaindaFinanceiroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEntradaeSaindaFinanceiroBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.button1.setOnClickListener {
            startActivity(Intent(this, FinaceiroActivity::class.java))


        }
        binding!!.button2.setOnClickListener {
            startActivity(Intent(this, DadosSaindaFincanceiroActivity::class.java))

        }
        binding!!.button3.setOnClickListener {
            startActivity(Intent(this, ObterBalancoFinanceiroActivity::class.java))

        }
    }
}