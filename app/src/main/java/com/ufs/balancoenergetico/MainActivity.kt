package com.ufs.balancoenergetico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.common.base.MoreObjects
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import com.ufs.balancoenergetico.databinding.ActivityMainBinding
import com.ufs.balancoenergetico.db.datacolheita

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var auth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<datacolheita>
    private lateinit var db: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        auth = Firebase.auth


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<datacolheita>()

        //{ User -> Toast.makeText(this,User,Toast.LENGTH_SHORT).show()}


        getUserData()

        updateUI()



        binding!!.logoutbtn.setOnClickListener {
            auth.signOut()

            startActivity(Intent(this, InitialMainActivity::class.java))

        }

        binding!!.button5.setOnClickListener {
            startActivity(Intent(this, BalancoEnergeticoActivity::class.java))
        }
    }


    private fun getUserData() {
        db = FirebaseDatabase.getInstance().getReference("Balanço Energetico")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshort in snapshot.children) {
                        val user = userSnapshort.getValue(datacolheita::class.java)
                        userArrayList.add(user!!)
                    }

                    recyclerView.adapter = MyAdpter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }

    private fun updateUI() {
        val usuario: FirebaseUser? = auth.currentUser
        try {
            binding?.idemail?.text = usuario?.email //ou displayName
        } catch (e: Exception) {
            binding?.idemail?.text = ""
        }
    }

}