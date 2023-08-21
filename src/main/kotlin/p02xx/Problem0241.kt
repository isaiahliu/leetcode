package p02xx

import util.expect

fun main() {
    class Solution {
        val cache = hashMapOf<String, List<Int>>()

        fun diffWaysToCompute(expression: String): List<Int> {
            if (expression in cache) {
                return cache[expression].orEmpty()
            }

            expression.toIntOrNull()?.also {
                return listOf(it).also {
                    cache[expression] = it
                }
            }

            val results = arrayListOf<Int>()
            for ((index, c) in expression.withIndex()) {
                val calculator: (Int, Int) -> Int
                when (c) {
                    '+' -> {
                        calculator = { a: Int, b: Int -> a + b }
                    }

                    '-' -> {
                        calculator = { a: Int, b: Int -> a - b }
                    }

                    '*' -> {
                        calculator = { a: Int, b: Int -> a * b }
                    }

                    else -> {
                        continue
                    }
                }

                val leftResults = diffWaysToCompute(expression.substring(0, index))
                val rightResults = diffWaysToCompute(expression.substring(index + 1))

                leftResults.map { left ->
                    rightResults.map { right ->
                        results.add(calculator(left, right))
                    }
                }
            }

            return results.also {
                cache[expression] = it
            }
        }
    }

    expect {
        Solution().diffWaysToCompute(
            "2-1-1"
        )
    }
}

