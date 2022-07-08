package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufs.balancoenergetico.R
import com.ufs.balancoenergetico.databinding.ActivityBalancoFinanceiroMainBinding
import com.ufs.balancoenergetico.databinding.ActivityObterBalancoFinanceiroBinding

class BalancoFinanceiroMainActivity : AppCompatActivity() {

    private var binding: ActivityBalancoFinanceiroMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBalancoFinanceiroMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.button16.setOnClickListener {
            startActivity(Intent(this,BalancoFinanceiroActivity::class.java))
        }
        binding!!.button17.setOnClickListener {
            startActivity(Intent(this,ObterBalancoFinanceiroActivity::class.java))

        }
    }
}