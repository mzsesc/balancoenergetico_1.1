package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.ufs.balancoenergetico.CfMaoDeObra
import com.ufs.balancoenergetico.CfMaquinasAgricolas
import com.ufs.balancoenergetico.MainActivity
import com.ufs.balancoenergetico.R
import com.ufs.balancoenergetico.databinding.ActivityColheitaBinding
import com.ufs.balancoenergetico.db.datacolheita
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class ColheitaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColheitaBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityColheitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        binding.salveDados.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.colhedora.text.toString()) -> {
                    val txt = findViewById<EditText>(R.id.colhedora)
                    txt.setText("0")
                }
                TextUtils.isEmpty(binding.maodeobra.text) -> {
                    val txt1 = findViewById<EditText>(R.id.colhedora)
                    txt1.setText("0")
                }
                TextUtils.isEmpty(binding.transporteForragem.text) -> {
                    val txt2 = findViewById<EditText>(R.id.colhedora)
                    txt2.setText("0")
                }
                else -> {

                    val colheitadeira = binding.colhedora.text.toString()
                    val toDoubleColheitadeira = colheitadeira.toDouble()
                    val CalColheita =(toDoubleColheitadeira * CfMaquinasAgricolas)
                    val rounded1 = df.format(CalColheita).toDouble()


                    val maodeobra = binding.maodeobra.text.toString()
                    val toDoubleMaoDeObra = maodeobra.toDouble()
                    val CalMaoDeObra = (toDoubleMaoDeObra * CfMaoDeObra)
                    val rounded2 = df.format(CalMaoDeObra).toDouble()


                    val ensiladeira = binding.transporteForragem.text.toString()
                    val toDoubleEnsilhadeira = ensiladeira.toDouble()
                    val CalEnsilhadeira = (toDoubleEnsilhadeira * CfMaquinasAgricolas)
                    val rounded3 = df.format(CalEnsilhadeira).toDouble()

                    val forragem = binding.transporteForragem.text.toString()
                    val toDoubleForragem = forragem.toDouble()
                    val CalForragem = (toDoubleForragem * CfMaquinasAgricolas)
                    val rounded4 = df.format( CalForragem).toDouble()

                    val colheita = "CH"

                    database = FirebaseDatabase.getInstance().getReference("Balanço Energetico Dados")
                    val dados = datacolheita(
                        toDoubleColheitadeira,
                        toDoubleMaoDeObra,
                        toDoubleEnsilhadeira,
                        toDoubleForragem                    )
                    database.child(colheita).setValue(dados).addOnSuccessListener {}

                    database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
                    val User = datacolheita(
                        rounded1,
                        rounded2,
                        rounded3,
                        rounded4)
                    database.child(colheita).setValue(User).addOnSuccessListener {

                        binding.colhedora.text.clear()
                        binding.maodeobra.text.clear()
                        binding.ensiladeira.text.clear()
                        binding.transporteForragem.text.clear()


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
    }
}