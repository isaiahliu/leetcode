package p17xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(boxes: String): IntArray {
            var sum = 0
            var rightCount = 0
            for (index in boxes.lastIndex downTo 0) {
                sum += rightCount
                rightCount += boxes[index] - '0'
            }

            var leftCount = 0
            return IntArray(boxes.length) { index ->
                sum.also {
                    leftCount += boxes[index] - '0'
                    rightCount -= boxes[index] - '0'

                    sum -= rightCount
                    sum += leftCount
                }
            }
        }
    }

    expect {
        Solution().minOperations(
            "110"
        ).toList()
    }
}
