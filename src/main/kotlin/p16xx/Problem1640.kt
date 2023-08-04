package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canFormArray(arr: IntArray, pieces: Array<IntArray>): Boolean {
            val pieceMap = pieces.associateBy { it[0] }

            var index = 0
            while (index < arr.size) {
                val piece = pieceMap[arr[index]] ?: return false

                piece.forEach {
                    if ((arr.getOrNull(index++) ?: return false) != it) {
                        return false
                    }
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().canFormArray(
            intArrayOf(15, 88), arrayOf(
                intArrayOf(88),
                intArrayOf(15),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}