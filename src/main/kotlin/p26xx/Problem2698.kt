package p26xx

import util.expect

fun main() {
    class Solution {
        fun punishmentNumber(n: Int): Int {
            val cache = hashMapOf<Int, Set<Int>>()

            (0..9).forEach {
                cache[it] = setOf(it)
            }

            fun p(pow: Int): Set<Int> {
                if (pow !in cache) {
                    val powStr = pow.toString()

                    val result = hashSetOf<Int>(pow)
                    for (leftSize in 1 until powStr.length) {
                        val left = p(powStr.take(leftSize).toInt())
                        val right = p(powStr.drop(leftSize).toInt())

                        left.forEach { l ->
                            right.forEach { r ->
                                result.add(l + r)
                            }
                        }
                    }

                    cache[pow] = result
                }

                return cache[pow].orEmpty()
            }

            p(100)
            var result = 0
            (1..n).forEach { num ->
                val pow = num * num

                if (num in p(pow)) {
                    result += pow
                }
            }

            return result
        }
    }

    expect {
        Solution().punishmentNumber(
            10
        )
    }
}
