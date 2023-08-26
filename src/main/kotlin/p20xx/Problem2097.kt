package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun validArrangement(pairs: Array<IntArray>): Array<IntArray> {
            val edges = hashMapOf<Int, LinkedList<IntArray>>()

            val degrees = hashMapOf<Int, Int>()
            pairs.forEach { pair ->
                edges.computeIfAbsent(pair[0]) { LinkedList() }.add(pair)
                degrees[pair[0]] = (degrees[pair[0]] ?: 0) + 1
                degrees[pair[1]] = (degrees[pair[1]] ?: 0) - 1
            }

            val result = LinkedList<IntArray>()

            var start = edges.keys.first()
            degrees.entries.firstOrNull { it.value == 1 }?.also {
                start = it.key
            }

            fun dfs(pair: IntArray? = null, node: Int = pair?.get(1) ?: start) {
                edges[node]?.also {
                    while (it.isNotEmpty()) {
                        dfs(it.poll())
                    }
                }
                pair?.also { result.push(it) }
            }

            dfs()

            return result.toTypedArray()
        }
    }

    expect {
        Solution().validArrangement(
            arrayOf(
                intArrayOf(5, 1),
                intArrayOf(4, 5),
                intArrayOf(11, 9),
                intArrayOf(9, 4),
            )
        ).map { it.toList() }
    }

    expect {
        Solution().validArrangement(
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(3, 2),
                intArrayOf(2, 1),
            )
        ).map { it.toList() }
    }

    expect {
        Solution().validArrangement(
            arrayOf(
                intArrayOf(2, 1),
                intArrayOf(1, 2),
                intArrayOf(1, 3),
            )
        ).map { it.toList() }
    }

    expect {
        Solution().validArrangement(
            arrayOf(
                intArrayOf(999, 990),
                intArrayOf(356, 511),
                intArrayOf(192, 879),
                intArrayOf(246, 945),
                intArrayOf(322, 602),
                intArrayOf(776, 246),
                intArrayOf(248, 126),
                intArrayOf(503, 284),
                intArrayOf(126, 164),
                intArrayOf(494, 731),
                intArrayOf(862, 382),
                intArrayOf(731, 578),
                intArrayOf(511, 277),
                intArrayOf(122, 731),
                intArrayOf(578, 99),
                intArrayOf(731, 277),
                intArrayOf(847, 538),
                intArrayOf(246, 494),
                intArrayOf(284, 138),
                intArrayOf(382, 899),
                intArrayOf(811, 439),
                intArrayOf(164, 99),
                intArrayOf(485, 307),
                intArrayOf(618, 320),
                intArrayOf(494, 511),
                intArrayOf(413, 248),
                intArrayOf(945, 356),
                intArrayOf(990, 614),
                intArrayOf(138, 18),
                intArrayOf(164, 862),
                intArrayOf(277, 164),
                intArrayOf(826, 737),
                intArrayOf(277, 322),
                intArrayOf(618, 122),
                intArrayOf(291, 639),
                intArrayOf(288, 11),
                intArrayOf(624, 485),
                intArrayOf(740, 452),
                intArrayOf(614, 740),
                intArrayOf(307, 903),
                intArrayOf(457, 412),
                intArrayOf(538, 961),
                intArrayOf(439, 122),
                intArrayOf(961, 999),
                intArrayOf(639, 822),
                intArrayOf(903, 503),
                intArrayOf(961, 776),
                intArrayOf(138, 538),
                intArrayOf(122, 826),
                intArrayOf(99, 138),
                intArrayOf(949, 175),
                intArrayOf(452, 847),
                intArrayOf(320, 624),
                intArrayOf(879, 457),
                intArrayOf(511, 961),
                intArrayOf(835, 692),
                intArrayOf(18, 949),
                intArrayOf(737, 413),
                intArrayOf(822, 999),
                intArrayOf(11, 726),
                intArrayOf(692, 618),
                intArrayOf(899, 835),
                intArrayOf(726, 192),
                intArrayOf(999, 452),
                intArrayOf(602, 811),
                intArrayOf(452, 618),
                intArrayOf(175, 246),
                intArrayOf(99, 291),
                intArrayOf(412, 494)
            )
        ).map { it.toList() }
    }
}