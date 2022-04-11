package ar.edu.unahur.obj2.semillas

abstract class Planta(var altura: Double, val anioObtencionSemilla: Int) {

  open fun horasDeSolQueTolera() = 7

  fun esFuerte() = horasDeSolQueTolera() > 9

  open fun daNuevasSemillas(): Boolean = esFuerte() or condicionAlternativa()

  abstract fun condicionAlternativa(): Boolean

  abstract fun espacio(): Double

  abstract fun resultaIdeal(parcela: Parcela): Boolean

  fun seAsociaBien(parcela: Parcela): Boolean = parcela.puedeAsociarse(this)

}

open class Menta(altura: Double, anioObtencionSemilla: Int): Planta(altura,anioObtencionSemilla) {

  override fun condicionAlternativa() =  altura > 0.4

  override fun espacio() = altura + 1

  override fun resultaIdeal(parcela: Parcela) = parcela.superficie() > 6

}

open class Soja(altura: Double, anioObtencionSemilla: Int): Planta(altura,anioObtencionSemilla) {

  override fun horasDeSolQueTolera(): Int {
    var tolerancia: Int
    if (altura < 0.5) {
      tolerancia = 6
    } else if (altura in 0.5..1.0) {
      tolerancia = 8
    } else {
      tolerancia = 12
    }
    return tolerancia
  }

  override fun condicionAlternativa() = (anioObtencionSemilla > 2007) and (altura in 0.75..0.9)

  override fun espacio() = altura / 2

  override fun resultaIdeal(parcela: Parcela) = parcela.horasDeSolPorDia == horasDeSolQueTolera()

}

class Quinoa(altura: Double, anioObtencionSemilla: Int): Planta(altura,anioObtencionSemilla) {

  override fun horasDeSolQueTolera() = if (espacio() < 0.3) 10 else super.horasDeSolQueTolera()

  override fun condicionAlternativa() = (anioObtencionSemilla>=2001) and (anioObtencionSemilla<=2008)

  override fun espacio() = 0.5

  override fun resultaIdeal(parcela: Parcela) = parcela.plantacion.all{ p -> p.altura <= 1.5 }

}

class SojaTransgenica(altura: Double, anioObtencionSemilla: Int): Soja(altura,anioObtencionSemilla) {

  override fun daNuevasSemillas() = false

  override fun resultaIdeal(parcela: Parcela) = parcela.cantidadMaximaDePlantas() == 1
}

class Peperina(altura: Double, anioObtencionSemilla: Int): Menta(altura,anioObtencionSemilla) {

  override fun espacio() = super.espacio() * 2

}
