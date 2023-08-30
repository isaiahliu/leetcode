package p21xx

import util.expect

fun main() {
    class Solution {
        fun countEven(num: Int): Int {
            var even = true
            var result = 0
            for (i in 1..num) {
                when {
                    i % 1000 == 0 -> {}
                    i % 100 == 0 -> {
                        even = !even
                    }

                    i % 10 == 0 -> {}
                    else -> {
                        even = !even
                    }
                }

                if (even) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().countEven(
            4
        )
    }
//    val params = input.map {
//        it.split(",").map { it.toInt() }.toIntArray()
//    }
//    expect {
//        Solution().goodTriplets(
//            params[0], params[1]
//        )
//    }
}