package p13xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxJumps(arr: IntArray, d: Int): Int {
            val leftLowers = IntArray(arr.size)

            val stack = LinkedList<Pair<Int, Int>>()
            //num - size
            stack.push(Int.MAX_VALUE to 0)

            arr.forEachIndexed { index, num ->
                var size = 0

                while (num > stack.peek().first) {
                    size += stack.poll().second
                }

                leftLowers[index] = size
                stack.push(num to size + 1)
            }

            val rightLowers = IntArray(arr.size)
            stack.clear()

            stack.push(Int.MAX_VALUE to 0)

            for (index in arr.lastIndex downTo 0) {
                val num = arr[index]
                var size = 0

                while (num > stack.peek().first) {
                    size += stack.poll().second
                }

                rightLowers[index] = size
                stack.push(num to size + 1)
            }

            val maxStep = IntArray(arr.size) { -1 }

            fun getStep(index: Int): Int {
                if (maxStep[index] > 0) {
                    return maxStep[index]
                }

                var result = 1

                repeat(leftLowers[index].coerceAtMost(d)) {
                    result = result.coerceAtLeast(getStep(index - it - 1) + 1)
                }

                repeat(rightLowers[index].coerceAtMost(d)) {
                    result = result.coerceAtLeast(getStep(index + it + 1) + 1)
                }

                maxStep[index] = result

                return result
            }

            return arr.indices.map { getStep(it) }.max()
        }
    }

    expect {
        Solution().maxJumps(
            intArrayOf(6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12), 2
        )
    }
}

