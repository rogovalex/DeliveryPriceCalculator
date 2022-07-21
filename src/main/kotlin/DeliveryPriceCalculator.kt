
object DeliveryPriceCalculator {

    fun calculate(distance: Int, size: Size, fragility: Fragility, serviceLoad: ServiceLoad): Int {
        val pricePerDistance = getPricePerDistance(distance)
        val pricePerFragility = fragility.takeIf { distance <= it.distanceRestriction }?.price
            ?: throw IllegalArgumentException("Distance '$distance' too large for specified fragility '$fragility'. Limit ${fragility.distanceRestriction}.")
        val calculatedPrice = (pricePerDistance + pricePerFragility + size.price) * serviceLoad.multiplier
        val roundUpPrice = (calculatedPrice + 0.5f).toInt()
        return maxOf(roundUpPrice, MIN_PRICE)
    }

    private fun getPricePerDistance(distance: Int): Int {
        return when {
            distance > 30_000 -> 300
            distance > 10_000 -> 200
            distance > 2_000 -> 100
            distance > 0 -> 50
            else -> throw IllegalArgumentException("Invalid distance value '$distance'. Should be positive integer.")
        }
    }

    const val MIN_PRICE = 400
}

enum class Size(val price: Int) {
    SMALL(100),
    LARGE(200),
}

enum class Fragility(val price: Int, val distanceRestriction: Int) {
    REGULAR(0, Int.MAX_VALUE),
    FRAGILE(300, 30_000),
}

enum class ServiceLoad(val multiplier: Float) {
    REGULAR(1.0f),
    INCREASED(1.2f),
    HIGH(1.4f),
    EXTREME(1.6f),
}
