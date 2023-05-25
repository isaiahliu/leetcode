package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun gridIllumination(n: Int, lamps: Array<IntArray>, queries: Array<IntArray>): IntArray {
            val around = arrayOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 0, 0 to 1, 1 to -1, 1 to 0, 1 to 1)

            val rows = hashMapOf<Int, Int>()
            val columns = hashMapOf<Int, Int>()
            val topLefts = hashMapOf<Int, Int>()
            val topRights = hashMapOf<Int, Int>()

            val lampSet = hashSetOf<Pair<Int, Int>>()

            fun toggle(r: Int, c: Int, open: Boolean) {
                var d = 0
                if (open) {
                    if (!lampSet.add(r to c)) {
                        return
                    }
                    d++
                } else {
                    if (!lampSet.remove(r to c)) {
                        return
                    }
                    d--
                }

                ((rows[r] ?: 0) + d).takeIf { it > 0 }?.also { rows[r] = it } ?: rows.remove(r)
                ((columns[c] ?: 0) + d).takeIf { it > 0 }?.also { columns[c] = it } ?: columns.remove(c)
                ((topLefts[r - c] ?: 0) + d).takeIf { it > 0 }?.also { topLefts[r - c] = it } ?: topLefts.remove(r - c)
                ((topRights[r + c] ?: 0) + d).takeIf { it > 0 }?.also { topRights[r + c] = it }
                    ?: topRights.remove(r + c)
            }

            lamps.forEach { (r, c) ->
                toggle(r, c, true)
            }

            return IntArray(queries.size) {
                val (qr, qc) = queries[it]

                var result = 0

                when {
                    qr in rows -> result++
                    qc in columns -> result++
                    qr - qc in topLefts -> result++
                    qr + qc in topRights -> result++
                }

                around.forEach { (dr, dc) ->
                    toggle(qr + dr, qc + dc, false)
                }

                result
            }
        }
    }

    measureTimeMillis {
        Solution().gridIllumination(
            5, arrayOf(intArrayOf(0, 0), intArrayOf(4, 4)), arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 0),
            )
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
