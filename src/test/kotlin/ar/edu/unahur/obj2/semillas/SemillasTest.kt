package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({
    // hay una clase Planta que tiene por atributos
    // anioSemilla, altura
    describe("Creación de las plantas") {
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3,2021)
        val soja = Soja(0.6, 2009)
        val sojaVieja = Soja(0.89, 2006)
        val sojaEnana = Soja(0.3, 2009)
        val sojaAlta = Soja(1.8, 2009)
        val quinoaChica = Quinoa(0.2,2010)
        val quinoaGrande = Quinoa(0.9,2006)
        val parcela = ParcelaIndustrial(1.0,3.0,6)

        it("Comprobación de atributos altura y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioObtencionSemilla.shouldBe(2021)
        }

        it("Da nuevas semillas") {
            menta.daNuevasSemillas().shouldBeTrue()
            mentita.daNuevasSemillas().shouldBeFalse()
            soja.daNuevasSemillas().shouldBeFalse()
            sojaVieja.daNuevasSemillas().shouldBeFalse()
            quinoaChica.daNuevasSemillas().shouldBeTrue()
            quinoaGrande.daNuevasSemillas().shouldBeTrue()
        }

        it("Es fuerte") {
            menta.esFuerte().shouldBeFalse()
            soja.esFuerte().shouldBeFalse()
        }

        it("Espacio que ocupa") {
            menta.espacio().shouldBe(2.0)
            mentita.espacio().shouldBe(1.3)
            soja.espacio().shouldBe(0.3)
        }

        it("Tolerancia al sol") {
            menta.horasDeSolQueTolera().shouldBe(7)
            sojaEnana.horasDeSolQueTolera().shouldBe(6)
            soja.horasDeSolQueTolera().shouldBe(8)
            sojaAlta.horasDeSolQueTolera().shouldBe(12)
        }

        it("Superficie que ocupan varias plantas") {
            val superficie = mutableListOf(
                soja.espacio(),
                menta.espacio()
            ).sum()
            superficie.shouldBe(2.3)
        }

        it("Parcela resulta ideal para planta ") {
            menta.resultaIdeal(parcela).shouldBeFalse()
            sojaEnana.resultaIdeal(parcela).shouldBeTrue()
        }

    }
})