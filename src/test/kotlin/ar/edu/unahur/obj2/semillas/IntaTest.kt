package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class IntaTest: DescribeSpec({

        describe("Test Estadísticas del Inta") {
            val parcela1 = ParcelaEcologica (20.0,1.0, 7)
            val parcela2 = ParcelaIndustrial(1.0, 3.0, 6)

            it("Promedio de Plantas, cuando Inta no tiene parcelas asociadas") {
                inta.promedioPlantasPorParcela().shouldBe(0.00)
            }

            it("Promedio de Plantas, cuando Inta tiene parcelas asociadas, pero sin plantas") {
                inta.agregarParcela(parcela1)
                inta.agregarParcela(parcela2)
                inta.promedioPlantasPorParcela().shouldBe(0.00)
            }

            it ("Promedio de Plantas, cuando Inta tiene parcelas asociadas con plantas"){
                val plantaSoja1 = Soja(anioObtencionSemilla = 2009, altura = 1.5)
                val plantaSoja2 = Soja(anioObtencionSemilla = 2009, altura = 1.1)
                val plantaSoja3 = Soja(anioObtencionSemilla = 2021, altura = 1.2)
                val plantaSoja4 = Soja(anioObtencionSemilla = 2010, altura = 1.8)
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

            /*it("Parcela más autosustentable cuando no hay parcelas asociadas a Inta"){

            }

            it("Parcela más autosustentable cuando hay parcelas asociadas, pero con hasta 4 plantas"){

            }

            it("Parcela más autosustentable cuando hay parcelas asociadas a Inta, pero con el mismo porcentaje de plantas bien asociadas"){

            }
            */

        }
})