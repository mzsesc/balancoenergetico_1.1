package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.ufs.balancoenergetico.ActivityBalancoFinanceiro.EntradaeSaindaFinanceiroActivity
import com.ufs.balancoenergetico.ActivityBalancoFinanceiro.FinaceiroActivity
import com.ufs.balancoenergetico.ActivitysBalancoEnergertico.MainBalancoEnergeticoActivity
import com.ufs.balancoenergetico.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference
   // val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        auth = Firebase.auth

        updateUI()
        //readDataSoma()
        //readDataProdMilho()
        //readDataEE()



        binding!!.logoutbtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, InitialMainActivity::class.java))
        }

        binding!!.button5.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))

        }

        binding!!.Financeiro.setOnClickListener {

            startActivity(Intent(this, EntradaeSaindaFinanceiroActivity::class.java))
        }

        binding!!.button6.setOnClickListener {
            startActivity(Intent(this,RelatorioActivity::class.java))

        }
    }



    private fun updateUI() {
        val usuario: FirebaseUser? = auth.currentUser
        try {
            binding?.idemail?.text = usuario?.email
        } catch (e: Exception) {
            binding?.idemail?.text = ""
        }


        // aqui cria o usuario no realtime o problema é que sobrescreve todos os dados após ser chamado
        //Então tem que fazer o tratamento desses dados
/*
        val USUARIO = binding!!.idemail.text.toString()
        val Users = "Usuarios:"

        database = FirebaseDatabase.getInstance().reference
        val User = DataUsuario(USUARIO)
        database.child(Users).setValue(User).addOnSuccessListener {}

 */
    }

/***
    private fun readDataSoma() {
        db = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
        db.get().addOnSuccessListener {

            if (it.exists()) {

                val soma = it.child("soma").value

                binding?.textViewTotalEnergetico?.text = soma.toString()

            }
        }
    }

    private fun readDataProdMilho() {
        db = FirebaseDatabase.getInstance().getReference("Produção do Milho")
        db.get().addOnSuccessListener {

            if (it.exists()) {

                val prod = it.child("produçaodomilho").value

                binding?.textViewTotalEnergeticoProduzido?.text = prod.toString()

            }
        }
    }

    private fun readDataEE() {
        db = FirebaseDatabase.getInstance().getReference("Eficacia Energetica,Razao Energetica,Balanco Energetico")
        db.get().addOnSuccessListener {

            if (it.exists()) {

                val EE = it.child("EE").value
                val RE = it.child("RE").value
                val BE = it.child("BE").value
                binding?.textViewTotalEficaciaEnergetica?.text = EE.toString()
                binding?.textViewRazOEnergTica?.text = RE.toString()
                binding?.textViewBalanOEnergTico?.text = BE.toString()

            }
        }
    }

***/




/*
    private fun EE() {

        db = FirebaseDatabase.getInstance().getReference("Produção do Milho")
        db.get().addOnSuccessListener {

            if (it.exists()) {

                val prod = it.child("produçaodomilho").value.toString()
                val PD = prod.toDouble()

                binding?.textViewTotalEnergeticoProduzido?.text = prod


                db = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
                db.get().addOnSuccessListener {

                    if (it.exists()) {

                        val soma = it.child("soma").value.toString()
                        val Doublesoma = soma.toDouble()
                        val rounded1 = df.format(Doublesoma).toDouble()


                        val SM:Double = Doublesoma

                        binding?.textViewTotalEnergetico?.text = ("$rounded1").toString()

                        val EE = (PD / SM)
                        val rounded2 = df.format(EE).toDouble()

                        val RE = (SM / PD)
                        val rounded3 = df.format(RE).toDouble()

                        val BE = PD - SM
                        val rounded4 = df.format(BE).toDouble()

                        binding?.textViewTotalEficaciaEnergetica?.text = ("$rounded2").toString()
                        binding?.textViewRazOEnergTica?.text = ("$rounded3").toString()
                        binding?.textViewBalanOEnergTico?.text = ("$rounded4").toString()


                    }
                }


            }
        }
    }
*/
}