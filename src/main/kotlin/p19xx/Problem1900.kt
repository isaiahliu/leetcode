package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        val cache = hashMapOf<Pair<Pair<Int, Int>, Int>, IntArray>()

        fun earliestAndLatest(n: Int, firstPlayer: Int, secondPlayer: Int): IntArray {
            var leftSeq = firstPlayer.coerceAtMost(secondPlayer)
            var rightSeq = firstPlayer.coerceAtLeast(secondPlayer)

            when {
                leftSeq == n - rightSeq + 1 -> {
                    return intArrayOf(1, 1)
                }

                leftSeq > n - rightSeq + 1 -> {
                    val t = n - rightSeq + 1
                    rightSeq = n - leftSeq + 1
                    leftSeq = t

                }
            }

            val cacheKey = leftSeq to rightSeq to n
            if (cacheKey in cache) {
                return cache[cacheKey] ?: intArrayOf()
            }

            val result = intArrayOf(Int.MAX_VALUE, 0)

            val team = n / 2
            repeat(1 shl team) { status ->
                var deltaL = 0
                var deltaR = 0

                for (index in 0 until team) {
                    val l = index + 1
                    val r = n - index

                    val remove = when {
                        l == leftSeq -> r
                        r == rightSeq -> l
                        status and (1 shl index) == 0 -> l
                        else -> r
                    }

                    if (remove < leftSeq) {
                        deltaL--
                    }

                    if (remove < rightSeq) {
                        deltaR--
                    }

                }
                earliestAndLatest(n / 2 + n % 2, leftSeq + deltaL, rightSeq + deltaR).also {
                    result[0] = result[0].coerceAtMost(it[0])
                    result[1] = result[1].coerceAtLeast(it[1])
                }
            }

            result[0]++
            result[1]++

            cache[cacheKey] = result

            return result
        }
    }

    measureTimeMillis {
        Solution().earliestAndLatest(
            28, 1, 3
        ).toList().also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
