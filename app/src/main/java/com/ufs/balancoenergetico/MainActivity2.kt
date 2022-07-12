package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufs.balancoenergetico.ActivitysBalancoEnergertico.DadoSaidaActivity
import com.ufs.balancoenergetico.ActivitysBalancoEnergertico.MainBalancoEnergeticoActivity
import com.ufs.balancoenergetico.ActivitysBalancoEnergertico.ObterBalancoActivity
import com.ufs.balancoenergetico.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private var binding:ActivityMain2Binding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.button1.setOnClickListener {
            startActivity(Intent(this, MainBalancoEnergeticoActivity::class.java))


        }

        binding!!.button2.setOnClickListener {
            startActivity(Intent(this, DadoSaidaActivity::class.java))

        }

        binding!!.button3.setOnClickListener {
            startActivity(Intent(this, ObterBalancoActivity::class.java))

        }

    }
}