package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun fullBloomFlowers(flowers: Array<IntArray>, people: IntArray): IntArray {
            val map = TreeMap<Int, Int>()

            fun inc(p: Int) {
                map[p]?.takeIf {
                    it == -1
                }?.also { map.remove(p) } ?: run {
                    map[p] = (map[p] ?: 0) + 1
                }
            }

            fun dec(p: Int) {
                map[p]?.takeIf {
                    it == 1
                }?.also { map.remove(p) } ?: run {
                    map[p] = (map[p] ?: 0) - 1
                }
            }

            flowers.forEach { (from, to) ->
                inc(from)
                dec(to + 1)
            }

            val result = IntArray(people.size)

            var count = 0
            for (peopleIndex in people.indices.sortedBy { people[it] }) {
                if (map.isEmpty()) {
                    break
                }
                val time = people[peopleIndex]

                while (map.isNotEmpty() && map.firstKey() <= time) {
                    count += map.pollFirstEntry().value
                }

                result[peopleIndex] = count
            }

            return result
        }
    }

    expect {
        Solution().fullBloomFlowers(
            arrayOf(
                intArrayOf(1, 6),
                intArrayOf(3, 7),
                intArrayOf(9, 12),
                intArrayOf(4, 13),
            ), intArrayOf(2, 3, 7, 11)
        )
    }
}