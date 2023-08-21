package p17xx

import java.math.BigInteger
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun getCoprimes(nums: IntArray, edges: Array<IntArray>): IntArray {
            val adjacent = Array(nums.size) {
                hashSetOf<Int>()
            }

            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            val coprime = Array(51) {
                hashSetOf<Int>()
            }

            for (m in 1..50) {
                for (n in m..50) {
                    if (m.toBigInteger().gcd(n.toBigInteger()) == BigInteger.ONE) {
                        coprime[m].add(n)
                        coprime[n].add(m)
                    }
                }
            }

            val indices = Array(51) {
                LinkedList<Pair<Int, Int>>()
            }

            val result = IntArray(nums.size) { -1 }
            fun dfs(index: Int, depth: Int) {
                val num = nums[index]

                coprime[num].mapNotNull { indices[it].peek() }.maxByOrNull { it.first }?.also {
                    result[index] = it.second
                }

                indices[num].push(depth to index)

                adjacent[index].forEach {
                    adjacent[it].remove(index)

                    dfs(it, depth + 1)
                }

                indices[num].poll()
            }

            dfs(0, 0)
            return result
        }
    }

    expect {
        Solution().getCoprimes(
            intArrayOf(
                18,
                10,
                23,
                47,
                11,
                20,
                7,
                44,
                14,
                43,
                43,
                42,
                2,
                23,
                5,
                31,
                18,
                40,
                49,
                27,
                50,
                21,
                19,
                35,
                23,
                30,
                31,
                8,
                7,
                50,
                7,
                11,
                4,
                43,
                1,
                5,
                24,
                44,
                24,
                25,
                24,
                19,
                48,
                5,
                37,
                13,
                50,
                6,
                20,
                38,
                43,
                45,
                34,
                15,
                42,
                41,
                5,
                44,
                16,
                21,
                26,
                31,
                12,
                35,
                13,
                36,
                2,
                21,
                29,
                36,
                7,
                24,
                1,
                37,
                40,
                6,
                19,
                30,
                12,
                42,
                30,
                50,
                20,
                15,
                34,
                36,
                49,
                2,
                34,
                36,
                38,
                8,
                11,
                33,
                46,
                19,
                24,
                41,
                2,
                31,
                14,
                32,
                9,
                29,
                12,
                6,
                45,
                47,
                32,
                24,
                37,
                4,
                25,
                50,
                24,
                10,
                31,
                40,
                5,
                12,
                22,
                7,
                23,
                2,
                27,
                42,
                8,
                6,
                1,
                15,
                16,
                32,
                32,
                38,
                29,
                24,
                33,
                22,
                33,
                29,
                17
            ), arrayOf(
                intArrayOf(57, 0),
                intArrayOf(5, 57),
                intArrayOf(76, 5),
                intArrayOf(85, 76),
                intArrayOf(46, 85),
                intArrayOf(127, 85),
                intArrayOf(25, 0),
                intArrayOf(114, 25),
                intArrayOf(7, 114),
                intArrayOf(45, 114),
                intArrayOf(100, 25),
                intArrayOf(122, 100),
                intArrayOf(17, 122),
                intArrayOf(12, 17),
                intArrayOf(48, 100),
                intArrayOf(40, 48),
                intArrayOf(60, 40),
                intArrayOf(88, 48),
                intArrayOf(108, 48),
                intArrayOf(10, 108),
                intArrayOf(11, 10),
                intArrayOf(121, 11),
                intArrayOf(9, 121),
                intArrayOf(109, 11),
                intArrayOf(111, 109),
                intArrayOf(91, 109),
                intArrayOf(118, 91),
                intArrayOf(53, 118),
                intArrayOf(26, 53),
                intArrayOf(47, 26),
                intArrayOf(126, 47),
                intArrayOf(133, 109),
                intArrayOf(123, 133),
                intArrayOf(59, 123),
                intArrayOf(81, 48),
                intArrayOf(31, 81),
                intArrayOf(15, 31),
                intArrayOf(24, 15),
                intArrayOf(132, 81),
                intArrayOf(119, 132),
                intArrayOf(21, 119),
                intArrayOf(63, 81),
                intArrayOf(128, 63),
                intArrayOf(73, 128),
                intArrayOf(34, 63),
                intArrayOf(72, 34),
                intArrayOf(38, 72),
                intArrayOf(97, 72),
                intArrayOf(3, 97),
                intArrayOf(30, 3),
                intArrayOf(13, 30),
                intArrayOf(80, 13),
                intArrayOf(33, 80),
                intArrayOf(66, 80),
                intArrayOf(102, 66),
                intArrayOf(8, 80),
                intArrayOf(77, 8),
                intArrayOf(79, 77),
                intArrayOf(42, 79),
                intArrayOf(19, 42),
                intArrayOf(78, 19),
                intArrayOf(20, 78),
                intArrayOf(55, 79),
                intArrayOf(37, 55),
                intArrayOf(49, 37),
                intArrayOf(89, 49),
                intArrayOf(36, 89),
                intArrayOf(83, 89),
                intArrayOf(95, 49),
                intArrayOf(64, 95),
                intArrayOf(28, 64),
                intArrayOf(32, 28),
                intArrayOf(92, 32),
                intArrayOf(93, 92),
                intArrayOf(86, 93),
                intArrayOf(39, 86),
                intArrayOf(87, 39),
                intArrayOf(2, 87),
                intArrayOf(134, 93),
                intArrayOf(135, 49),
                intArrayOf(110, 3),
                intArrayOf(29, 110),
                intArrayOf(52, 29),
                intArrayOf(136, 29),
                intArrayOf(99, 136),
                intArrayOf(50, 99),
                intArrayOf(84, 50),
                intArrayOf(56, 84),
                intArrayOf(51, 99),
                intArrayOf(112, 51),
                intArrayOf(101, 112),
                intArrayOf(41, 29),
                intArrayOf(74, 41),
                intArrayOf(103, 74),
                intArrayOf(129, 74),
                intArrayOf(6, 129),
                intArrayOf(137, 129),
                intArrayOf(61, 29),
                intArrayOf(104, 61),
                intArrayOf(131, 104),
                intArrayOf(58, 104),
                intArrayOf(14, 58),
                intArrayOf(18, 14),
                intArrayOf(138, 18),
                intArrayOf(117, 138),
                intArrayOf(125, 138),
                intArrayOf(106, 125),
                intArrayOf(120, 18),
                intArrayOf(130, 120),
                intArrayOf(124, 130),
                intArrayOf(62, 124),
                intArrayOf(82, 62),
                intArrayOf(4, 62),
                intArrayOf(113, 4),
                intArrayOf(139, 130),
                intArrayOf(1, 104),
                intArrayOf(67, 1),
                intArrayOf(70, 1),
                intArrayOf(43, 70),
                intArrayOf(96, 70),
                intArrayOf(98, 96),
                intArrayOf(69, 98),
                intArrayOf(94, 69),
                intArrayOf(115, 94),
                intArrayOf(75, 1),
                intArrayOf(44, 75),
                intArrayOf(68, 44),
                intArrayOf(16, 68),
                intArrayOf(54, 68),
                intArrayOf(65, 68),
                intArrayOf(27, 65),
                intArrayOf(71, 65),
                intArrayOf(105, 65),
                intArrayOf(35, 105),
                intArrayOf(107, 65),
                intArrayOf(116, 65),
                intArrayOf(90, 116),
                intArrayOf(23, 90),
                intArrayOf(140, 1),
                intArrayOf(22, 140)
            )
        ).toList()
    }
}
