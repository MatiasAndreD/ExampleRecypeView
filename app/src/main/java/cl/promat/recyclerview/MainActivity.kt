package cl.promat.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var lista:RecyclerView? = null
    var adaptador:Adaptador? = null
    var layoutManager:RecyclerView.LayoutManager? = null


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

        adaptador = Adaptador(this,comidas)
        lista?.adapter = adaptador

    }
}