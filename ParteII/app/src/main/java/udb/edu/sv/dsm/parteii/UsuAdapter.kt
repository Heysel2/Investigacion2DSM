package udb.edu.sv.dsm.parteii

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsuAdapter : RecyclerView.Adapter<UsuAdapter.UsuViewHolder>() {

    private var userList: MutableList<User> = mutableListOf()

    //agregar usuario
    fun addUserData(newUser: User) {
        userList.add(newUser)
        notifyItemInserted(userList.size - 1)
    }

    //extender la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return UsuViewHolder(view)
    }

    //posicionar los datos
    override fun onBindViewHolder(holder: UsuViewHolder, position: Int) {
        val user = userList[position]
        holder.itemView.findViewById<TextView>(android.R.id.text1).text = user.name
        holder.itemView.findViewById<TextView>(android.R.id.text2).text = user.email
    }

    override fun getItemCount(): Int = userList.size

//actualizar lista
    fun setData(users: List<User>) {
        userList = users.toMutableList()
        notifyDataSetChanged()
    }

    class UsuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
