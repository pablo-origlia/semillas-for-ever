package ar.edu.unahur.obj2.semillas

import java.nio.DoubleBuffer
import kotlin.math.max

object inta {

  val parcelas = mutableListOf<Parcela>()

  fun agregarParcela(parcela: Parcela) = parcelas.add(parcela)

  fun totalDePlantas() = parcelas.sumBy{ p -> p.cantidadDePlantas() }

  fun cantidadDeParcelas() = parcelas.size

  fun promedioPlantasPorParcela(): Double {
    if (cantidadDeParcelas() > 0) {
      return totalDePlantas().toDouble() / cantidadDeParcelas().toDouble()
    }
    else {
      error("No hay parcelas asociadas al INTA")
    }
  }

  fun parcelaMasAutosustentable(): Parcela {
    if (cantidadDeParcelas() > 0) {
      val parcelasMayorA4Plantas = parcelas.filter{ p -> p.cantidadDePlantas() > 4 }
      if (parcelasMayorA4Plantas.isNotEmpty()) {
        val mayorPorcentajeBA = parcelas.maxOf{ p -> p.porcentajeDePlantasBienAsociadas()}
        return parcelasMayorA4Plantas.filter{ p -> p.porcentajeDePlantasBienAsociadas() == mayorPorcentajeBA }.first()
      }
      else {
        error("No hay parcelas asociadas al INTA con más de 4 plantas")
      }
    }
    else {
      error("No hay parcelas asociadas al INTA")
    }
  }
}
