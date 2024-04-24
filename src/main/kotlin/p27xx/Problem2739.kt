package p27xx

import util.expect

fun main() {
    class Solution {
        fun distanceTraveled(mainTank: Int, additionalTank: Int): Int {
            var result = 0

            var main = mainTank
            var additional = additionalTank

            while (main >= 5 && additional > 0) {
                result += 50
                main -= 4
                additional--
            }

            result += main * 10

            return result
        }
    }

    expect {
        Solution().distanceTraveled(
            5, 3
        )
    }
}
