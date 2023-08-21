package p03xx

import kotlin.random.Random
import util.expect

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

    expect {
        val r = RandomizedSet()
        r.insert(0)
        r.remove(0)

        r.insert(-1)
        r.remove(0)

        r.getRandom()
        r.getRandom()
        r.getRandom()
        r.getRandom()
        r.getRandom()
    }
}

