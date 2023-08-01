package p15xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getWinner(arr: IntArray, k: Int): Int {
            if (k >= arr.lastIndex) {
                return arr.max()
            }

            val nums = LinkedList<Int>()
            arr.forEach { nums.add(it) }

            var win = 0
            while (win < k) {
                val num1 = nums.pollFirst()
                val num2 = nums.pollFirst()

                if (num1 > num2) {
                    nums.push(num1)
                    nums.add(num2)
                    win++
                } else {
                    nums.push(num2)
                    nums.add(num1)
                    win = 1
                }
            }

            return nums.peekFirst()
        }
    }

    measureTimeMillis {
        Solution().getWinner(
            intArrayOf(), 1
        ).also { println(it) }
    }
}

