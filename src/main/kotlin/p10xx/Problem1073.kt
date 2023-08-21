package p10xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun addNegabinary(arr1: IntArray, arr2: IntArray): IntArray {
            val result = LinkedList<Int>()

            var digitPos = 0
            var num = 0

            while (digitPos < arr1.size || digitPos < arr2.size || num != 0) {
                arr1.getOrNull(arr1.lastIndex - digitPos)?.also { num += it }
                arr2.getOrNull(arr2.lastIndex - digitPos++)?.also { num += it }

                (num and 1).also {
                    result.push(it)
                    num = (num - it) / -2
                }
            }

            while (result.size > 1 && result.peek() == 0) {
                result.pop()
            }

            return result.toIntArray()
        }
    }

    expect {
        Solution().addNegabinary(
            intArrayOf(1), intArrayOf(1)
        ).toList()
    }
}
