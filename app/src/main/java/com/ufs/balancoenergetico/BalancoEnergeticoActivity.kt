package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.ufs.balancoenergetico.databinding.ActivityBalancoEnergeticoBinding

class BalancoEnergeticoActivity : AppCompatActivity() {

    private var binding: ActivityBalancoEnergeticoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBalancoEnergeticoBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        binding!!.textView23.setOnClickListener {

            startActivity(Intent(this, PreparacaodoSoloActivity::class.java))

        }
        binding!!.textView24.setOnClickListener {

            startActivity(Intent(this, SemeioeAdubacaoActivity::class.java))

        }
        binding!!.textView25.setOnClickListener {

            startActivity(Intent(this, DesenvolvimentoMilhoActivity::class.java))

        }
        binding!!.textView26.setOnClickListener {

            startActivity(Intent(this, ColheitaActivity::class.java))
            finish()

        }
    }
}