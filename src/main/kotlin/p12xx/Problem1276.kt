package p12xx

import util.expect

fun main() {
    class Solution {
        fun numOfBurgers(tomatoSlices: Int, cheeseSlices: Int): List<Int> {
            return if (tomatoSlices % 2 == 1 || tomatoSlices !in cheeseSlices * 2..cheeseSlices * 4) {
                emptyList()
            } else {
                (tomatoSlices / 2 - cheeseSlices).let { listOf(it, cheeseSlices - it) }
            }
        }
    }

    expect {
        Solution().numOfBurgers(
            16, 7
        )
    }
}
