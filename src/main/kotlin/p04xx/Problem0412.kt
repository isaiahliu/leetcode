package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun fizzBuzz(n: Int): List<String> {
            return (1..n).map {
                when {
                    it % 15 == 0 -> "FizzBuzz"
                    it % 3 == 0 -> "Fizz"
                    it % 5 == 0 -> "Buzz"
                    else -> it.toString()
                }
            }
        }
    }

    measureTimeMillis {
        Solution().fizzBuzz(
            3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


