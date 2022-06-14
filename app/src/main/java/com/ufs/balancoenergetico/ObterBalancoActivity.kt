package com.ufs.balancoenergetico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufs.balancoenergetico.databinding.ActivityObterBalancoBinding

class ObterBalancoActivity : AppCompatActivity() {

     private var binding: ActivityObterBalancoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityObterBalancoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

    }
}