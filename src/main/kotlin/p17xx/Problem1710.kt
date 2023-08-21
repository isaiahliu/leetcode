package p17xx

import util.expect

fun main() {
    class Solution {
        fun maximumUnits(boxTypes: Array<IntArray>, truckSize: Int): Int {
            boxTypes.sortByDescending { it[1] }

            var result = 0

            var remain = truckSize
            var boxIndex = 0

            while (remain > 0 && boxIndex < boxTypes.size) {
                val (count, unit) = boxTypes[boxIndex++]

                val loadCount = count.coerceAtMost(remain)

                result += loadCount * unit
                remain -= loadCount
            }

            return result
        }
    }

    expect {
        Solution().maximumUnits(
            arrayOf(), 1
        )
    }
}
