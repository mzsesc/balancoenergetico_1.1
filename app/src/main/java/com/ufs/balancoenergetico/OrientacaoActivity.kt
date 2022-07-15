package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufs.balancoenergetico.databinding.ActivityOrientacaoBinding

class OrientacaoActivity : AppCompatActivity() {

    private var binding: ActivityOrientacaoBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrientacaoBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        var orientacao1 = getString(R.string.Orientação1)
        var orientacao2 = getString(R.string.Orientação2)
        var orientacao3 = getString(R.string.Orientação3)

        binding?.orientacao1?.text =orientacao1
        binding?.orientacao2?.text =orientacao2
        binding?.orientacao3?.text =orientacao3

        binding!!.button4.setOnClickListener {
            val intent = Intent(this, InitialMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}