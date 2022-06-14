package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import com.ufs.balancoenergetico.ObterBalancoActivity
import com.ufs.balancoenergetico.databinding.ActivityBalancoEnergeticoBinding

class BalancoEnergeticoActivity : AppCompatActivity() {

    private var binding: ActivityBalancoEnergeticoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBalancoEnergeticoBinding.inflate(layoutInflater)

        setContentView(binding!!.root)


        //val userEmail = intent.getStringExtra(EXTRA_MESSAGE)

        binding!!.button16.setOnClickListener {

            startActivity(Intent(this, PreparacaodoSoloActivity::class.java))
            onBackPressed()

        }
        binding!!.button17.setOnClickListener {

            startActivity(Intent(this, SemeioeAdubacaoActivity::class.java))
            onBackPressed()

        }
        binding!!.button18.setOnClickListener {

            startActivity(Intent(this, DesenvolvimentoMilhoActivity::class.java))
            onBackPressed()

        }
        binding!!.button19.setOnClickListener {

            startActivity(Intent(this, ColheitaActivity::class.java))
            onBackPressed()

        }

        binding!!.button20.setOnClickListener {
            startActivity(Intent(this, ProducaoMilhoActivity::class.java))
            onBackPressed()

        }

        binding!!.button21.setOnClickListener {

            startActivity(Intent(this, ObterBalancoActivity::class.java))
            onBackPressed()

        }


    }
}