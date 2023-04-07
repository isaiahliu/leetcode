package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun asteroidCollision(asteroids: IntArray): IntArray {
            val stack = LinkedList<Int>()

            fun Int.push() {
                when {
                    stack.isEmpty() || this > 0 || stack.peekLast() < 0 -> {
                        stack.add(this)
                    }

                    stack.peekLast() + this < 0 -> {
                        stack.pollLast()
                        push()
                    }

                    stack.peekLast() + this == 0 -> {
                        stack.pollLast()
                    }
                }
            }

            asteroids.forEach {
                it.push()
            }

            return stack.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().asteroidCollision(intArrayOf()).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}