package p03xx

import java.util.*
import util.expect

fun main() {
    class SummaryRanges {
        val map = TreeMap<Int, Int>()

        private var cache: Array<IntArray>? = null

        fun addNum(value: Int) {
            if (value !in map) {
                var leftKey = map.lowerKey(value)
                var leftRight = leftKey?.let { map[it] } ?: 0

                val rightKey = map.higherKey(value)
                val rightRight = rightKey?.let { map[it] } ?: 0

                if (leftKey != null) {
                    when {
                        leftRight + 1 < value -> {
                            leftKey = value
                            leftRight = value
                            cache = null
                        }

                        leftRight + 1 == value -> {
                            leftRight = value
                            cache = null
                        }

                        else -> {
                            return
                        }
                    }
                } else {
                    leftKey = value
                    leftRight = value
                    cache = null
                }

                if (rightKey != null && rightKey == leftRight + 1) {
                    leftRight = rightRight
                    map.remove(rightKey)
                    cache = null
                }

                map[leftKey] = leftRight
            }
        }

        fun getIntervals(): Array<IntArray> {
            return cache ?: map.entries.map { intArrayOf(it.key, it.value) }.toTypedArray().also { cache = it }

        }
    }
    expect {
        val t = SummaryRanges()
        t.addNum(1)
        t.addNum(3)
        t.addNum(2)
        t.getIntervals()
    }
}

