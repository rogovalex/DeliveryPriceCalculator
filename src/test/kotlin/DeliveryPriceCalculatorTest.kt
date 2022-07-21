import DeliveryPriceCalculator.MIN_PRICE
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DeliveryPriceCalculatorTest(
    private val distance: Int,
    private val size: Size,
    private val fragility: Fragility,
    private val serviceLoad: ServiceLoad,
    private val expectedPrice: Int
) {

    @Test
    fun calculatePrice() {
        assertEquals(expectedPrice, DeliveryPriceCalculator.calculate(distance, size, fragility, serviceLoad))
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters(name = "{0} {1} {2} {3} = {4}")
        fun params(): Iterable<Array<*>> {
            return listOf(
                // large fragile extreme
                arrayOf(30_000, Size.LARGE, Fragility.FRAGILE, ServiceLoad.EXTREME, 1120),
                arrayOf(10_000, Size.LARGE, Fragility.FRAGILE, ServiceLoad.EXTREME, 960),
                arrayOf(2_000, Size.LARGE, Fragility.FRAGILE, ServiceLoad.EXTREME, 880),

                // small fragile extreme
                arrayOf(30_000, Size.SMALL, Fragility.FRAGILE, ServiceLoad.EXTREME, 1120),
                arrayOf(10_000, Size.SMALL, Fragility.FRAGILE, ServiceLoad.EXTREME, 960),
                arrayOf(2_000, Size.SMALL, Fragility.FRAGILE, ServiceLoad.EXTREME, 880),

                // large regular extreme
                arrayOf(Int.MAX_VALUE, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 800),
                arrayOf(30_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 1120),
                arrayOf(10_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 960),
                arrayOf(2_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 880),

                // small regular extreme
                arrayOf(Int.MAX_VALUE, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 800),
                arrayOf(30_000, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 1120),
                arrayOf(10_000, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 960),
                arrayOf(2_000, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 880),



                arrayOf(1, Size.LARGE, Fragility.FRAGILE, ServiceLoad.EXTREME, 880),



                arrayOf(31_002, Size.SMALL, Fragility.REGULAR, ServiceLoad.REGULAR, MIN_PRICE),
                arrayOf(16_500, Size.SMALL, Fragility.REGULAR, ServiceLoad.REGULAR, MIN_PRICE),
                arrayOf(9_040, Size.SMALL, Fragility.REGULAR, ServiceLoad.REGULAR, MIN_PRICE),
                arrayOf(1_234, Size.SMALL, Fragility.REGULAR, ServiceLoad.REGULAR, MIN_PRICE),
                arrayOf(31_002, Size.LARGE, Fragility.REGULAR, ServiceLoad.REGULAR, 500),
                arrayOf(16_500, Size.LARGE, Fragility.REGULAR, ServiceLoad.REGULAR, MIN_PRICE),
                arrayOf(9_040, Size.LARGE, Fragility.REGULAR, ServiceLoad.REGULAR, MIN_PRICE),
                arrayOf(1_234, Size.LARGE, Fragility.REGULAR, ServiceLoad.REGULAR, MIN_PRICE),
            )
        }
//        - более 30 км: +300 рублей к доставке;
//        - до 30 км: +200 рублей к доставке;
//        - до 10 км: +100 рублей к доставке;
//        - до 2 км: +50 рублей к доставке;

//        - большие габариты: +200 рублей к доставке;
//        - маленькие габариты: +100 рублей к доставке;

//        Если груз хрупкий — +300 рублей к доставке. Хрупкие грузы нельзя возить на расстояние более 30 км;

//        - очень высокая загруженность — 1.6;
//        - высокая загруженность — 1.4;
//        - повышенная загруженность — 1.2;
//        - во всех остальных случаях коэффициент равен 1.
    }
}
