package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun chalkReplacer(chalk: IntArray, k: Int): Int {
            var sum = 0L
            val treeMap = TreeMap<Long, Int>()

            chalk.forEachIndexed { index, num ->
                sum += num
                treeMap[sum] = index
            }

            return treeMap.higherEntry(k.toLong() % sum).value
        }
    }

    expect {
        Solution().chalkReplacer(
            intArrayOf(), 1
        )
    }
}
