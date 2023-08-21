package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxBuilding(n: Int, restrictions: Array<IntArray>): Int {
            val queue = LinkedList<Pair<Int, Int>>()
            queue.add(1 to 0)

            var result = 0
            restrictions.sortedBy { it[0] }.forEach { (index, height) ->
                while (queue.isNotEmpty()) {
                    val (lastIndex, lastHeight) = queue.peekLast()

                    if (height < lastHeight - (index - lastIndex)) {
                        queue.pollLast()
                    } else {
                        break
                    }
                }

                val (lastIndex, lastHeight) = queue.peekLast()
                if (height < lastHeight + index - lastIndex) {
                    queue.add(index to height)
                }
            }

            var last = queue.poll()
            while (queue.isNotEmpty()) {
                val (lastIndex, lastHeight) = last
                last = queue.poll()
                val (index, height) = last

                val minHeight = height.coerceAtMost(lastHeight)
                val maxHeight = height.coerceAtLeast(lastHeight)
                result = result.coerceAtLeast((index - lastIndex - maxHeight + minHeight) / 2 + maxHeight)
            }

            result = result.coerceAtLeast(last.second + n - last.first)

            return result
        }
    }

    expect {
        Solution().maxBuilding(
            100, arrayOf(
                intArrayOf(68, 29),
                intArrayOf(89, 27),
                intArrayOf(66, 26),
                intArrayOf(34, 9),
                intArrayOf(53, 23),
                intArrayOf(93, 24),
                intArrayOf(70, 12),
                intArrayOf(25, 24),
                intArrayOf(5, 4),
                intArrayOf(94, 41),
                intArrayOf(51, 42),
                intArrayOf(6, 39),
                intArrayOf(55, 21),
                intArrayOf(69, 9),
                intArrayOf(39, 50),
                intArrayOf(99, 42),
                intArrayOf(77, 24),
                intArrayOf(81, 46),
                intArrayOf(90, 43),
                intArrayOf(27, 14),
                intArrayOf(31, 5),
                intArrayOf(67, 37),
                intArrayOf(82, 10),
                intArrayOf(26, 47),
                intArrayOf(84, 34),
                intArrayOf(37, 30),
                intArrayOf(83, 39),
                intArrayOf(21, 39),
                intArrayOf(49, 39),
                intArrayOf(13, 48),
                intArrayOf(12, 34),
                intArrayOf(57, 0),
                intArrayOf(7, 43),
                intArrayOf(17, 6),
                intArrayOf(23, 0),
                intArrayOf(86, 30),
                intArrayOf(47, 30),
                intArrayOf(61, 19),
                intArrayOf(30, 49),
                intArrayOf(95, 42),
                intArrayOf(3, 31),
                intArrayOf(33, 36),
                intArrayOf(11, 45),
                intArrayOf(75, 39),
                intArrayOf(85, 46),
                intArrayOf(29, 33),
                intArrayOf(2, 9),
                intArrayOf(22, 17),
                intArrayOf(65, 42),
                intArrayOf(96, 0),
                intArrayOf(35, 46),
                intArrayOf(88, 47),
                intArrayOf(74, 0),
                intArrayOf(73, 47),
                intArrayOf(41, 45),
                intArrayOf(15, 21),
                intArrayOf(97, 0),
                intArrayOf(64, 0),
                intArrayOf(40, 21),
                intArrayOf(76, 2),
                intArrayOf(54, 3),
                intArrayOf(24, 33),
                intArrayOf(45, 24),
                intArrayOf(16, 23),
                intArrayOf(91, 14),
                intArrayOf(43, 35),
                intArrayOf(79, 6),
                intArrayOf(46, 30),
                intArrayOf(71, 3),
                intArrayOf(9, 39),
                intArrayOf(50, 21),
                intArrayOf(48, 45),
                intArrayOf(63, 42),
                intArrayOf(58, 3),
                intArrayOf(10, 26),
                intArrayOf(4, 6),
                intArrayOf(52, 19),
                intArrayOf(32, 39),
                intArrayOf(87, 50),
                intArrayOf(8, 48),
                intArrayOf(19, 25),
                intArrayOf(92, 1),
                intArrayOf(28, 21),
                intArrayOf(59, 31),
                intArrayOf(72, 24),
                intArrayOf(78, 9),
                intArrayOf(100, 8),
                intArrayOf(60, 20),
                intArrayOf(42, 16),
                intArrayOf(38, 8),
                intArrayOf(62, 31),
                intArrayOf(36, 22),
                intArrayOf(44, 27),
                intArrayOf(14, 45),
                intArrayOf(18, 3),
                intArrayOf(98, 0),
                intArrayOf(20, 1),
                intArrayOf(56, 24),
                intArrayOf(80, 3)
            )
        )

    }
}
