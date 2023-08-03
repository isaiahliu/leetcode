package p15xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isTransformable(s: String, t: String): Boolean {
            val leftIndices = Array(10) {
                LinkedList<Int>()
            }

            s.forEachIndexed { index, c ->
                leftIndices[c - '0'].add(index)
            }

            for (rightChar in t) {
                val rightNum = rightChar - '0'

                val leftIndex = leftIndices[rightNum].poll() ?: return false

                for (checkIndex in 0 until rightNum) {
                    if (leftIndices[checkIndex].peek()?.takeIf { it < leftIndex } != null) {
                        return false
                    }
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().isTransformable(
            "4941",
            "1494"
        ).also { println("${it} should be true") }

        Solution().isTransformable(
            "34521",
            "23415"
        ).also { println("${it} should be true") }

        Solution().isTransformable(
            "891",
            "198"
        ).also { println("${it} should be false") }

        Solution().isTransformable(
            "84532",
            "34852"
        ).also { println("${it} should be true") }
    }
}

