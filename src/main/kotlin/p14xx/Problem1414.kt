package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMinFibonacciNumbers(k: Int): Int {
            val nums = arrayListOf(1, 1)

            var num1 = 1
            var num2 = 1

            while (num2 < k) {
                (num1 + num2).also {
                    num1 = num2
                    num2 = it
                    nums.add(0, it)
                }
            }

            var result = Int.MAX_VALUE

            fun dfs(count: Int, remain: Int, index: Int) {
                if (index == nums.size) {
                    return
                }
                val num = nums[index]

                if (remain / num + count >= result) {
                    return
                }

                if (remain % num == 0) {
                    result = result.coerceAtMost(count + remain / num)
                } else if (remain > num) {
                    dfs(count + 1, remain - num, index + 1)
                    dfs(count, remain, index + 1)
                } else {
                    dfs(count, remain, index + 1)
                }
            }

            dfs(0, k, 0)
            return result
        }
    }

    measureTimeMillis {
        Solution().findMinFibonacciNumbers(
            10
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

