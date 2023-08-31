package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countCollisions(directions: String): Int {
            val stack = LinkedList<Char>()

            val queue = LinkedList<Char>()
            queue.addAll(directions.toList())

            var result = 0
            while (queue.isNotEmpty()) {
                val top = stack.peekLast()
                when (queue.poll()) {
                    'L' -> {
                        when (top) {
                            'S' -> {
                                result++
                            }

                            'R' -> {
                                result += 2
                                stack.pollLast()
                                queue.push('S')
                            }
                        }
                    }

                    'R' -> {
                        stack.add('R')
                    }

                    'S' -> {
                        when (top) {
                            'R' -> {
                                result++
                                stack.pollLast()
                                queue.push('S')
                            }

                            else -> {
                                stack.add('S')
                            }
                        }
                    }
                }
            }

            return result
        }
    }

    expect(5) {
        Solution().countCollisions(
            "RLRSLL"
        )
    }
}