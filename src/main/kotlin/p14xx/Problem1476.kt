package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class SubrectangleQueries(private val rectangle: Array<IntArray>) {
        val updates = LinkedList<Pair<Pair<IntRange, IntRange>, Int>>()

        fun updateSubrectangle(row1: Int, col1: Int, row2: Int, col2: Int, newValue: Int) {
            updates.push(row1..row2 to col1..col2 to newValue)
        }

        fun getValue(row: Int, col: Int): Int {
            for ((r, value) in updates) {
                val (rowRange, colRange) = r

                if (row in rowRange && col in colRange) {
                    return value
                }
            }

            return rectangle[row][col]
        }
    }

    measureTimeMillis {
        SubrectangleQueries(arrayOf()).getValue(
            1, 2
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

