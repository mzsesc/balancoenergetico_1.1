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

        binding!!.button4.setOnClickListener {
            val intent = Intent(this, InitialMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}