package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun scoreOfStudents(s: String, answers: IntArray): Int {
            val operators = LinkedList<Int>()
            val stack = LinkedList<Int>()

            s.forEachIndexed { index, c ->
                when {
                    c == '+' || c == '*' -> {
                        operators.push(index)
                    }

                    operators.isNotEmpty() && s[operators.peek()] == '*' -> {
                        stack.push(stack.poll() * (c - '0'))
                        operators.poll()
                    }

                    else -> {
                        stack.push(c - '0')
                    }
                }
            }

            repeat(operators.size) {
                stack.push(stack.poll() + stack.poll())
            }

            val correctAnswer = stack.poll()

            val cache = Array(s.length) {
                Array(s.length) { emptySet<Int>() }
            }

            fun dfs(left: Int, right: Int): Set<Int> {
                if (left == right) {
                    return setOf(s[left] - '0')
                }

                if (cache[left][right].isNotEmpty()) {
                    return cache[left][right]
                }

                val result = hashSetOf<Int>()

                for (i in left + 1 until right step 2) {
                    val part1 = dfs(left, i - 1)
                    val part2 = dfs(i + 1, right)

                    val calculator = if (s[i] == '+') {
                        { a: Int, b: Int -> a + b }
                    } else {
                        { a: Int, b: Int -> a * b }
                    }
                    part1.forEach { n1 ->
                        part2.forEach { n2 ->
                            calculator(n1, n2).takeIf { it <= 1000 }?.also {
                                result.add(it)
                            }
                        }
                    }
                }

                cache[left][right] = result
                return result
            }

            val others = dfs(0, s.lastIndex)

            return answers.map {
                when (it) {
                    correctAnswer -> 5
                    in others -> 2
                    else -> 0
                }
            }.sum()
        }
    }

    expect {
        Solution().scoreOfStudents(
            "6+4*4+2+8*8+2*2+4*2+6+4*8+2*8+6", intArrayOf(587)
        )
    }
}