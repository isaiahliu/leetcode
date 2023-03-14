package p04xx

import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    open class SolBase {
        fun rand7(): Int {
            return Random.nextInt(7) + 1
        }
    }

    class Solution : SolBase() {
        fun roll(): Int {
            return (rand7() - 1) * 7 + (rand7() - 1)
        }

        fun rand10(): Int {
            var t = roll()

            while (t >= 40) {
                t = roll()
            }
            return t / 4 + 1
        }
    }

    measureTimeMillis {
        val s = Solution()

        val counts = IntArray(10)

        repeat(100000) {
            s.rand10().also {
                counts[it - 1]++
            }
        }
        val a = 1
    }.also { println("Time cost: ${it}ms") }
}