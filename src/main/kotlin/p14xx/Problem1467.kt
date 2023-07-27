package p14xx

import java.math.BigInteger
import java.math.RoundingMode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getProbability(balls: IntArray): Double {
            fun Int.factorial(): BigInteger {
                return (1..this).fold(BigInteger.ONE) { a, b -> a * b.toBigInteger() }
            }

            val sum = balls.sum()

            val halfTotal = (sum / 2).factorial()
            var total = sum.factorial()
            balls.forEach {
                total /= it.factorial()
            }

            val stack = LinkedList<Int>()
            repeat(balls.size) {
                stack.add(0)
            }

            var result = BigInteger.ZERO
            fun dfs(index: Int, counts: List<Int>, count: Int, left: Int, right: Int) {
                if (count == sum / 2) {
                    if (left == right) {
                        var leftP = halfTotal
                        counts.forEach {
                            leftP /= it.factorial()
                        }

                        var rightP = halfTotal
                        balls.forEachIndexed { i, n ->
                            rightP /= (n - (counts.getOrNull(i) ?: 0)).factorial()
                        }

                        result += leftP * rightP
                    }

                    return
                }

                if (index == balls.size || left > right) {
                    return
                }

                (0..balls[index].coerceAtMost(sum / 2 - count)).forEach {
                    dfs(
                        index + 1,
                        counts + it,
                        count + it,
                        left + if (it > 0) 1 else 0,
                        right - if (it == balls[index]) 1 else 0
                    )
                }
            }

            dfs(0, listOf(), 0, 0, balls.size)

            return result.toBigDecimal().divide(total.toBigDecimal(), 10, RoundingMode.HALF_UP).toDouble()
        }
    }

    measureTimeMillis {
        Solution().getProbability(
            intArrayOf(6, 6, 6, 6, 6, 6, 6, 6)
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

