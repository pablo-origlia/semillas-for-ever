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
    describe("Test de Parcelas") {
        val plantaSoja1 = Soja(1.5, 2009)
        val plantaSoja2 = Soja(1.1, 2009)
        val plantaSoja3 = Soja(1.2, 2021)
        val plantaSoja4 = Soja(1.8, 2010)
        val plantaPeperina = Peperina(1.3, 2010)
        val parcela = ParcelaEcologica(20.0, 1.0, 7)
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