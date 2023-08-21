package p09xx

import util.expect

fun main() {
    class Solution {
        fun threeSumMulti(arr: IntArray, target: Int): Int {
            val m = 1000000007L

            val map = arr.toList().groupingBy { it }.eachCount()
            val entries = map.entries.sortedBy { it.key }

            var result = 0L
            for (i in 0 until map.size) {
                val (num1, count1) = entries[i]
                for (j in i until map.size) {
                    val (num2, count2) = entries[j]

                    val num3 = target - num1 - num2
                    if (num3 < num2) {
                        break
                    }

                    val count3 = map[num3] ?: continue

                    result += when {
                        num1 == num2 && num2 == num3 -> {
                            1L * count1 * (count1 - 1) * (count1 - 2) / 3 / 2
                        }

                        num1 == num2 -> {
                            1L * count3 * count1 * (count1 - 1) / 2
                        }

                        num2 == num3 -> {
                            1L * count1 * count2 * (count2 - 1) / 2
                        }

                        else -> {
                            1L * count1 * count2 * count3
                        }
                    }

                    result %= m
                }
            }

            return result.toInt()
        }
    }

    expect {
        Solution().threeSumMulti(
            intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5), 8
        )
    }
}