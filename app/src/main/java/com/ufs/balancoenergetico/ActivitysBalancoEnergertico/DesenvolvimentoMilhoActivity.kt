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

class DesenvolvimentoMilhoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDesenvolvimentoMilhoBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesenvolvimentoMilhoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            val fungicida = binding.fungicida.text.toString()
            val toDoubleFungicida = fungicida.toDouble()
            val CalFungicida = (toDoubleFungicida * CfFungicida)

            val inseticidas = binding.inseticida.text.toString()
            val toDoubleInseticida = inseticidas.toDouble()
            val CalInsenticida = (toDoubleInseticida * CfInceticida)

            val herbicida = binding.herbicida.text.toString()
            val toDoubleHerbicida = herbicida.toDouble()
            val CalHerbicida = (toDoubleHerbicida * CfHerbicida)


            val desenvolvimento = "DM"

            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
            val User = datacrescimentomilho(CalFungicida, CalHerbicida, CalInsenticida)
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

