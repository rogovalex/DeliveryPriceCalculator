import org.junit.Test

class DeliveryPriceCalculatorFailuresTest {

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenDistanceIsZero() {
        DeliveryPriceCalculator.calculate(0, Size.SMALL, Fragility.REGULAR, ServiceLoad.REGULAR)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenDistanceIsNegative() {
        DeliveryPriceCalculator.calculate(-1, Size.SMALL, Fragility.REGULAR, ServiceLoad.REGULAR)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenDistanceIsLargerThanFragileDistanceRestriction() {
        DeliveryPriceCalculator.calculate(30_001, Size.SMALL, Fragility.FRAGILE, ServiceLoad.REGULAR)
    }
}
