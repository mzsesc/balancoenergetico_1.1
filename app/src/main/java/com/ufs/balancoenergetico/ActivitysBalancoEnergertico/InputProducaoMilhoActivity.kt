package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufs.balancoenergetico.MainActivity
import com.ufs.balancoenergetico.databinding.ActivityInputProducaoMilhoBinding

class InputProducaoMilhoActivity : AppCompatActivity() {
    private var binding:ActivityInputProducaoMilhoBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputProducaoMilhoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.prodmilho?.setOnClickListener {
            startActivity(Intent(this, ProducaoMilhoActivity::class.java))
        }
    }
}