package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numOfWays(n: Int): Int {
            val bit3 = intArrayOf(1, 1 * 3, 1 * 9)

            fun Int.getPos(pos: Int): Int {
                return if (this >= 27) {
                    3
                } else {
                    (this / bit3[pos]) % 3
                }
            }

            fun Int.setPos(pos: Int, num: Int): Int {
                return this + (num - getPos(pos)) * bit3[pos]
            }

            val m = 1000000007

            var result = hashMapOf(27 to 1L)
            repeat(n) {
                val newResult = hashMapOf<Int, Long>()
                result.forEach { (status, count) ->
                    val status0 = status.getPos(0)
                    val status1 = status.getPos(1)
                    val status2 = status.getPos(2)

                    repeat(27) { newStatus ->
                        val pos0 = newStatus.getPos(0)
                        val pos1 = newStatus.getPos(1)
                        val pos2 = newStatus.getPos(2)
                        if (pos0 != pos1 && pos1 != pos2 && pos0 != status0 && pos1 != status1 && pos2 != status2) {
                            newResult[newStatus] = ((newResult[newStatus] ?: 0L) + count) % m
                        }
                    }
                }
                result = newResult
            }

            return (result.values.sum() % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().numOfWays(
            3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

