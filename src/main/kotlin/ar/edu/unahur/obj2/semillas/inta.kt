package ar.edu.unahur.obj2.semillas

import java.nio.DoubleBuffer

object inta {

  val parcelas = mutableListOf<Parcela>()

  fun agregarParcela(parcela: Parcela) = parcelas.add(parcela)

  fun totalDePlantas() = parcelas.sumBy{ p -> p.cantidadDePlantas() }

  fun cantidadDeParcelas() = parcelas.size

  fun promedioPlantasPorParcela(): Double{
    var promedio = 0.00
    if (cantidadDeParcelas() != 0) {
      promedio = totalDePlantas().toDouble()/cantidadDeParcelas().toDouble()
    }
    return promedio
  }

  //fun parcelaMasAutosustentable() = parcelas.filter{ p -> p.cantidadDePlantas() > 4 }.max{ p -> p.cantidadDePlantasBienAsociadas()/ p.cantidadDePlantas() }

}
