package com.ufs.balancoenergetico.ActivityBalancoFinanceiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufs.balancoenergetico.R
import com.ufs.balancoenergetico.databinding.ActivityObterBalancoFinanceiroBinding

class ObterBalancoFinanceiroActivity : AppCompatActivity() {


    private var binding: ActivityObterBalancoFinanceiroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityObterBalancoFinanceiroBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }
}