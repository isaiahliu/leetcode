package p12xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxCandies(
            status: IntArray,
            candies: IntArray,
            keys: Array<IntArray>,
            containedBoxes: Array<IntArray>,
            initialBoxes: IntArray
        ): Int {
            val awaitingBoxes = LinkedList<Int>()

            fun IntArray.open(type: Int) {
                forEach {
                    if (status[it] < 3) {
                        status[it] = status[it] or type
                        if (status[it] == 3) {
                            awaitingBoxes.add(it)
                        }
                    }
                }
            }

            initialBoxes.open(2)

            var result = 0
            while (true) {
                val box = awaitingBoxes.poll() ?: break
                result += candies[box]
                keys[box].open(1)
                containedBoxes[box].open(2)
            }

            return result
        }
    }

    expect {
        Solution().maxCandies(
            intArrayOf(
                1,
                1,
                0,
                1,
                1,
                0,
                0,
                1,
                0,
                0,
                1,
                1,
                0,
                0,
                0,
                0,
                1,
                0,
                1,
                1,
                0,
                0,
                0,
                0,
                1,
                0,
                0,
                0,
                1,
                0,
                0,
                1,
                1,
                1,
                1,
                1,
                0,
                1,
                1,
                0,
                1,
                1,
                1,
                1,
                0,
                0,
                1,
                0,
                0
            ), intArrayOf(
                732,
                320,
                543,
                300,
                814,
                568,
                947,
                685,
                142,
                111,
                805,
                233,
                813,
                306,
                55,
                1,
                290,
                944,
                36,
                592,
                150,
                596,
                372,
                299,
                644,
                445,
                605,
                202,
                64,
                807,
                753,
                731,
                552,
                766,
                119,
                862,
                453,
                136,
                43,
                572,
                801,
                518,
                936,
                408,
                515,
                215,
                492,
                738,
                154
            ), arrayOf(
                intArrayOf(42, 2, 24, 8, 39, 16, 46),
                intArrayOf(20, 39, 46, 21, 32, 31, 43, 16, 12, 23, 3),
                intArrayOf(21, 14, 30, 2, 11, 13, 27, 37, 4, 48),
                intArrayOf(16, 17, 15, 6),
                intArrayOf(31, 14, 3, 32, 35, 19, 42, 43, 44, 29, 25, 41),
                intArrayOf(7, 39, 2, 3, 40, 28, 37, 35, 43, 22, 6, 23, 48, 10, 21, 11),
                intArrayOf(27, 1, 37, 3, 45, 32, 30, 26, 16, 2, 35, 19, 31, 47, 5, 14),
                intArrayOf(28, 35, 23, 17, 6),
                intArrayOf(6, 39, 34, 22),
                intArrayOf(44, 29, 36, 31, 40, 22, 9, 11, 17, 25, 1, 14, 41),
                intArrayOf(39, 37, 11, 36, 17, 42, 13, 12, 7, 9, 43, 41),
                intArrayOf(23, 16, 32, 37),
                intArrayOf(36, 39, 21, 41),
                intArrayOf(15, 27, 5, 42),
                intArrayOf(11, 5, 18, 48, 25, 47, 17, 0, 41, 26, 9, 29),
                intArrayOf(18, 36, 40, 35, 12, 33, 11, 5, 44, 14, 46, 7),
                intArrayOf(48, 22, 11, 33, 14),
                intArrayOf(44, 12, 3, 31, 25, 15, 18, 28, 42, 43),
                intArrayOf(36, 9, 0, 42),
                intArrayOf(1, 22, 3, 24, 9, 11, 43, 8, 35, 5, 41, 29, 40),
                intArrayOf(15, 47, 32, 28, 33, 31, 4, 43),
                intArrayOf(1, 11, 6, 37, 28),
                intArrayOf(46, 20, 47, 32, 26, 15, 11, 40),
                intArrayOf(33, 45, 26, 40, 12, 3, 16, 18, 10, 28, 5),
                intArrayOf(14, 6, 4, 46, 34, 9, 33, 24, 30, 12, 37),
                intArrayOf(45, 24, 18, 31, 32, 39, 26, 27),
                intArrayOf(29, 0, 32, 15, 7, 48, 36, 26, 33, 31, 18, 39, 23, 34, 44),
                intArrayOf(25, 16, 42, 31, 41, 35, 26, 10, 3, 1, 4, 29),
                intArrayOf(8, 11, 5, 40, 9, 18, 10, 16, 26, 30, 19, 2, 14, 4),
                intArrayOf(),
                intArrayOf(0, 20, 17, 47, 41, 36, 23, 42, 15, 13, 27),
                intArrayOf(7, 15, 44, 38, 41, 42, 26, 19, 5, 47),
                intArrayOf(),
                intArrayOf(37, 22),
                intArrayOf(21, 24, 15, 48, 33, 6, 39, 11),
                intArrayOf(23, 7, 3, 29, 10, 40, 1, 16, 6, 8, 27),
                intArrayOf(27, 29, 25, 26, 46, 15, 16),
                intArrayOf(33, 40, 10, 38, 13, 19, 17, 23, 32, 39, 7),
                intArrayOf(35, 3, 39, 18),
                intArrayOf(47, 11, 27, 23, 35, 26, 43, 4, 22, 38, 44, 31, 1, 0),
                intArrayOf(),
                intArrayOf(18, 43, 46, 9, 15, 3, 42, 31, 13, 4, 12, 39, 22),
                intArrayOf(42, 45, 47, 18, 26, 41, 38, 9, 0, 35, 8, 16, 29, 36, 31),
                intArrayOf(3, 20, 29, 12, 46, 41, 23, 4, 9, 27),
                intArrayOf(19, 33),
                intArrayOf(32, 18),
                intArrayOf(17, 28, 7, 35, 6, 22, 4, 43),
                intArrayOf(41, 31, 20, 28, 35, 32, 24, 23, 0, 33, 18, 39, 29, 30, 16),
                intArrayOf(43, 47, 46)
            ), arrayOf(
                intArrayOf(14),
                intArrayOf(),
                intArrayOf(26),
                intArrayOf(4, 47),
                intArrayOf(),
                intArrayOf(6),
                intArrayOf(39, 43, 46),
                intArrayOf(30),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(0, 3),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(27),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(12),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(41),
                intArrayOf(),
                intArrayOf(31),
                intArrayOf(20, 29),
                intArrayOf(13, 35),
                intArrayOf(18),
                intArrayOf(10, 40),
                intArrayOf(),
                intArrayOf(38),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(19),
                intArrayOf(5),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(11),
                intArrayOf(1),
                intArrayOf(15),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(24),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf()
            ), intArrayOf(
                2, 7, 8, 9, 16, 17, 21, 22, 23, 25, 28, 32, 33, 34, 36, 37, 42, 44, 45, 48
            )

        )
    }
}
