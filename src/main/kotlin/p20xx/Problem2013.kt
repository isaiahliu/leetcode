package p20xx

import util.expect

fun main() {
    class DetectSquares {
        val xy = hashMapOf<Int, MutableMap<Int, Int>>()
        val yx = hashMapOf<Int, MutableMap<Int, Int>>()
        fun add(point: IntArray) {
            val (x, y) = point
            xy.computeIfAbsent(x) { hashMapOf() }.also {
                it[y] = (it[y] ?: 0) + 1
            }

            yx.computeIfAbsent(y) { hashMapOf() }.also {
                it[x] = (it[x] ?: 0) + 1
            }
        }

        fun count(point: IntArray): Int {
            val (x, y) = point

            var result = 0

            xy[x]?.forEach { (y1, count1) ->
                if (y != y1) {
                    arrayOf(y1 - y, y - y1).forEach { delta ->
                        yx[y]?.get(x + delta)?.also { count2 ->
                            yx[y1]?.get(x + delta)?.also { count3 ->
                                result += count1 * count2 * count3
                            }
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        DetectSquares().add(
            intArrayOf(1, 2)
        )
    }
}