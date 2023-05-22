package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun powerfulIntegers(x: Int, y: Int, bound: Int): List<Int> {
            fun generateList(num: Int): List<Int> {
                var n = num.toLong()

                val result = arrayListOf(1)

                while (num > 1 && n < bound) {
                    result.add(n.toInt())

                    n *= num
                }

                return result
            }

            val left = generateList(x)
            val right = generateList(y)

            val result = hashSetOf<Int>()

            for (l in left) {
                for (r in right) {
                    if (l + r > bound) {
                        break
                    }

                    result.add(l + r)
                }
            }

            return result.toList()
        }
    }

    measureTimeMillis {
        Solution().powerfulIntegers(
            12, 3, 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
