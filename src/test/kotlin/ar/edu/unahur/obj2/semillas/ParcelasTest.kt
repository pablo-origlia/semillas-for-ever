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
    describe("Parcela") {
        val plantaSoja1 = Soja(anioObtencionSemilla = 2009, altura = 1.5)
        val plantaSoja2 = Soja(anioObtencionSemilla = 2009, altura = 1.1)
        val plantaSoja3 = Soja(anioObtencionSemilla = 2009, altura = 1.2)
        val parcela = ParcelaEcologica(ancho = 20.0, largo = 1.0, horasDeSolPorDia = 8)
        parcela.plantar(plantaSoja1)
        parcela.plantar(plantaSoja2)
        parcela.plantar(plantaSoja3)

        it("Creación de la parcela") {
            parcela.ancho.shouldBe(20.0)
            parcela.largo.shouldBe(1.0)
        }

        it("Superficie") {
            parcela.superficie().shouldBe(20.0)
        }

        it("Cantidad Maxima Plantas") {
            parcela.cantidadMaximaDePlantas().shouldBe(4)
        }

        it("Cantidad de Plantas") {
            parcela.cantidadDePlantas().shouldBe(3)
        }

        it("Tiene complicaciones") {
            parcela.tieneComplicaciones().shouldBeFalse()
        }
    }

})