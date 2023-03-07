package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun validUtf8(data: IntArray): Boolean {
            data.forEach {
                println(it.toString(2).padStart(8, '0'))
            }

            val b1 = 0b0
            val b2 = 0b110
            val b3 = 0b1110
            val b4 = 0b11110
            val bn = 0b10

            var remainingBytes = 0
            data.forEach {
                when {
                    remainingBytes > 0 -> {
                        if ((it shr 6) == bn) {
                            remainingBytes--
                        } else {
                            return false
                        }
                    }

                    (it shr 7) == b1 -> {
                    }

                    (it shr 3) == b4 -> {
                        remainingBytes = 3
                    }

                    (it shr 4) == b3 -> {
                        remainingBytes = 2
                    }

                    (it shr 5) == b2 -> {
                        remainingBytes = 1
                    }

                    else -> {
                        return false
                    }
                }
            }

            return remainingBytes == 0
        }
    }

    measureTimeMillis {
        Solution().validUtf8(
            intArrayOf(235, 140, 4)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

