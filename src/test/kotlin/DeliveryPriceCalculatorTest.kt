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
                // pairwise and limit value
                arrayOf(Int.MAX_VALUE, Size.LARGE, Fragility.REGULAR, ServiceLoad.REGULAR, 500),
                arrayOf(1_000_000, Size.SMALL, Fragility.REGULAR, ServiceLoad.EXTREME, 640),
                arrayOf(100_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.INCREASED, 600),
                arrayOf(30_001, Size.SMALL, Fragility.REGULAR, ServiceLoad.HIGH, 560),
                arrayOf(30_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 640),
                arrayOf(25_000, Size.SMALL, Fragility.FRAGILE, ServiceLoad.REGULAR, 600),
                arrayOf(15_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.INCREASED, 480),
                arrayOf(10_001, Size.SMALL, Fragility.FRAGILE, ServiceLoad.HIGH, 840),
                arrayOf(10_000, Size.SMALL, Fragility.REGULAR, ServiceLoad.HIGH, 400),
                arrayOf(7_000, Size.LARGE, Fragility.FRAGILE, ServiceLoad.EXTREME, 960),
                arrayOf(4_000, Size.SMALL, Fragility.REGULAR, ServiceLoad.REGULAR, 400),
                arrayOf(2_001, Size.LARGE, Fragility.FRAGILE, ServiceLoad.INCREASED, 720),
                arrayOf(2_000, Size.LARGE, Fragility.REGULAR, ServiceLoad.INCREASED, 400),
                arrayOf(1_500, Size.SMALL, Fragility.FRAGILE, ServiceLoad.HIGH, 630),
                arrayOf(500, Size.LARGE, Fragility.REGULAR, ServiceLoad.EXTREME, 400),
                arrayOf(1, Size.SMALL, Fragility.FRAGILE, ServiceLoad.REGULAR, 450),
            )
        }
    }
}
