package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class ParcelasTest : DescribeSpec ({
    // hay una clase Parcela que tiene por atributos
    // ancho, largo,horasDeSolPorDia
    describe("Creación de Parcelas") {
        val plantaSoja1 = Soja(anioObtencionSemilla = 2009, altura = 1.5)
        val plantaSoja2 = Soja(anioObtencionSemilla = 2009, altura = 1.1)
        val plantaSoja3 = Soja(anioObtencionSemilla = 2021, altura = 1.2)
        val plantaSoja4 = Soja(anioObtencionSemilla = 2010, altura = 1.8)
        val plantaPeperina = Peperina(anioObtencionSemilla = 2010, altura = 1.3)
        val parcela = ParcelaEcologica(ancho = 20.0, largo = 1.0, horasDeSolPorDia = 7)
        parcela.plantar(plantaSoja1)
        parcela.plantar(plantaSoja2)
        parcela.plantar(plantaSoja3)
        parcela.plantar(plantaSoja4)

        it("Comprobación de atributos largo y ancho") {
            parcela.ancho.shouldBe(20.0)
            parcela.largo.shouldBe(1.0)
        }

        it("Superficie") {
            parcela.superficie().shouldBe(20.0)
        }

        it("Cantidad Máxima de Plantas") {
            parcela.cantidadMaximaDePlantas().shouldBe(4)
        }

        it("Cantidad de Plantas") {
            parcela.cantidadDePlantas().shouldBe(4)
        }

        it("Tiene complicaciones") {
            parcela.tieneComplicaciones().shouldBeFalse()
        }

        it("Intenta plantar una planta, superando la cantidad máxima de plantas permitidas en la parcela"){
            parcela.hayLugar().shouldBeFalse()
            parcela.excesoDeSol(plantaPeperina).shouldBeFalse()
            shouldThrowAny { parcela.plantar(plantaPeperina) }
        }

        it("Puede asociarse con planta") {
            parcela.puedeAsociarse(plantaPeperina).shouldBeTrue()
        }

    }
})