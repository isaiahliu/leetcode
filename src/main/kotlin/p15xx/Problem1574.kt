package p15xx

import util.expect

fun main() {
    class Solution {
        fun findLengthOfShortestSubarray(arr: IntArray): Int {
            var rightIndex = arr.lastIndex

            while (rightIndex > 0) {
                if (arr[rightIndex - 1] <= arr[rightIndex]) {
                    rightIndex--
                } else {
                    break
                }
            }

            var result = rightIndex

            if (result == 0) {
                return 0
            }

            var leftIndex = 0
            while (true) {
                while (rightIndex < arr.size && arr[rightIndex] < arr[leftIndex]) {
                    rightIndex++
                }

                result = result.coerceAtMost(rightIndex - leftIndex - 1)

                leftIndex++

                if (arr[leftIndex] < arr[leftIndex - 1]) {
                    break
                }
            }

            return result
        }
    }

    expect {
        Solution().findLengthOfShortestSubarray(
            intArrayOf(10, 13, 17, 21, 15, 15, 9, 17, 22, 22, 13)
        )
    }
}

