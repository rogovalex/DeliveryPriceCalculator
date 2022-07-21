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
                // ALL DISTANCES / Size.LARGE / Fragility.REGULAR / ServiceLoad.EXTREME
                arrayOf(Int.MAX_VALUE, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 800),
                arrayOf(30_001, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 800),
                arrayOf(30_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 640),
                arrayOf(10_001, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 640),
                arrayOf(10_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 480),
                arrayOf(2_001, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 480),
                arrayOf(2_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 400),
                arrayOf(1, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 400),

                // Int.MAX_VALUE / Size.LARGE / Fragility.REGULAR / REMAINING SERVICE LOAD
                arrayOf(Int.MAX_VALUE, Size.LARGE, Fragility.REGULAR, ServiceLoad.HIGH, 700),
                arrayOf(Int.MAX_VALUE, Size.LARGE, Fragility.REGULAR, ServiceLoad.INCREASED, 600),
                arrayOf(Int.MAX_VALUE, Size.LARGE, Fragility.REGULAR, ServiceLoad.REGULAR, 500),

                // Int.MAX_VALUE / REMAINING SIZE / Fragility.REGULAR / ServiceLoad.EXTREME
                arrayOf(Int.MAX_VALUE, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 640),

                // 30_000 / Size.LARGE / REMAINING FRAGILITY / ServiceLoad.EXTREME
                arrayOf(30_000, Size.LARGE, Fragility.FRAGILE, ServiceLoad.EXTREME, 1120),

                // ALL DISTANCES / Size.SMALL / Fragility.REGULAR / ServiceLoad.EXTREME
                arrayOf(40_001, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 640),
                arrayOf(20_001, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 480),
                arrayOf(5_001, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 400),
                arrayOf(1_001, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 400),

                // 40_001 / Size.SMALL / Fragility.REGULAR / REMAINING SERVICE LOAD
                arrayOf(40_001, Size.SMALL, Fragility.REGULAR, ServiceLoad.HIGH, 560),
                arrayOf(40_001, Size.SMALL, Fragility.REGULAR, ServiceLoad.INCREASED, 480),
                arrayOf(40_001, Size.SMALL, Fragility.REGULAR, ServiceLoad.REGULAR, 400),

                // 1_000 / Size.SMALL / REMAINING FRAGILITY / ServiceLoad.EXTREME
                arrayOf(1_000, Size.SMALL, Fragility.FRAGILE, ServiceLoad.EXTREME, 720),
            )
        }
    }
}
