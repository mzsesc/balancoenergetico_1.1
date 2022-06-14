package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.ufs.balancoenergetico.ActivityBalancoFinanceiro.FinaceiroActivity
import com.ufs.balancoenergetico.ActivitysBalancoEnergertico.MainBalancoEnergeticoActivity
import com.ufs.balancoenergetico.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        auth = Firebase.auth

        updateUI()

      //  readDataSoma()
        //readDataProdMilho()

        EE()
        //soma()

        binding!!.logoutbtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, InitialMainActivity::class.java))
        }

        binding!!.button5.setOnClickListener {
            startActivity(Intent(this, MainBalancoEnergeticoActivity::class.java))

        }

        binding!!.Financeiro.setOnClickListener {

            startActivity(Intent(this, FinaceiroActivity::class.java))
        }
    }


/*
    private fun soma() {
        db = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
        db.get().addOnSuccessListener { it ->

            if (it.exists()) {
                db.child("CH").get().addOnSuccessListener { it ->
                    if (it.exists()) {

                        val colheitadera = it.child("colheitadera").value.toString()
                        val ensiladeira = it.child("ensiladeira").value.toString()
                        val maodeobra = it.child("maodeobra").value.toString()
                        val CH = colheitadera.toDouble()
                        val SA = ensiladeira.toDouble()
                        val MO = maodeobra.toDouble()

                        if (it.exists()) {
                            db.child("DM").get().addOnSuccessListener { it ->

                                val fungicida = it.child("fungicida").value.toString()
                                val herbicida = it.child("herbicida").value.toString()
                                val inseticidas = it.child("inseticida").value.toString()

                                val FC = fungicida.toDouble()
                                val HC = herbicida.toDouble()
                                val IT = inseticidas.toDouble()

                                if (it.exists()) {
                                    db.child("SA").get().addOnSuccessListener { it ->
                                        if (it.exists()) {

                                            val tiposdesemente =
                                                it.child("tipodesemente").value.toString()
                                            val fertilizanteazotado =
                                                it.child("fertilizanteazotado").value.toString()
                                            val fertilizantepotassico =
                                                it.child("fertilizantepotassico").value.toString()
                                            val fertilizantefosfatado =
                                                it.child("fertilizantefosfatado").value.toString()

                                            val TS = tiposdesemente.toDouble()
                                            val FTA = fertilizanteazotado.toDouble()
                                            val FTP = fertilizantepotassico.toDouble()
                                            val FTF = fertilizantefosfatado.toDouble()


                                            db.child("PS").get().addOnSuccessListener { it ->

                                                if (it.exists()) {
                                                    val oleodissel =
                                                        it.child("lubrificante").value.toString()
                                                    val lubrificante =
                                                        it.child("oleodissel").value.toString()
                                                    val trator = it.child("trator").value.toString()

                                                    val OD = oleodissel.toDouble()
                                                    val LB = lubrificante.toDouble()
                                                    val TT = trator.toDouble()


                                                    val soma =
                                                        CH + SA + MO + HC + IT  + FC + TS +
                                                                FTA + FTP + FTF + OD + LB + TT
                                                    val sm = ("$soma MJ")
                                                    binding?.textViewTotalEnergetico?.text = sm
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

 */

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

                        val SM = soma.toDouble()

                        binding?.textViewTotalEnergetico?.text = soma

                        val EE = (PD / SM)
                        val RE = (SM / PD)
                        val BE = PD - SM
                        binding?.textViewTotalEficaciaEnergetica?.text = EE.toString()
                        binding?.textViewRazOEnergTica?.text = RE.toString()
                        binding?.textViewBalanOEnergTico?.text = BE.toString()


                    }
                }


            }
        }
    }

}