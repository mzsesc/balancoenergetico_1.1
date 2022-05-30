package com.ufs.balancoenergetico

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ufs.balancoenergetico.db.dataPreparacaosolo
import com.ufs.balancoenergetico.db.datacolheita

class MyAdpter(
    private val userlist: ArrayList<datacolheita>,
) : RecyclerView.Adapter<MyAdpter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user = userlist[position]
        holder.fistName.text = user.colheitader
        holder.lastName.text = user.ensiladeira
        holder.age.text = user.maobra



    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val fistName: TextView = itemView.findViewById(R.id.text_view_fist_name)
        val lastName: TextView = itemView.findViewById(R.id.text_view_last_name)
        val age: TextView = itemView.findViewById(R.id.text_age)


    }

}