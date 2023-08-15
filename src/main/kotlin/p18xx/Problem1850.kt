package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMinSwaps(num: String, k: Int): Int {
            val target = num.toCharArray()

            fun reverse(fromIndex: Int, toIndex: Int) {
                var l = fromIndex
                var r = toIndex
                while (l < r) {
                    val t = target[l]
                    target[l] = target[r]
                    target[r] = t
                    l++
                    r--
                }
            }
            repeat(k) {
                var i = target.lastIndex
                while (target[i - 1] >= target[i]) {
                    i--
                }

                val char1 = target[i - 1]
                var j = target.lastIndex
                while (target[j] <= char1) {
                    j--
                }

                target[i - 1] = target[j]
                target[j] = char1
                reverse(i, target.lastIndex)
//                println(String(target))
            }

            var result = 0

            num.forEachIndexed { index, ch ->
                var moveIndex = index
                var targetChar = target[moveIndex]
                while (targetChar != ch) {
                    result++
                    val t = targetChar
                    targetChar = target[++moveIndex]
                    target[moveIndex] = t
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().getMinSwaps(
            "5489355142", 4
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
