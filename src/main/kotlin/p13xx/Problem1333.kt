package p13xx

import util.expect

fun main() {
    class Solution {
        fun filterRestaurants(
            restaurants: Array<IntArray>,
            veganFriendly: Int,
            maxPrice: Int,
            maxDistance: Int
        ): List<Int> {
            return restaurants.filter { (_, _, v, p, d) ->
                (veganFriendly == 0 || v == 1) && p <= maxPrice && d <= maxDistance
            }.sortedWith(compareByDescending<IntArray> { it[1] }.thenByDescending { it[0] }).map { it[0] }
        }
    }

    expect {
        Solution().filterRestaurants(
            arrayOf(), 1, 2, 3
        )
    }
}

