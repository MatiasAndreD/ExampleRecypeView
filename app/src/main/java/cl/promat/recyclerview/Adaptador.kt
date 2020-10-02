package cl.promat.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(var contexto:Context, items:ArrayList<Comida>): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    var items:ArrayList<Comida>?= null

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador.ViewHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.template_platillo,parent,false)
        val viewHolder = ViewHolder(vista)

        return viewHolder
    }

    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.imagen?.setImageResource(item?.imagen!!)
        holder.nombre?.text = item?.nombre
        holder.precio?.text = "$" + item?.precio.toString()
        holder.rating?.rating = item?.rating!!
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    class ViewHolder(vista: View):RecyclerView.ViewHolder(vista){
        var vista = vista
        var nombre: TextView? = null
        var precio: TextView? = null
        var rating: RatingBar? = null
        var imagen: ImageView? = null

        init {
            nombre = vista.findViewById(R.id.nombre)
            precio = vista.findViewById(R.id.precio)
            rating = vista.findViewById(R.id.rating)
            imagen = vista.findViewById(R.id.imagen)
        }
    }


}