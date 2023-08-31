package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumWhiteTiles(floor: String, numCarpets: Int, carpetLen: Int): Int {
            val dp = TreeMap<Int, IntArray>()

            dp[-carpetLen - 1] = IntArray(numCarpets + 1) { floor.length }.also { it[0] = 0 }

            floor.forEachIndexed { index, c ->
                if (c == '1') {
                    val notUsedLastDp = dp.lastEntry().value
                    val newDp = IntArray(numCarpets + 1) { notUsedLastDp[it] + 1 }

                    val usedLastDp = dp.floorEntry(index - carpetLen).value
                    for (i in 1 until newDp.size) {
                        newDp[i] = newDp[i].coerceAtMost(usedLastDp[i - 1])
                    }

                    dp[index] = newDp
                }
            }

            return dp.lastEntry()?.value?.min() ?: 0
        }
    }

    expect {
        Solution().minimumWhiteTiles(
            "10110101", 2, 2
        )
    }
}