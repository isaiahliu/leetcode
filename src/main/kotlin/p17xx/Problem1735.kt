package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun waysToFillArray(queries: Array<IntArray>): IntArray {
            val m = 1000000007
            val mi = m.toBigInteger()

            fun Int.arrangement(start: Int = 1): Int {
                var result = 1L

                for (num in start..this) {
                    result *= num
                    result %= m
                }

                return result.toInt()
            }

            fun combine(cm: Int, cn: Int): Int {
                val cmn = cm - cn

                return ((cm.arrangement(cn.coerceAtLeast(cmn) + 1) * cn.coerceAtMost(cmn).arrangement().toBigInteger()
                    .modInverse(mi).toLong()) % m).toInt()
            }

            fun process(size: Int, num: Int, factorStart: Int = 2): Int {
                return when {
                    size < 0 -> {
                        0
                    }

                    num == 1 -> {
                        1
                    }

                    size == 0 -> {
                        0
                    }

                    factorStart > num -> {
                        0
                    }

                    size == 1 -> {
                        1
                    }

                    else -> {
                        var result = size.toLong()

                        for (factor in factorStart until num) {
                            if (factor * factor > num) {
                                break
                            }

                            var t = num
                            var count = 0

                            while (t % factor == 0) {
                                t /= factor
                                count++

                                process(size - count, t, factor + 1).takeIf { it > 0 }?.toLong()?.also {
                                    result += it * combine(size, count)
                                    result %= m
                                }
                            }
                        }

                        result.toInt()
                    }
                }
            }

            return queries.map { (n, k) ->
                process(n, k, 2)
            }.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().waysToFillArray(
            arrayOf(
                intArrayOf(2, 4),
//                intArrayOf(4, 4),
//                intArrayOf(2, 6),
//                intArrayOf(5, 1),
//                intArrayOf(73, 660),
            )
        ).toList().also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
