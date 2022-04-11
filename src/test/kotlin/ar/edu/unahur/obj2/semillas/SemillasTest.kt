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
        val soja = Soja(0.6, 2009)

        it("Probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioObtencionSemilla.shouldBe(2021)
        }

        it("Verificar si da nuevas semillas") {
            menta.daNuevasSemillas().shouldBeTrue()
            soja.daNuevasSemillas().shouldBeFalse()
        }

        it("es fuerte") {
            menta.esFuerte().shouldBeFalse()
            soja.esFuerte().shouldBeFalse()
        }

        it("Espacio que ocupa") {
            menta.espacio().shouldBe(2.0)
            soja.espacio().shouldBe(0.3)
        }

        it("verifico la suma de superficie ocupada por varias plantas") {
            val superficie = mutableListOf(
                soja.espacio(),
                menta.espacio()
            ).sum()
            superficie.shouldBe(2.3)
        }
    }
})