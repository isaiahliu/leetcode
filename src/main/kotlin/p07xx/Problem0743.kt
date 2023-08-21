package p07xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
            val map = times.groupBy({ it[0] }, { it[1] to it[2] })

            var currentTime = -1
            val timeLine = TreeMap<Int, MutableSet<Int>>()
            timeLine[0] = hashSetOf(k)
            val scheduled = hashMapOf(k to 0)

            while (true) {
                timeLine.higherEntry(currentTime)?.also { (t, nodes) ->
                    currentTime = t

                    while (nodes.isNotEmpty()) {
                        val node = nodes.first()
                        nodes.remove(node)
                        map[node]?.forEach loop@{ (to, delay) ->
                            val reachTime = currentTime + delay
                            scheduled[to]?.also {
                                if (it > reachTime) {
                                    timeLine[it]?.also { set ->
                                        set.remove(to)
                                        if (set.isEmpty()) {
                                            timeLine.remove(it)
                                        }
                                    }
                                } else {
                                    return@loop
                                }
                            }

                            scheduled[to] = reachTime
                            timeLine.computeIfAbsent(reachTime) { hashSetOf() }.add(to)
                        }
                    }
                } ?: break
            }

            return if (scheduled.size == n) {
                currentTime
            } else {
                -1
            }
        }
    }

    expect {
        Solution().networkDelayTime(
            arrayOf(
                intArrayOf(2, 13, 18),
                intArrayOf(15, 10, 92),
                intArrayOf(6, 15, 80),
                intArrayOf(2, 14, 68),
                intArrayOf(13, 14, 65),
                intArrayOf(14, 3, 63),
                intArrayOf(10, 13, 59),
                intArrayOf(9, 7, 42),
                intArrayOf(1, 14, 70),
                intArrayOf(15, 14, 34),
                intArrayOf(11, 1, 48),
                intArrayOf(6, 7, 2),
                intArrayOf(8, 13, 48),
                intArrayOf(15, 6, 92),
                intArrayOf(8, 7, 19),
                intArrayOf(9, 14, 53),
                intArrayOf(3, 5, 48),
                intArrayOf(3, 10, 70),
                intArrayOf(3, 8, 57),
                intArrayOf(5, 15, 5),
                intArrayOf(10, 14, 8),
                intArrayOf(9, 3, 8),
                intArrayOf(15, 8, 52),
                intArrayOf(10, 5, 96),
                intArrayOf(4, 7, 52),
                intArrayOf(14, 13, 87),
                intArrayOf(14, 10, 91),
                intArrayOf(5, 2, 17),
                intArrayOf(3, 15, 5),
                intArrayOf(5, 1, 39),
                intArrayOf(13, 3, 39),
                intArrayOf(7, 13, 71),
                intArrayOf(13, 2, 41),
                intArrayOf(4, 13, 20),
                intArrayOf(11, 12, 61),
                intArrayOf(8, 4, 4),
                intArrayOf(9, 8, 80),
                intArrayOf(9, 2, 45),
                intArrayOf(7, 9, 88),
                intArrayOf(8, 15, 96),
                intArrayOf(1, 12, 92),
                intArrayOf(2, 7, 0),
                intArrayOf(7, 2, 43),
                intArrayOf(3, 9, 21),
                intArrayOf(4, 2, 95),
                intArrayOf(2, 12, 35),
                intArrayOf(2, 5, 32),
                intArrayOf(1, 9, 97),
                intArrayOf(4, 9, 95),
                intArrayOf(15, 4, 81),
                intArrayOf(5, 13, 30),
                intArrayOf(1, 6, 43),
                intArrayOf(1, 7, 22),
                intArrayOf(10, 3, 60),
                intArrayOf(11, 4, 9),
                intArrayOf(4, 11, 55),
                intArrayOf(14, 5, 5),
                intArrayOf(7, 4, 13),
                intArrayOf(15, 13, 72),
                intArrayOf(11, 3, 55),
                intArrayOf(11, 8, 50),
                intArrayOf(3, 7, 25),
                intArrayOf(12, 11, 29),
                intArrayOf(7, 10, 71),
                intArrayOf(7, 5, 24),
                intArrayOf(12, 14, 18),
                intArrayOf(9, 13, 89),
                intArrayOf(7, 3, 25),
                intArrayOf(2, 9, 2),
                intArrayOf(5, 11, 83),
                intArrayOf(6, 4, 48),
                intArrayOf(14, 1, 27),
                intArrayOf(14, 11, 21),
                intArrayOf(8, 14, 12),
                intArrayOf(10, 1, 74),
                intArrayOf(4, 1, 97),
                intArrayOf(4, 10, 46),
                intArrayOf(14, 8, 16),
                intArrayOf(13, 5, 39),
                intArrayOf(9, 4, 6),
                intArrayOf(11, 7, 98),
                intArrayOf(1, 13, 10),
                intArrayOf(8, 11, 22),
                intArrayOf(9, 11, 96),
                intArrayOf(1, 8, 56),
                intArrayOf(3, 14, 81),
                intArrayOf(6, 11, 45),
                intArrayOf(5, 4, 48),
                intArrayOf(4, 6, 71),
                intArrayOf(11, 15, 64),
                intArrayOf(3, 12, 74),
                intArrayOf(2, 6, 71),
                intArrayOf(7, 8, 35),
                intArrayOf(11, 2, 20),
                intArrayOf(7, 12, 12),
                intArrayOf(6, 14, 8),
                intArrayOf(2, 15, 42),
                intArrayOf(8, 2, 24),
                intArrayOf(6, 12, 67),
                intArrayOf(5, 8, 90),
                intArrayOf(2, 10, 36),
                intArrayOf(15, 7, 0),
                intArrayOf(15, 1, 68),
                intArrayOf(12, 4, 43),
                intArrayOf(1, 5, 78),
                intArrayOf(13, 9, 97),
                intArrayOf(2, 4, 51),
                intArrayOf(13, 15, 39),
                intArrayOf(9, 12, 93),
                intArrayOf(5, 3, 79),
                intArrayOf(7, 1, 34),
                intArrayOf(8, 12, 37),
                intArrayOf(12, 15, 36),
                intArrayOf(8, 5, 92),
                intArrayOf(7, 11, 96),
                intArrayOf(14, 9, 94),
                intArrayOf(8, 1, 31),
                intArrayOf(14, 2, 18),
                intArrayOf(2, 8, 62),
                intArrayOf(15, 3, 84),
                intArrayOf(6, 1, 3),
                intArrayOf(10, 4, 91),
                intArrayOf(1, 3, 75),
                intArrayOf(1, 4, 9),
                intArrayOf(11, 10, 69),
                intArrayOf(7, 15, 88),
                intArrayOf(6, 9, 25),
                intArrayOf(9, 6, 44),
                intArrayOf(6, 8, 68),
                intArrayOf(6, 2, 96),
                intArrayOf(1, 15, 16),
                intArrayOf(6, 3, 61),
                intArrayOf(12, 9, 50),
                intArrayOf(13, 11, 27),
                intArrayOf(8, 6, 40),
                intArrayOf(13, 12, 45),
                intArrayOf(14, 7, 61),
                intArrayOf(4, 15, 8),
                intArrayOf(12, 2, 87),
                intArrayOf(14, 4, 94),
                intArrayOf(11, 6, 37),
                intArrayOf(12, 8, 10),
                intArrayOf(13, 6, 0),
                intArrayOf(9, 15, 70),
                intArrayOf(1, 10, 26),
                intArrayOf(14, 6, 30),
                intArrayOf(15, 2, 58),
                intArrayOf(3, 1, 12),
                intArrayOf(10, 7, 96),
                intArrayOf(2, 3, 4),
                intArrayOf(5, 14, 99),
                intArrayOf(8, 3, 85),
                intArrayOf(12, 10, 38),
                intArrayOf(14, 15, 34),
                intArrayOf(4, 8, 31),
                intArrayOf(10, 8, 13),
                intArrayOf(4, 12, 57),
                intArrayOf(12, 7, 4),
                intArrayOf(3, 2, 65),
                intArrayOf(15, 5, 85),
                intArrayOf(12, 5, 42),
                intArrayOf(3, 6, 53),
                intArrayOf(4, 3, 3),
                intArrayOf(10, 15, 29),
                intArrayOf(9, 5, 47),
                intArrayOf(4, 5, 43),
                intArrayOf(9, 1, 98),
                intArrayOf(13, 4, 72),
                intArrayOf(3, 4, 88),
                intArrayOf(5, 9, 21),
                intArrayOf(10, 12, 41),
                intArrayOf(13, 10, 3),
                intArrayOf(3, 11, 77),
                intArrayOf(13, 7, 21),
                intArrayOf(5, 7, 88),
                intArrayOf(6, 5, 22),
                intArrayOf(12, 6, 72),
                intArrayOf(15, 12, 37),
                intArrayOf(9, 10, 94),
                intArrayOf(11, 14, 0),
                intArrayOf(1, 11, 51),
                intArrayOf(5, 10, 44),
                intArrayOf(14, 12, 34),
                intArrayOf(15, 9, 85),
                intArrayOf(11, 13, 74),
                intArrayOf(6, 10, 54),
                intArrayOf(8, 10, 9),
                intArrayOf(13, 8, 68),
                intArrayOf(2, 11, 13),
                intArrayOf(2, 1, 91),
                intArrayOf(12, 3, 31),
                intArrayOf(12, 13, 99),
                intArrayOf(1, 2, 84),
                intArrayOf(12, 1, 89),
                intArrayOf(4, 14, 9),
                intArrayOf(5, 12, 34),
                intArrayOf(7, 14, 53),
                intArrayOf(7, 6, 87),
                intArrayOf(11, 9, 20),
                intArrayOf(3, 13, 6),
                intArrayOf(6, 13, 40),
                intArrayOf(13, 1, 94),
                intArrayOf(10, 11, 20),
                intArrayOf(10, 6, 67),
                intArrayOf(5, 6, 27),
                intArrayOf(8, 9, 97),
                intArrayOf(11, 5, 66),
                intArrayOf(10, 2, 73),
                intArrayOf(10, 9, 17),
                intArrayOf(15, 11, 48)
            ), 15, 2
        )
    }
}