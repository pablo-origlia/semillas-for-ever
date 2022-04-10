package ar.edu.unahur.obj2.parcelas

import ar.edu.unahur.obj2.semillas

class Parcela(val ancho: Double, val largo: Double, val horasDeSolPorDia: Int) {
  val plantacion = []

  fun superficie() = ancho() * largo()

  fun cantidadMaximaDePlantas() {
    var cantidad
    if (ancho() > largo()) {
      cantidad = superficie() / 5
    } else {
      cantidad = (superficie() / 3) + largo()
    }
    return cantidad
  }

  fun tieneComplicaciones() = plantacion.any({ p => p.horasDeSolQueTolera() < horasDeSolPorDia() })

  fun cantidadDePlantas() = plantacion.size()

  fun hayLugar() = (cantidadDePlantas() + 1) <= cantidadMaximaDePlantas()

  fun excesoDeSol(planta) = horasDeSolPorDia() >= (planta.horasDeSolQueTolera() + 2)

  fun plantar(planta) {
    if (hayLugar() and not excesoDeSol(planta)) {
      plantacion.add(planta)
    } else {
      error("No se puede plantar, parcela completa o mucho sol.")
    }
  }

  fun puedeAsociarse(planta)

  fun cantidadDePlantasBienAsociadas() = plantacion.count({ p => puedeAsociarse(p) })

}

class ParcelaEcologica(val ancho: Double, val largo: Double, val horasDeSolPorDia: Int): Parcela {

  override fun puedeAsociarse(planta) = not tieneComplicaciones() and planta.resultaIdeal(this)

}

class ParcelaIndustrial(val ancho: Double, val largo: Double, val horasDeSolPorDia: Int): Parcela {

  override fun puedeAsociarse(planta) = (cantidadDePlantas() <= 2) and planta.esFuerte()

}

