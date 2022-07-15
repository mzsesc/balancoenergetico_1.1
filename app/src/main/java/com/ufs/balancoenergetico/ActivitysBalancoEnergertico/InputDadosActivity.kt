package com.ufs.balancoenergetico.ActivitysBalancoEnergertico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ufs.balancoenergetico.*
import com.ufs.balancoenergetico.databinding.ActivityInputDadosBinding
import com.ufs.balancoenergetico.db.dataPreparacaosolo
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class InputDadosActivity : AppCompatActivity() {

    private var binding: ActivityInputDadosBinding? = null
    private lateinit var database: DatabaseReference
    val df = DecimalFormat("#.##", DecimalFormatSymbols(Locale.ENGLISH))



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDadosBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.button.setOnClickListener {
            saveDados()
            startActivity(Intent(this, MainActivity2::class.java))
            finish()

        }

    }
    fun saveDados(){
        val maodebra = binding!!.maobra.text.toString()
        val md = maodebra.toDouble()
        val CalMaoDeObra = (md * CfMaoDeObra)
        val rounded1 = df.format(CalMaoDeObra).toDouble()

        val oleodissel = binding!!.oleodissel.text.toString()
        val od = oleodissel.toDouble()
        val CalOleoDissel = (od * CfCombustivel)
        val rounded2 = df.format(CalOleoDissel).toDouble()

        val lubrificante = binding!!.lubrificante.text.toString()
        val lf = lubrificante.toDouble()
        val CalLubricante = (lf * CfLubrificante)
        val rounded3 = df.format(CalLubricante).toDouble()

        val trator = binding!!.trator.text.toString()
        val tt = trator.toDouble()
        val CalTrator = (tt * CfMaquinasAgricolas)
        val rounded4 = df.format(CalTrator).toDouble()

        val graxa = binding!!.graxa.text.toString()
        val gx = graxa.toDouble()
        val CalGraxa = (gx * CfGraxa)
        val rounded5 = df.format(CalGraxa).toDouble()

        val semeadora = binding!!.semeadora.text.toString()
        val sd = semeadora.toDouble()
        val CalSemeadora = (sd * CfMaquinasAgricolas)
        val rounded6 = df.format(CalSemeadora).toDouble()

        val pulverizador = binding!!.pulverizador.text.toString()
        val pv = pulverizador.toDouble()
        val CalPulverizador = (pv * CfMaquinasAgricolas)
        val rounded7 = df.format(CalPulverizador).toDouble()

        val gradagem = binding!!.gradagem.text.toString()
        val gd = gradagem.toDouble()
        val CalGradagem = (gd * CfMaquinasAgricolas)
        val rounded8 = df.format(CalGradagem).toDouble()

        val colhedora = binding!!.colhedora.text.toString()
        val cd = colhedora.toDouble()
        val CalColhedora = (cd * CfMaquinasAgricolas)
        val rounded9 = df.format(CalColhedora).toDouble()

        val ensiladeira = binding!!.ensiladeira.text.toString()
        val el = ensiladeira.toDouble()
        val CalEnsiladeira = (el * CfMaquinasAgricolas)
        val rounded10 = df.format(CalEnsiladeira).toDouble()

        val transporteforagem = binding!!.transporteforagem.text.toString()
        val tf = transporteforagem.toDouble()
        val CalTransporteforagem = (tf * CfMaquinasAgricolas)
        val rounded11 = df.format(CalTransporteforagem).toDouble()

        val fungicida = binding!!.fungicida.text.toString()
        val fg = fungicida.toDouble()
        val CalFungicida = (fg * CfFungicida)
        val rounded12 = df.format(CalFungicida).toDouble()

        val herbicida = binding!!.herbicida.text.toString()
        val hb = herbicida.toDouble()
        val CalHerbicida = (hb * CfHerbicida)
        val rounded13 = df.format(CalHerbicida).toDouble()

        val inseticida = binding!!.inseticida.text.toString()
        val it = inseticida.toDouble()
        val CalInseticida = (it * CfInceticida)
        val rounded14 = df.format(CalInseticida).toDouble()

        val sementes = binding!!.sementes.text.toString()
        val st = sementes.toDouble()
        val CalSementes = (st * CfTiposDeSemente)
        val rounded15 = df.format(CalSementes).toDouble()

        val fertilizanteNitogenado = binding!!.fertilizanteNitogenado.text.toString()
        val fn = fertilizanteNitogenado.toDouble()
        val CalFertilizanteNitogenado = (fn * CfFertilizanteAzotados)
        val rounded16 = df.format(CalFertilizanteNitogenado).toDouble()

        val fertilizantePotassico = binding!!.fertilizantePotassico.text.toString()
        val fp = fertilizantePotassico.toDouble()
        val CalFertilizantePotassico = (fp * CfFertilizantePotassico)
        val rounded17 = df.format(CalFertilizantePotassico).toDouble()

        val fertilizanteFosfatado = binding!!.fertilizanteFosfatado.text.toString()
        val ff = fertilizanteFosfatado.toDouble()
        val CalFertilizanteFosfatado = (ff * CfFertilizanteFosfotado)
        val rounded18 = df.format(CalFertilizanteFosfatado).toDouble()


        val preparacaosolo = "PS"
        database = FirebaseDatabase.getInstance().getReference("Balanço Energetico Dados")
        val dados = dataPreparacaosolo(
            md,
            od,
            lf,
            tt,
            gx,
            sd,
            pv,
            gd,
            cd,
            el,
            tf,
            fg,
            hb,
            it,
            st,
            fn,
            fp,
            ff
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
            rounded8,
            rounded9,
            rounded10,
            rounded11,
            rounded12,
            rounded13,
            rounded14,
            rounded15,
            rounded16,
            rounded17,
            rounded18
        )
        database.child(preparacaosolo).setValue(User).addOnSuccessListener {
            binding!!.maobra.text.clear()
            binding!!.oleodissel.text.clear()
            binding!!.lubrificante.text.toString()
            binding!!.trator.text.clear()
            binding!!.sementes.text.clear()
            binding!!.fertilizantePotassico.text.clear()
            binding!!.fertilizanteNitogenado.text.clear()
            binding!!.fertilizanteFosfatado.text.clear()
            binding!!.fungicida.text.clear()
            binding!!.herbicida.text.clear()
            binding!!.inseticida.text.clear()
            binding!!.colhedora.text.clear()
            binding!!.ensiladeira.text.clear()
            binding!!.graxa.text.clear()
            binding!!.semeadora.text.clear()
            binding!!.pulverizador.text.clear()
            binding!!.gradagem.text.clear()
            binding!!.transporteforagem.text.clear()




            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {

            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

