package ar.edu.unahur.obj2.semillas

//import ar.edu.unahur.obj2.semillas

abstract class Parcela(val ancho: Double, val largo: Double, val horasDeSolPorDia: Int) {
  val plantacion = mutableListOf<Planta>()

  fun superficie() = ancho * largo

  fun cantidadMaximaDePlantas(): Int{
    var cantidad: Int
    if (ancho > largo) {
       cantidad = (superficie() / 5).toInt()
    } else {
       cantidad = ((superficie() / 3) + largo).toInt()
    }
    return cantidad
  }

  fun tieneComplicaciones() = plantacion.any{ p -> p.horasDeSolQueTolera() < horasDeSolPorDia}

  fun cantidadDePlantas() = plantacion.size

  fun hayLugar() = (cantidadDePlantas() + 1) <= cantidadMaximaDePlantas()

  fun excesoDeSol(planta: Planta) = horasDeSolPorDia >= (planta.horasDeSolQueTolera() + 2)

  fun plantar(planta: Planta) {
    if (hayLugar() and !excesoDeSol(planta)) {
      plantacion.add(planta)
    } else {
      error("No se puede plantar, parcela completa o mucho sol.")
    }
  }

  abstract fun puedeAsociarse(planta: Planta): Boolean

  fun cantidadDePlantasBienAsociadas() = plantacion.count{ p -> puedeAsociarse(p)}

}

class ParcelaEcologica(ancho: Double, largo: Double, horasDeSolPorDia: Int): Parcela(ancho, largo, horasDeSolPorDia){

  override fun puedeAsociarse(planta: Planta) = !tieneComplicaciones() and planta.resultaIdeal(this)

}

class ParcelaIndustrial(ancho: Double, largo: Double, horasDeSolPorDia: Int): Parcela(ancho, largo, horasDeSolPorDia) {

  override fun puedeAsociarse(planta: Planta) = (cantidadDePlantas() <= 2) and planta.esFuerte()

}

