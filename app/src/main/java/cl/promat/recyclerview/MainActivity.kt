package cl.promat.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    var lista:RecyclerView? = null
    var adaptador:Adaptador? = null
    var layoutManager:RecyclerView.LayoutManager? = null

    var isActionMode = false
    var actionMode:ActionMode? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val comidas = ArrayList<Comida>()

        comidas.add(Comida("Pollo barbicue",30000, R.drawable.platillo01, 3.5F))
        comidas.add(Comida("Ensalada con pollo",10000, R.drawable.platillo02, 3.5F))
        comidas.add(Comida("Emparedados",15000, R.drawable.platillo03, 3.5F))
        comidas.add(Comida("Camote",5000, R.drawable.platillo04, 4.5F))


        lista = findViewById(R.id.lista)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        // -------------------------------------- C O N F I G U R A R   A C T I O N   M O D E --------------------------------------------
        val callback = object: ActionMode.Callback{
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {

                when(item?.itemId){
                    R.id.iEliminar -> {
                        Toast.makeText(applicationContext, "Eliminar objetos", Toast.LENGTH_SHORT).show()
                        adaptador?.eliminarSeleccion()
                    }
                    else->{
                        return true
                    }
                }

                adaptador?.terminarActionMode()
                mode?.finish()
                isActionMode = false


                return true
            }
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                //Iniciar action mode
                adaptador?.iniciarActionMode()
                actionMode = mode

                //inflar menu
                menuInflater.inflate(R.menu.menu_contextual, menu!! )
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode?.title = "0 seleccionados"
                return false
            }


            override fun onDestroyActionMode(mode: ActionMode?) {

                adaptador?.destruirActionMode()
                isActionMode = false
            }

        }
//------------------------------------------------------------------------------------------------
        adaptador = Adaptador(comidas, object : Clicklistener {
            override fun onClick(vista: View, index: Int) {
                Toast.makeText(applicationContext, comidas.get(index).nombre, Toast.LENGTH_SHORT)
                    .show()
            }


        }, object : LongClickListener {
            override fun longClick(vista: View, index: Int) {
              if(!isActionMode){
                  startSupportActionMode(callback)
                  isActionMode = true
                  adaptador?.seleccionarItem(index)

              }else{
                  //Hacer selecciones o deselecciones
                  adaptador?.seleccionarItem(index)
              }
                actionMode?.title= adaptador?.obtenerNumeroElementosSeleccionados().toString() +" seleccionados"
            }

        })
        lista?.adapter = adaptador

// -------------------------------------- C O N F I G U R A R  S W I P E  T O  R E F R E S H -----------------------------------------------------------

        val swipetorefresh = findViewById<SwipeRefreshLayout>(R.id.swipetorefresh)
        swipetorefresh.setOnRefreshListener {
            swipetorefresh.isRefreshing = false
            comidas.add(Comida("Mote",5000, R.drawable.platillo06, 5.0F))
            adaptador?.notifyDataSetChanged()
        }


    }
}