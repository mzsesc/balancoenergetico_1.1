package com.ufs.balancoenergetico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.databinding.ActivityPreparacaoSoloBinding
import com.ufs.balancoenergetico.db.dataPreparacaosolo

class PreparacaodoSoloActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreparacaoSoloBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreparacaoSoloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val impleAgric = binding.impleAgric.text.toString()
            val maobra = binding.maobra.text.toString()
            val oleodissel = binding.oleodissel.text.toString()
            val lubrificante = binding.lubrificante.text.toString()
            val trator = binding.trator.text.toString()

            impleAgric.toDouble() // não esta funcionando ainda tem que declarar na forma de variavel
            maobra.toDouble() // não esta funcionando ainda tem que declarar na forma de variavel
            oleodissel.toDouble() // não esta funcionando ainda tem que declarar na forma de variavel
            lubrificante.toDouble() // não esta funcionando ainda tem que declarar na forma de variavel
            trator.toDouble() // não esta funcionando ainda tem que declarar na forma de variavel

            val preparacaosolo = binding.preparacaosolo.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Preparação do solo")
            val User = dataPreparacaosolo(impleAgric,maobra,oleodissel,lubrificante,trator)
            database.child(preparacaosolo).setValue(User).addOnSuccessListener {

                binding.impleAgric.text.clear()
                binding.maobra.text.clear()
                binding.oleodissel.text.clear()
                binding.lubrificante.text.clear()
                binding.trator.text.clear()


                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}