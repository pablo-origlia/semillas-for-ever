package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class IntaTest: DescribeSpec({

    describe("Test Estadísticas del Inta") {
        val parcela1 = ParcelaEcologica(20.0, 1.0, 7)
        val parcela2 = ParcelaIndustrial(1.0, 3.0, 6)
        val parcela3 = ParcelaEcologica(80.00,8.00,6)
        val parcela4 = ParcelaEcologica(80.00,8.00,6)
        val plantaSoja1 = Soja(anioObtencionSemilla = 2009, altura = 1.5)
        val plantaSoja2 = Soja(anioObtencionSemilla = 2009, altura = 1.1)
        val plantaSoja3 = Soja(anioObtencionSemilla = 2021, altura = 1.2)
        val plantaSoja4 = Soja(anioObtencionSemilla = 2010, altura = 1.8)
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3, 2021)
        val soja = Soja(0.6, 2009)
        val sojaVieja = Soja(0.89, 2006)
        val sojaEnana = Soja(0.3, 2009)
        val sojaAlta = Soja(1.8, 2009)


        it("Promedio de Plantas, cuando Inta no tiene parcelas asociadas") {
            shouldThrowAny { inta.promedioPlantasPorParcela() }
        }

        it("Promedio de Plantas, cuando Inta tiene parcelas asociadas, pero sin plantas") {
            inta.agregarParcela(parcela1)
            inta.agregarParcela(parcela2)
            inta.promedioPlantasPorParcela().shouldBe(0.00)
        }

        it("Promedio de Plantas, cuando Inta tiene parcelas asociadas con plantas") {
            parcela1.plantar(plantaSoja1)
            parcela1.plantar(plantaSoja2)
            parcela1.plantar(plantaSoja3)
            parcela1.plantar(plantaSoja4)
            val plantaPeperina = Peperina(anioObtencionSemilla = 2010, altura = 1.3)
            parcela2.plantar(plantaPeperina)
            inta.totalDePlantas().shouldBe(5)
            inta.cantidadDeParcelas().shouldBe(2)
            inta.promedioPlantasPorParcela().shouldBe(2.50)
        }

        it("Parcela más autosustentable cuando no hay parcelas asociadas a Inta") {
            inta.parcelas.clear()
            //"No hay parcelas asociadas al INTA"
            shouldThrowAny { inta.parcelaMasAutosustentable() }
        }

        it("Parcela más autosustentable cuando hay parcelas asociadas, pero con hasta 4 plantas") {
            inta.agregarParcela(parcela1)
            inta.parcelas.sumBy { p -> p.cantidadDePlantas() }.shouldBe(4)
            //"No hay parcelas asociadas al INTA con más de 4 plantas"
            shouldThrowAny { inta.parcelaMasAutosustentable() }
        }

        it("Parcela más autosustentable cuando hay al menos una parcela asociadas a Inta, con más de 4 plantas") {
            parcela3.plantar(menta)
            parcela3.plantar(mentita)
            parcela3.plantar(soja)
            parcela3.plantar(sojaVieja)
            parcela3.plantar(sojaEnana)
            parcela3.plantar(sojaAlta)
            parcela3.cantidadDePlantas().shouldBe(6)
            inta.agregarParcela(parcela3)
            inta.cantidadDeParcelas().shouldBe(2)
            inta.parcelas.shouldBe(listOf(parcela1,parcela3))
            inta.parcelas.map{p->p.cantidadDePlantas()}.shouldBe(listOf(4,6))
            inta.parcelas.map{p->p.cantidadDePlantasBienAsociadas()}.shouldBe(listOf(0,3))
            inta.parcelas.maxOf{ p -> p.porcentajeDePlantasBienAsociadas()}.shouldBe(0.50)
            inta.parcelaMasAutosustentable().shouldBe(parcela3)
        }

        it("Parcela más autosustentable cuando hay al menos 2 parcelas asociadas a Inta, con más de 4 plantas," +
                "y con el mismo porcentaje de plantas bien asociadas") {
            parcela4.plantar(menta)
            parcela4.plantar(mentita)
            parcela4.plantar(soja)
            parcela4.plantar(sojaVieja)
            parcela4.plantar(sojaEnana)
            parcela4.plantar(sojaAlta)
            parcela4.cantidadDePlantas().shouldBe(6)
            inta.agregarParcela(parcela4)
            inta.cantidadDeParcelas().shouldBe(3)
            inta.parcelas.shouldBe(listOf(parcela1,parcela3,parcela4))
            inta.parcelas.map{p->p.cantidadDePlantas()}.shouldBe(listOf(4,6,6))
            inta.parcelas.map{p->p.cantidadDePlantasBienAsociadas()}.shouldBe(listOf(0,3,3))
            inta.parcelas.maxOf{ p -> p.porcentajeDePlantasBienAsociadas()}.shouldBe(0.50)
            inta.parcelaMasAutosustentable().shouldBe(parcela3)
        }

    }

})