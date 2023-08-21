package p19xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun countPaths(n: Int, roads: Array<IntArray>): Int {
            val m = 1000000007L
            val adjacent = Array(n) { hashMapOf<Int, Long>() }
            roads.forEach { (from, to, time) ->
                adjacent[from][to] = time.toLong()
                adjacent[to][from] = time.toLong()
            }

            val sums = Array(n) { longArrayOf(Long.MAX_VALUE, 0L) }
            sums[0][0] = 0
            sums[0][1] = 1

            val queue = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
            queue.add(0 to 0)

            while (queue.isNotEmpty()) {
                val (index, t) = queue.poll()

                val (baseTime, count) = sums[index]

                if (t > baseTime) {
                    continue
                }

                adjacent[index].forEach { (to, time) ->
                    (baseTime + time).also {
                        when {
                            sums[to][0] == it -> {
                                sums[to][1] += count
                                sums[to][1] %= m
                            }

                            sums[to][0] > it -> {
                                sums[to][0] = it
                                sums[to][1] = count
                                queue.add(to to it)
                            }
                        }
                    }
                }
            }

            return sums[sums.lastIndex][1].toInt()
        }
    }

    expect {
        Solution().countPaths(
            7, arrayOf(
                intArrayOf(0, 6, 7),
                intArrayOf(0, 1, 2),
                intArrayOf(1, 2, 3),
                intArrayOf(1, 3, 3),
                intArrayOf(6, 3, 3),
                intArrayOf(3, 5, 1),
                intArrayOf(6, 5, 1),
                intArrayOf(2, 5, 1),
                intArrayOf(0, 4, 5),
                intArrayOf(4, 6, 2)
            )
        )

        Solution().countPaths(
            200, arrayOf(
                intArrayOf(0, 1, 865326231),
                intArrayOf(1, 4, 865326231),
                intArrayOf(0, 2, 865326231),
                intArrayOf(2, 4, 865326231),
                intArrayOf(0, 3, 865326231),
                intArrayOf(3, 4, 865326231),
                intArrayOf(4, 5, 647618270),
                intArrayOf(5, 9, 647618270),
                intArrayOf(4, 6, 647618270),
                intArrayOf(6, 9, 647618270),
                intArrayOf(4, 7, 647618270),
                intArrayOf(7, 9, 647618270),
                intArrayOf(4, 8, 647618270),
                intArrayOf(8, 9, 647618270),
                intArrayOf(9, 10, 153310768),
                intArrayOf(10, 15, 153310768),
                intArrayOf(9, 11, 153310768),
                intArrayOf(11, 15, 153310768),
                intArrayOf(9, 12, 153310768),
                intArrayOf(12, 15, 153310768),
                intArrayOf(9, 13, 153310768),
                intArrayOf(13, 15, 153310768),
                intArrayOf(9, 14, 153310768),
                intArrayOf(14, 15, 153310768),
                intArrayOf(15, 16, 446216658),
                intArrayOf(16, 21, 446216658),
                intArrayOf(15, 17, 446216658),
                intArrayOf(17, 21, 446216658),
                intArrayOf(15, 18, 446216658),
                intArrayOf(18, 21, 446216658),
                intArrayOf(15, 19, 446216658),
                intArrayOf(19, 21, 446216658),
                intArrayOf(15, 20, 446216658),
                intArrayOf(20, 21, 446216658),
                intArrayOf(21, 22, 482432125),
                intArrayOf(22, 27, 482432125),
                intArrayOf(21, 23, 482432125),
                intArrayOf(23, 27, 482432125),
                intArrayOf(21, 24, 482432125),
                intArrayOf(24, 27, 482432125),
                intArrayOf(21, 25, 482432125),
                intArrayOf(25, 27, 482432125),
                intArrayOf(21, 26, 482432125),
                intArrayOf(26, 27, 482432125),
                intArrayOf(27, 28, 546917635),
                intArrayOf(28, 32, 546917635),
                intArrayOf(27, 29, 546917635),
                intArrayOf(29, 32, 546917635),
                intArrayOf(27, 30, 546917635),
                intArrayOf(30, 32, 546917635),
                intArrayOf(27, 31, 546917635),
                intArrayOf(31, 32, 546917635),
                intArrayOf(32, 33, 905837683),
                intArrayOf(33, 37, 905837683),
                intArrayOf(32, 34, 905837683),
                intArrayOf(34, 37, 905837683),
                intArrayOf(32, 35, 905837683),
                intArrayOf(35, 37, 905837683),
                intArrayOf(32, 36, 905837683),
                intArrayOf(36, 37, 905837683),
                intArrayOf(37, 38, 941383964),
                intArrayOf(38, 41, 941383964),
                intArrayOf(37, 39, 941383964),
                intArrayOf(39, 41, 941383964),
                intArrayOf(37, 40, 941383964),
                intArrayOf(40, 41, 941383964),
                intArrayOf(41, 42, 482278242),
                intArrayOf(42, 44, 482278242),
                intArrayOf(41, 43, 482278242),
                intArrayOf(43, 44, 482278242),
                intArrayOf(44, 45, 209029963),
                intArrayOf(45, 49, 209029963),
                intArrayOf(44, 46, 209029963),
                intArrayOf(46, 49, 209029963),
                intArrayOf(44, 47, 209029963),
                intArrayOf(47, 49, 209029963),
                intArrayOf(44, 48, 209029963),
                intArrayOf(48, 49, 209029963),
                intArrayOf(49, 50, 180362920),
                intArrayOf(50, 53, 180362920),
                intArrayOf(49, 51, 180362920),
                intArrayOf(51, 53, 180362920),
                intArrayOf(49, 52, 180362920),
                intArrayOf(52, 53, 180362920),
                intArrayOf(53, 54, 40040617),
                intArrayOf(54, 58, 40040617),
                intArrayOf(53, 55, 40040617),
                intArrayOf(55, 58, 40040617),
                intArrayOf(53, 56, 40040617),
                intArrayOf(56, 58, 40040617),
                intArrayOf(53, 57, 40040617),
                intArrayOf(57, 58, 40040617),
                intArrayOf(58, 59, 429647103),
                intArrayOf(59, 62, 429647103),
                intArrayOf(58, 60, 429647103),
                intArrayOf(60, 62, 429647103),
                intArrayOf(58, 61, 429647103),
                intArrayOf(61, 62, 429647103),
                intArrayOf(62, 63, 863858638),
                intArrayOf(63, 64, 250353988),
                intArrayOf(64, 66, 250353988),
                intArrayOf(63, 65, 250353988),
                intArrayOf(65, 66, 250353988),
                intArrayOf(66, 67, 502785687),
                intArrayOf(67, 68, 565934645),
                intArrayOf(68, 69, 548158326),
                intArrayOf(69, 74, 548158326),
                intArrayOf(68, 70, 548158326),
                intArrayOf(70, 74, 548158326),
                intArrayOf(68, 71, 548158326),
                intArrayOf(71, 74, 548158326),
                intArrayOf(68, 72, 548158326),
                intArrayOf(72, 74, 548158326),
                intArrayOf(68, 73, 548158326),
                intArrayOf(73, 74, 548158326),
                intArrayOf(74, 75, 858926247),
                intArrayOf(75, 79, 858926247),
                intArrayOf(74, 76, 858926247),
                intArrayOf(76, 79, 858926247),
                intArrayOf(74, 77, 858926247),
                intArrayOf(77, 79, 858926247),
                intArrayOf(74, 78, 858926247),
                intArrayOf(78, 79, 858926247),
                intArrayOf(79, 80, 610164528),
                intArrayOf(80, 82, 610164528),
                intArrayOf(79, 81, 610164528),
                intArrayOf(81, 82, 610164528),
                intArrayOf(82, 83, 116910438),
                intArrayOf(83, 84, 153203278),
                intArrayOf(84, 88, 153203278),
                intArrayOf(83, 85, 153203278),
                intArrayOf(85, 88, 153203278),
                intArrayOf(83, 86, 153203278),
                intArrayOf(86, 88, 153203278),
                intArrayOf(83, 87, 153203278),
                intArrayOf(87, 88, 153203278),
                intArrayOf(88, 89, 484666281),
                intArrayOf(89, 93, 484666281),
                intArrayOf(88, 90, 484666281),
                intArrayOf(90, 93, 484666281),
                intArrayOf(88, 91, 484666281),
                intArrayOf(91, 93, 484666281),
                intArrayOf(88, 92, 484666281),
                intArrayOf(92, 93, 484666281),
                intArrayOf(93, 94, 694001013),
                intArrayOf(94, 95, 180373702),
                intArrayOf(95, 96, 392438425),
                intArrayOf(96, 97, 392438425),
                intArrayOf(97, 98, 915720722),
                intArrayOf(98, 99, 915720722),
                intArrayOf(99, 100, 660886218),
                intArrayOf(100, 101, 980566840),
                intArrayOf(101, 103, 980566840),
                intArrayOf(100, 102, 980566840),
                intArrayOf(102, 103, 980566840),
                intArrayOf(103, 104, 509703517),
                intArrayOf(104, 105, 817920401),
                intArrayOf(105, 106, 992341639),
                intArrayOf(106, 107, 992341639),
                intArrayOf(107, 108, 180854348),
                intArrayOf(108, 111, 180854348),
                intArrayOf(107, 109, 180854348),
                intArrayOf(109, 111, 180854348),
                intArrayOf(107, 110, 180854348),
                intArrayOf(110, 111, 180854348),
                intArrayOf(111, 112, 699029750),
                intArrayOf(112, 115, 699029750),
                intArrayOf(111, 113, 699029750),
                intArrayOf(113, 115, 699029750),
                intArrayOf(111, 114, 699029750),
                intArrayOf(114, 115, 699029750),
                intArrayOf(115, 116, 973634138),
                intArrayOf(116, 120, 973634138),
                intArrayOf(115, 117, 973634138),
                intArrayOf(117, 120, 973634138),
                intArrayOf(115, 118, 973634138),
                intArrayOf(118, 120, 973634138),
                intArrayOf(115, 119, 973634138),
                intArrayOf(119, 120, 973634138),
                intArrayOf(120, 121, 785570880),
                intArrayOf(121, 125, 785570880),
                intArrayOf(120, 122, 785570880),
                intArrayOf(122, 125, 785570880),
                intArrayOf(120, 123, 785570880),
                intArrayOf(123, 125, 785570880),
                intArrayOf(120, 124, 785570880),
                intArrayOf(124, 125, 785570880),
                intArrayOf(125, 126, 720521316),
                intArrayOf(126, 130, 720521316),
                intArrayOf(125, 127, 720521316),
                intArrayOf(127, 130, 720521316),
                intArrayOf(125, 128, 720521316),
                intArrayOf(128, 130, 720521316),
                intArrayOf(125, 129, 720521316),
                intArrayOf(129, 130, 720521316),
                intArrayOf(130, 131, 829375409),
                intArrayOf(131, 132, 152165056),
                intArrayOf(132, 133, 152165056),
                intArrayOf(133, 134, 50370340),
                intArrayOf(134, 137, 50370340),
                intArrayOf(133, 135, 50370340),
                intArrayOf(135, 137, 50370340),
                intArrayOf(133, 136, 50370340),
                intArrayOf(136, 137, 50370340),
                intArrayOf(137, 138, 704034877),
                intArrayOf(138, 139, 484468238),
                intArrayOf(139, 143, 484468238),
                intArrayOf(138, 140, 484468238),
                intArrayOf(140, 143, 484468238),
                intArrayOf(138, 141, 484468238),
                intArrayOf(141, 143, 484468238),
                intArrayOf(138, 142, 484468238),
                intArrayOf(142, 143, 484468238),
                intArrayOf(143, 144, 205433987),
                intArrayOf(144, 145, 205433987),
                intArrayOf(145, 146, 166956493),
                intArrayOf(146, 149, 166956493),
                intArrayOf(145, 147, 166956493),
                intArrayOf(147, 149, 166956493),
                intArrayOf(145, 148, 166956493),
                intArrayOf(148, 149, 166956493),
                intArrayOf(149, 150, 476307189),
                intArrayOf(150, 151, 388217973),
                intArrayOf(151, 152, 388217973),
                intArrayOf(152, 153, 211490211),
                intArrayOf(153, 155, 211490211),
                intArrayOf(152, 154, 211490211),
                intArrayOf(154, 155, 211490211),
                intArrayOf(155, 156, 186981143),
                intArrayOf(156, 160, 186981143),
                intArrayOf(155, 157, 186981143),
                intArrayOf(157, 160, 186981143),
                intArrayOf(155, 158, 186981143),
                intArrayOf(158, 160, 186981143),
                intArrayOf(155, 159, 186981143),
                intArrayOf(159, 160, 186981143),
                intArrayOf(160, 161, 305206923),
                intArrayOf(161, 166, 305206923),
                intArrayOf(160, 162, 305206923),
                intArrayOf(162, 166, 305206923),
                intArrayOf(160, 163, 305206923),
                intArrayOf(163, 166, 305206923),
                intArrayOf(160, 164, 305206923),
                intArrayOf(164, 166, 305206923),
                intArrayOf(160, 165, 305206923),
                intArrayOf(165, 166, 305206923),
                intArrayOf(166, 167, 482432170),
                intArrayOf(167, 171, 482432170),
                intArrayOf(166, 168, 482432170),
                intArrayOf(168, 171, 482432170),
                intArrayOf(166, 169, 482432170),
                intArrayOf(169, 171, 482432170),
                intArrayOf(166, 170, 482432170),
                intArrayOf(170, 171, 482432170),
                intArrayOf(171, 172, 455923183),
                intArrayOf(172, 177, 455923183),
                intArrayOf(171, 173, 455923183),
                intArrayOf(173, 177, 455923183),
                intArrayOf(171, 174, 455923183),
                intArrayOf(174, 177, 455923183),
                intArrayOf(171, 175, 455923183),
                intArrayOf(175, 177, 455923183),
                intArrayOf(171, 176, 455923183),
                intArrayOf(176, 177, 455923183),
                intArrayOf(177, 178, 266584262),
                intArrayOf(178, 179, 266584262),
                intArrayOf(179, 180, 751579148),
                intArrayOf(180, 185, 751579148),
                intArrayOf(179, 181, 751579148),
                intArrayOf(181, 185, 751579148),
                intArrayOf(179, 182, 751579148),
                intArrayOf(182, 185, 751579148),
                intArrayOf(179, 183, 751579148),
                intArrayOf(183, 185, 751579148),
                intArrayOf(179, 184, 751579148),
                intArrayOf(184, 185, 751579148),
                intArrayOf(185, 186, 389984057),
                intArrayOf(186, 188, 389984057),
                intArrayOf(185, 187, 389984057),
                intArrayOf(187, 188, 389984057),
                intArrayOf(188, 189, 926312609),
                intArrayOf(189, 192, 926312609),
                intArrayOf(188, 190, 926312609),
                intArrayOf(190, 192, 926312609),
                intArrayOf(188, 191, 926312609),
                intArrayOf(191, 192, 926312609),
                intArrayOf(192, 193, 323360653),
                intArrayOf(193, 196, 323360653),
                intArrayOf(192, 194, 323360653),
                intArrayOf(194, 196, 323360653),
                intArrayOf(192, 195, 323360653),
                intArrayOf(195, 196, 323360653),
                intArrayOf(196, 197, 977934872),
                intArrayOf(197, 198, 977934872),
                intArrayOf(198, 199, 434009290)
            )
        )
    }
}