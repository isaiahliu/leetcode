package p08xx

import util.expect

fun main() {
    class Solution {
        fun lenLongestFibSubseq(arr: IntArray): Int {
            val nums = arr.toSet()

            val visited = hashSetOf<Pair<Int, Int>>()

            var result = 0

            for (i in arr.indices) {
                for (j in i + 1 until arr.size) {
                    var num1 = arr[i]
                    var num2 = arr[j]

                    var next = num1 + num2

                    if (next !in nums || num2 to next in visited) {
                        continue
                    }

                    var size = 2

                    while (next in nums) {
                        size++

                        visited.add(num2 to next)
                        num1 = num2
                        num2 = next
                        next += num1
                    }

                    result = result.coerceAtLeast(size)
                }
            }

            return result
        }
    }

    expect {
        Solution().lenLongestFibSubseq(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        )

    }
}