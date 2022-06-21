package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.*
import com.ufs.balancoenergetico.databinding.ActivityDesenvolvimentoMilhoBinding
import com.ufs.balancoenergetico.db.dataPreparacaosolo
import com.ufs.balancoenergetico.db.datacrescimentomilho
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class DesenvolvimentoMilhoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDesenvolvimentoMilhoBinding
    private lateinit var database: DatabaseReference
    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesenvolvimentoMilhoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            val fungicida = binding.fungicida.text.toString()
            val toDoubleFungicida = fungicida.toDouble()
            val CalFungicida = (toDoubleFungicida * CfFungicida)
            val rounded1 = df.format(CalFungicida).toDouble()


            val inseticidas = binding.inseticida.text.toString()
            val toDoubleInseticida = inseticidas.toDouble()
            val CalInsenticida = (toDoubleInseticida * CfInceticida)
            val rounded2 = df.format(CalInsenticida).toDouble()


            val herbicida = binding.herbicida.text.toString()
            val toDoubleHerbicida = herbicida.toDouble()
            val CalHerbicida = (toDoubleHerbicida * CfHerbicida)
            val rounded3 = df.format(CalHerbicida).toDouble()



            val desenvolvimento = "DM"

            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
            val User = datacrescimentomilho(rounded1, rounded2, rounded3)
            database.child(desenvolvimento).setValue(User).addOnSuccessListener {

                binding.fungicida.text.clear()
                binding.inseticida.text.clear()
                binding.herbicida.text.clear()



                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

