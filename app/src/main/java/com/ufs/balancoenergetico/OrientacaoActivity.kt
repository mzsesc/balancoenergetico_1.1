package com.ufs.balancoenergetico

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ufs.balancoenergetico.databinding.ActivityOrientacaoBinding

class OrientacaoActivity : AppCompatActivity() {

    private var binding: ActivityOrientacaoBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrientacaoBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        binding?.orientacao1?.text = getString(R.string.Orientação1)
        binding?.orientacao2?.text =  getString(R.string.Orientação2)
        binding?.orientacao3?.text = getString(R.string.Orientação3)
        binding!!.obs.text = getString(R.string.obs)


        binding!!.button4.setOnClickListener {
            val intent = Intent(this, InitialMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}