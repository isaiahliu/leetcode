package p03xx

import kotlin.random.Random
import util.expect

fun main() {
    class RandomizedCollection {
        private val array = IntArray(200001)
        private var lastIndex = -1

        private val map = hashMapOf<Int, MutableSet<Int>>()

        fun insert(`val`: Int): Boolean {
            val valIndices = map.computeIfAbsent(`val`) { hashSetOf() }

            lastIndex++
            array[lastIndex] = `val`

            valIndices.add(lastIndex)

            return valIndices.size == 1
        }

        fun remove(`val`: Int): Boolean {
            val valueIndices = map[`val`] ?: return false

            if (lastIndex in valueIndices) {
                valueIndices.remove(lastIndex)
            } else {
                val removeIndex = valueIndices.first()

                array[removeIndex] = array[lastIndex]

                map[array[removeIndex]]?.also {
                    it.remove(lastIndex)
                    it.add(removeIndex)
                }

                valueIndices.remove(removeIndex)
            }

            if (valueIndices.isEmpty()) {
                map.remove(`val`)
            }

            lastIndex--

            return true
        }

        fun getRandom(): Int {
            return array[Random.nextInt(lastIndex + 1)]
        }
    }

    expect {
        val r = RandomizedCollection()
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

