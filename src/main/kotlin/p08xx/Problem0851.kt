package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun loudAndRich(richer: Array<IntArray>, quiet: IntArray): IntArray {
            class Person(val idx: Int) {
                val richers = arrayListOf<Person>()

                var _silentPerson: Int? = null

                val silentPerson: Int
                    get() {
                        return _silentPerson ?: run {
                            val r = richers.map { it.silentPerson }.minByOrNull { quiet[it] }
                                ?.takeIf { quiet[it] < quiet[idx] } ?: idx

                            _silentPerson = r

                            r
                        }
                    }
            }

            val persons = Array(quiet.size) {
                Person(it)
            }

            richer.map { (r, p) ->
                persons[p].richers.add(persons[r])
            }

            return persons.map { it.silentPerson }.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().loudAndRich(
            arrayOf(
                intArrayOf(2, 58, 59, 89),
                intArrayOf(75, 35, 94, 43),
                intArrayOf(21, 3, 92, 62),
                intArrayOf(51, 75, 72, 91)
            ), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}