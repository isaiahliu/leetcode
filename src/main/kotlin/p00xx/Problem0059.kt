package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun generateMatrix(n: Int): Array<IntArray> {
            val result = Array(n) { IntArray(n) { 1 } }

            if (n == 1) {
                return result
            }

            val up = 0
            val right = 1
            val down = 2
            val left = 3

            var rowIndex = 0
            var columnIndex = 0

            var minRowIndex = 0
            var maxRowIndex = n - 1
            var minColumnIndex = 0
            var maxColumnIndex = n - 1

            var direction = right

            var current = 2

            while (minRowIndex <= maxRowIndex && minColumnIndex <= maxColumnIndex) {
                when (direction % 4) {
                    up -> {
                        repeat(rowIndex - minRowIndex) {
                            rowIndex--
                            result[rowIndex][columnIndex] = current++
                        }

                        minColumnIndex++
                    }

                    right -> {
                        repeat(maxColumnIndex - columnIndex) {
                            columnIndex++
                            result[rowIndex][columnIndex] = current++
                        }

                        minRowIndex++
                    }

                    down -> {
                        repeat(maxRowIndex - rowIndex) {
                            rowIndex++
                            result[rowIndex][columnIndex] = current++
                        }

                        maxColumnIndex--
                    }

                    left -> {
                        repeat(columnIndex - minColumnIndex) {
                            columnIndex--
                            result[rowIndex][columnIndex] = current++
                        }

                        maxRowIndex--
                    }
                }

                direction++
            }

            return result
        }
    }

    measureTimeMillis {
        println(Solution().generateMatrix(5))
    }.also { println("Time cost: ${it}ms") }
}


