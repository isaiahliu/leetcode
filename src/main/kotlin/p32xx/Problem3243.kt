package p32xx

import util.expect

fun main() {
    class Solution {
        fun shortestDistanceAfterQueries(n: Int, queries: Array<IntArray>): IntArray {
            val distance = IntArray(n) { it }

            val nexts = Array(n) { sortedSetOf(compareByDescending { it }, it + 1) }
            nexts.last().clear()

            fun mark(pos: Int, d: Int) {
                if (distance[pos] > d) {
                    distance[pos] = d

                    nexts[pos].forEach {
                        mark(it, d + 1)
                    }
                }
            }
            return queries.map { (from, to) ->
                nexts[from] += to

                mark(to, distance[from] + 1)

                distance.last()
            }.toIntArray()
        }
    }

    expect {
        Solution().shortestDistanceAfterQueries(
            43,
            arrayOf(
                intArrayOf(
                    3, 42
                ),
                intArrayOf(11, 19),
                intArrayOf(6, 33),
                intArrayOf(1, 20),
                intArrayOf(19, 22),
                intArrayOf(4, 21),
                intArrayOf(10, 35),
                intArrayOf(3, 38),
                intArrayOf(16, 31),
                intArrayOf(2, 10),
                intArrayOf(6, 37),
                intArrayOf(2, 13),
                intArrayOf(31, 41),
                intArrayOf(29, 39),
                intArrayOf(5, 8),
                intArrayOf(3, 11),
                intArrayOf(7, 22),
                intArrayOf(29, 33),
                intArrayOf(18, 27),
                intArrayOf(20, 22),
                intArrayOf(1, 29),
                intArrayOf(27, 38),
                intArrayOf(2, 15),
                intArrayOf(8, 32),
                intArrayOf(7, 11),
                intArrayOf(1, 6),
                intArrayOf(18, 33),
                intArrayOf(4, 34),
                intArrayOf(6, 22),
                intArrayOf(17, 33),
                intArrayOf(20, 32),
                intArrayOf(0, 12),
                intArrayOf(18, 41),
                intArrayOf(11, 33),
                intArrayOf(11, 28),
                intArrayOf(13, 35),
                intArrayOf(7, 29),
                intArrayOf(1, 25),
                intArrayOf(0, 38),
                intArrayOf(23, 32),
                intArrayOf(0, 35),
                intArrayOf(20, 42),
                intArrayOf(1, 36),
                intArrayOf(28, 31),
                intArrayOf(4, 9),
                intArrayOf(22, 30),
                intArrayOf(4, 32),
                intArrayOf(15, 27),
                intArrayOf(34, 41),
                intArrayOf(9, 13),
                intArrayOf(14, 29),
                intArrayOf(15, 36),
                intArrayOf(4, 22),
                intArrayOf(13, 41),
                intArrayOf(18, 39),
                intArrayOf(7, 18),
                intArrayOf(11, 14),
                intArrayOf(20, 28),
                intArrayOf(29, 32),
                intArrayOf(33, 36),
                intArrayOf(6, 10),
                intArrayOf(20, 27),
                intArrayOf(12, 16),
                intArrayOf(15, 20),
                intArrayOf(10, 25),
                intArrayOf(9, 40),
                intArrayOf(21, 38),
                intArrayOf(10, 36),
                intArrayOf(20, 23),
                intArrayOf(17, 34),
                intArrayOf(36, 41),
                intArrayOf(8, 28),
                intArrayOf(8, 29),
                intArrayOf(25, 31),
                intArrayOf(27, 41),
                intArrayOf(2, 42),
                intArrayOf(4, 11),
                intArrayOf(0, 2),
                intArrayOf(12, 35),
                intArrayOf(1, 8),
                intArrayOf(4, 14),
                intArrayOf(28, 42),
                intArrayOf(16, 34),
                intArrayOf(7, 13),
                intArrayOf(28, 38),
                intArrayOf(0, 25),
                intArrayOf(17, 23),
                intArrayOf(5, 36),
                intArrayOf(24, 34),
                intArrayOf(0, 3),
                intArrayOf(19, 35),
                intArrayOf(32, 41),
                intArrayOf(10, 24),
                intArrayOf(3, 35),
                intArrayOf(4, 20),
                intArrayOf(2, 35),
                intArrayOf(17, 40),
                intArrayOf(5, 18),
                intArrayOf(17, 29),
                intArrayOf(20, 33),
                intArrayOf(12, 25),
                intArrayOf(5, 28),
                intArrayOf(7, 27),
                intArrayOf(35, 39),
                intArrayOf(11, 30),
                intArrayOf(35, 40),
                intArrayOf(29, 34),
                intArrayOf(12, 38),
                intArrayOf(11, 17),
                intArrayOf(15, 40),
                intArrayOf(27, 31),
                intArrayOf(6, 24),
                intArrayOf(4, 18),
                intArrayOf(7, 19),
                intArrayOf(20, 29),
                intArrayOf(10, 33),
                intArrayOf(27, 36),
                intArrayOf(9, 23),
                intArrayOf(7, 16),
                intArrayOf(35, 38),
                intArrayOf(7, 26),
                intArrayOf(25, 40),
                intArrayOf(16, 23),
                intArrayOf(25, 29),
                intArrayOf(7, 35),
                intArrayOf(24, 28),
                intArrayOf(6, 42),
                intArrayOf(40, 42),
                intArrayOf(9, 12),
                intArrayOf(14, 16),
                intArrayOf(15, 17),
                intArrayOf(19, 41),
                intArrayOf(0, 24),
                intArrayOf(28, 41),
                intArrayOf(5, 22),
                intArrayOf(2, 37),
                intArrayOf(9, 19),
                intArrayOf(13, 38),
                intArrayOf(25, 41),
                intArrayOf(15, 35),
                intArrayOf(24, 38),
                intArrayOf(26, 29),
                intArrayOf(16, 24),
                intArrayOf(0, 33),
                intArrayOf(9, 33),
                intArrayOf(12, 31),
                intArrayOf(2, 21),
                intArrayOf(34, 39),
                intArrayOf(23, 35),
                intArrayOf(15, 33),
                intArrayOf(2, 32),
                intArrayOf(14, 17),
                intArrayOf(20, 41),
                intArrayOf(5, 34),
                intArrayOf(6, 34),
                intArrayOf(8, 34),
                intArrayOf(16, 41),
                intArrayOf(12, 23),
                intArrayOf(9, 24),
                intArrayOf(26, 41),
                intArrayOf(31, 34),
                intArrayOf(17, 32),
                intArrayOf(9, 34),
                intArrayOf(13, 33),
                intArrayOf(29, 41),
                intArrayOf(7, 14),
                intArrayOf(18, 24),
                intArrayOf(0, 14),
                intArrayOf(11, 15),
                intArrayOf(36, 38),
                intArrayOf(12, 27),
                intArrayOf(14, 23),
                intArrayOf(22, 41),
                intArrayOf(10, 31),
                intArrayOf(3, 8),
                intArrayOf(31, 36),
                intArrayOf(18, 20),
                intArrayOf(5, 26),
                intArrayOf(5, 39),
                intArrayOf(9, 22),
                intArrayOf(23, 38),
                intArrayOf(9, 42),
                intArrayOf(32, 38),
                intArrayOf(14, 33),
                intArrayOf(5, 35),
                intArrayOf(5, 19),
                intArrayOf(5, 27),
                intArrayOf(6, 41),
                intArrayOf(22, 35),
                intArrayOf(8, 14),
                intArrayOf(17, 26),
                intArrayOf(6, 27),
                intArrayOf(32, 40),
                intArrayOf(6, 36),
                intArrayOf(18, 35),
                intArrayOf(0, 7),
                intArrayOf(7, 25),
                intArrayOf(14, 31),
                intArrayOf(21, 39),
                intArrayOf(3, 40),
                intArrayOf(3, 41),
                intArrayOf(16, 36),
                intArrayOf(20, 36),
                intArrayOf(5, 30),
                intArrayOf(2, 4),
                intArrayOf(23, 37),
                intArrayOf(10, 28),
                intArrayOf(14, 41),
                intArrayOf(33, 35),
                intArrayOf(22, 31),
                intArrayOf(5, 9),
                intArrayOf(16, 27),
                intArrayOf(26, 35),
                intArrayOf(9, 32),
                intArrayOf(11, 40),
                intArrayOf(12, 18),
                intArrayOf(35, 42),
                intArrayOf(12, 26),
                intArrayOf(13, 20),
                intArrayOf(5, 37),
                intArrayOf(19, 33),
                intArrayOf(0, 20)
            )
        )
    }
}
