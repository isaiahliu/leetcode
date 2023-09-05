package p23xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {
            val map = TreeMap<Long, Int>()
            potions.sortDescending()
            potions.forEachIndexed { index, p ->
                map[p.toLong()] = index + 1
            }

            return spells.map {
                var potion = success / it
                if (potion * it < success) {
                    potion++
                }
                map.ceilingEntry(potion)?.value ?: 0
            }.toIntArray()
        }
    }

    expect {
        Solution().successfulPairs(
            intArrayOf(46886), intArrayOf(), 0L
        )
    }
}