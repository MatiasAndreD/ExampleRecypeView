package cl.promat.recyclerview

class Comida(nombre:String, precio:Int, imagen:Int, rating:Float) {

    var nombre:String = ""
    var precio:Int = 0
    var imagen:Int = 0
    var rating:Float = 0.0F

    init {
        this.nombre = nombre
        this.precio = precio
        this.imagen = imagen
        this.rating = rating
    }

}