package cl.promat.recyclerview

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(items:ArrayList<Comida>,  var listener: Clicklistener, var longClickListener:LongClickListener): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    var items:ArrayList<Comida>?= null
    var multiseleccion = false
    var itemseleccionados:ArrayList<Int>? = null
    var viewHolder:ViewHolder? = null

    init {
        this.items = items
        itemseleccionados = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_platillo,parent,false)
        viewHolder = ViewHolder(vista,listener,longClickListener )

        return viewHolder!!
    }

    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
        //onBindViewHolder nos permite mapear los elementos graficos con los datos.

        val item = items?.get(position)
        holder.imagen?.setImageResource(item?.imagen!!)
        holder.nombre?.text = item?.nombre
        holder.precio?.text = "$" + item?.precio.toString()
        holder.rating?.rating = item?.rating!!

        if(itemseleccionados?.contains(position)!!){
            holder.vista.setBackgroundColor(Color.LTGRAY)
        }else{
            holder.vista.setBackgroundColor(Color.WHITE)
        }


    }

    fun iniciarActionMode(){
        multiseleccion = true
    }

    fun destruirActionMode(){
        multiseleccion = false
        itemseleccionados?.clear()
        notifyDataSetChanged()

    }

    fun seleccionarItem(index:Int){
        if(multiseleccion){
            if(itemseleccionados?.contains(index)!!){
                itemseleccionados?.remove(index)
            }else{
                itemseleccionados?.add(index)
            }
            notifyDataSetChanged()
        }

    }

    fun terminarActionMode(){
        //eliminar elementos seleccionados
        for(item in itemseleccionados!!){
            itemseleccionados?.remove(item)
        }
        multiseleccion = false
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    class ViewHolder(vista: View, listener:Clicklistener, longClickListener: LongClickListener):RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener{
        var vista = vista
        var nombre: TextView? = null
        var precio: TextView? = null
        var rating: RatingBar? = null
        var imagen: ImageView? = null
        var listener:Clicklistener? = null
        var longListener:LongClickListener? = null

        init {
            nombre = vista.findViewById(R.id.nombre)
            precio = vista.findViewById(R.id.precio)
            rating = vista.findViewById(R.id.rating)
            imagen = vista.findViewById(R.id.imagen)
            this.listener = listener
            this.longListener = longClickListener
            vista.setOnLongClickListener(this)
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!,adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClick(v!!, adapterPosition)
            return true
        }
    }


}