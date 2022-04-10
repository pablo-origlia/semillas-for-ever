package ar.edu.unahur.obj2.semillas

class Planta(var altura: Double, val anioObtencionSemilla: Int) {
  open fun horasDeSolQueTolera() = 7

  fun esFuerte() = horasDeSolQueTolera() > 9

  fun daNuevaSemillas() = esFuerte() or condicionAlternativa()

  open fun condicionAlternativa()

  open fun espacio()

/*
  fun resultaIdeal(parcela)

  fun seAsociaBien(parcela) = parcela.puedeAsociarse(this)
*/
}

class Menta(var altura: Double, val anioObtencionSemilla: Int): Planta {

  override fun horasDeSolQueTolera() = 6

  override fun condicionAlternativa() = altura() > 0.4

  override fun espacio() = altura() + 1
/* 
  override fun resultaIdeal(parcela) = parcela.superficie() > 6
*/
}

class Soja(var altura: Double, val anioObtencionSemilla: Int): Planta {

  override fun horasDeSolQueTolera() {
    var tolerancia
    if (altura() < 0.5) {
      tolerancia = 6
    } else if (altura() in 0.5..1) {
      tolerancia = 8
    } else {
      tolerancia = 12
    }
    return tolerancia
  }

  override fun condicionAlternativa() = (anioObtencionSemilla() > 2007) and (altura() in 0.75..0.9)

  override fun espacio() = altura() / 2
/*
  override fun resultaIdeal(parcela) = parcela.horasDeSolPorDia() == horasDeSolQueTolera()
 */
}

class Quinoa(var altura: Double, val anioObtencionSemilla: Int): Planta {

  const horasDeToleranciaAlSol = 0

  override fun horasDeSolQueTolera() = horasDeToleranciaAlSol

  override fun condicionAlternativa() = self.anioObtencionSemilla() < 2005

  override fun espacio() = 0.5

  override fun resultaIdeal(parcela) = parcela.plantacion().all({ p => p.altura() <= 1.5 })

}

class SojaTransgenica(var altura: Double, val anioObtencionSemilla: Int): Soja {

  override fun daNuevaSemillas() = false

  override fun resultaIdeal(parcela) = parcela.cantidadMaximaDePlantas() == 1

}

class Peperina(var altura: Double, val anioObtencionSemilla: Int): Menta {

  override fun espacio() = super() * 2

}
