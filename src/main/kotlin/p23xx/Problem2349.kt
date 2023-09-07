package p23xx

import util.expect
import java.util.*

fun main() {
    class NumberContainers {
        val numbers = hashMapOf<Int, Int>()
        val numberIndices = hashMapOf<Int, TreeSet<Int>>()

        fun change(index: Int, number: Int) {
            numbers[index]?.also {
                numberIndices[it]?.remove(index)
            }

            numbers[index] = number
            numberIndices.computeIfAbsent(number) { TreeSet<Int>() }.add(index)
        }

        fun find(number: Int): Int {
            return numberIndices[number]?.firstOrNull() ?: -1
        }
    }

    expect {
        NumberContainers().find(
            5
        )
    }
}