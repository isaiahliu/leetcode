package p03xx

import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    class RandomizedSet {
        private val array = IntArray(200001)
        private var lastIndex = -1

        private val map = hashMapOf<Int, Int>()

        fun insert(`val`: Int): Boolean {
            if (`val` in map) {
                return false
            }

            lastIndex++
            array[lastIndex] = `val`

            map[`val`] = lastIndex

            return true
        }

        fun remove(`val`: Int): Boolean {
            val valueIndex = map[`val`] ?: return false

            array[valueIndex] = array[lastIndex]

            map[array[valueIndex]] = valueIndex

            map.remove(`val`)

            lastIndex--

            return true
        }

        fun getRandom(): Int {
            return array[Random.nextInt(lastIndex + 1)]
        }
    }

    measureTimeMillis {
        RandomizedSet().insert(
            1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

