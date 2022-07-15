package com.ufs.balancoenergetico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufs.balancoenergetico.databinding.ActivityMainBinding
import com.ufs.balancoenergetico.databinding.ActivityRelatorioBinding

class RelatorioActivity : AppCompatActivity() {

    private var binding: ActivityRelatorioBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRelatorioBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        val input = 11
        val output = 22
        val BE = 33
        var textRelatorio = getString(R.string.Relatorio, input,output,BE)

        binding!!.textView5.text = textRelatorio
    }
}