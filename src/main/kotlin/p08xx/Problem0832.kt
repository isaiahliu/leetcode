package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun flipAndInvertImage(image: Array<IntArray>): Array<IntArray> {
            return Array(image.size) { r ->
                IntArray(image[r].size) { c ->
                    1 - image[r][image[r].lastIndex - c]
                }
            }
        }
    }

    measureTimeMillis {
        Solution().flipAndInvertImage(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}