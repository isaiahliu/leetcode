package p21xx

import util.expect

fun main() {
    class Solution {
        fun maximumEvenSplit(finalSum: Long): List<Long> {
            val num = finalSum / 2
            if (finalSum > num * 2) {
                return emptyList()
            }

            var curr = 1L
            var sum = 0L

            while (sum < num) {
                sum += curr++
            }

            val result = arrayListOf<Long>()

            for (n in 1L until curr) {
                if (n != sum - num) {
                    result.add(n * 2)
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumEvenSplit(
            16
        )
    }
}
