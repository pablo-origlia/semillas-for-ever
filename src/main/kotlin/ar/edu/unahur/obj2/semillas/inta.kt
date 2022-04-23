package ar.edu.unahur.obj2.semillas

object inta {

  val parcelas = mutableListOf<Parcela>()

  fun agregarParcela(parcela: Parcela) = parcelas.add(parcela)

  fun totalDePlantas() = parcelas.sum{ p => p.cantidadDePlantas() }

  fun cantidadDeParcelas() = parcelas.size

  fun promedioPlantasPorParcela() = self.totalDePlantas() / self.cantidadDeParcelas()

  fun parcelaMasAutosustentable() = parcelas.filter{ p => p.cantidadDePlantas() > 4 }.max{ p => p.cantidadDePlantasBienAsociadas() / p.cantidadDePlantas() }

}
