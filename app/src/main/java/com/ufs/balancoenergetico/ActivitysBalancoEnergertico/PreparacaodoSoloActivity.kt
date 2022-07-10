package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.*
import com.ufs.balancoenergetico.databinding.ActivityPreparacaoSoloBinding
import com.ufs.balancoenergetico.db.dataPreparacaosolo
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class PreparacaodoSoloActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreparacaoSoloBinding
    private lateinit var database: DatabaseReference
    val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreparacaoSoloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val maodebra = binding.maobra.text.toString()
            val toDoubleMaoDeObra = maodebra.toDouble()
            val CalMaoDeObra = (toDoubleMaoDeObra * CfMaoDeObra)
            val rounded1 = df.format(CalMaoDeObra).toDouble()

            val oleodissel = binding.oleodissel.text.toString()
            val toDoubleOleoDissel = oleodissel.toDouble()
            val CalOleoDissel = (toDoubleOleoDissel * CfCombustivel)
            val rounded2 = df.format(CalOleoDissel).toDouble()

            val lubrificante = binding.lubrificante.text.toString()
            val toDoubleLubrificante = lubrificante.toDouble()
            val CalLubricante = (toDoubleLubrificante * CfLubrificante)
            val rounded3 = df.format(CalLubricante).toDouble()


            val trator = binding.trator.text.toString()
            val toDoubleTrator = trator.toDouble()
            val CalTrator = (toDoubleTrator * CfMaquinasAgricolas)
            val rounded4 = df.format(CalTrator).toDouble()

            val graxa = binding.graxa.text.toString()
            val toDoubleGraxa = graxa.toDouble()
            val CalGraxa = (toDoubleGraxa * CfGraxa)
            val rounded5 = df.format(CalGraxa).toDouble()

            val semeadora = binding.semeadora.text.toString()
            val toDoubleSemeadora = semeadora.toDouble()
            val CalSemeadora = (toDoubleSemeadora * CfMaquinasAgricolas)
            val rounded6 = df.format(CalSemeadora).toDouble()

            val pulverizador = binding.pulverizador.text.toString()
            val toDoublePulverizador = pulverizador.toDouble()
            val CalPulverizador = (toDoublePulverizador * CfMaquinasAgricolas)
            val rounded7 = df.format(CalPulverizador).toDouble()

            val gradagem = binding.gradagem.text.toString()
            val toDoubleGradagem = gradagem.toDouble()
            val CalGradagem = (toDoubleGradagem * CfMaquinasAgricolas)
            val rounded8 = df.format(CalGradagem).toDouble()


            val preparacaosolo = "PS"
            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico Dados")
            val dados = dataPreparacaosolo(
                toDoubleOleoDissel,
                toDoubleLubrificante,
                toDoubleMaoDeObra,
                toDoubleTrator,
                toDoubleGraxa,
                toDoubleSemeadora,
                toDoublePulverizador,
                toDoubleGradagem
            )
            database.child(preparacaosolo).setValue(dados).addOnSuccessListener {}

            database = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
            val User = dataPreparacaosolo(
                rounded1,
                rounded2,
                rounded3,
                rounded4,
                rounded5,
                rounded6,
                rounded7,
                rounded8
            )
            database.child(preparacaosolo).setValue(User).addOnSuccessListener {

                binding.maobra.text.clear()
                binding.oleodissel.text.clear()
                binding.lubrificante.text.clear()
                binding.trator.text.clear()
                binding.graxa.text.clear()
                binding.semeadora.text.clear()
                binding.pulverizador.text.clear()
                binding.gradagem.text.clear()


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