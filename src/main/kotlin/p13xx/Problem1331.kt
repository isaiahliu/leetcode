package p13xx

import util.expect

fun main() {
    class Solution {
        fun arrayRankTransform(arr: IntArray): IntArray {
            val sorted = arr.mapIndexed { index, num -> index to num }.sortedBy { it.second }

            var index = 1
            var pre = sorted.firstOrNull()?.second ?: 0
            sorted.forEach { (arrIndex, num) ->
                if (num != pre) {
                    index++
                    pre = num
                }
                arr[arrIndex] = index
            }

            return arr
        }
    }

    expect {
        Solution().arrayRankTransform(
            intArrayOf()
        )
    }
}

