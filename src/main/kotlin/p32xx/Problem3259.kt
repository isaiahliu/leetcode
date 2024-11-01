package p32xx

import util.expect

fun main() {
    class Solution {
        fun maxEnergyBoost(energyDrinkA: IntArray, energyDrinkB: IntArray): Long {
            return energyDrinkA.indices.fold(LongArray(2)) { (lastA, lastB), index ->
                longArrayOf(
                    maxOf(lastA + energyDrinkA[index], lastB),
                    maxOf(lastB + energyDrinkB[index], lastA)
                )
            }.max()
        }
    }
    expect {
        Solution().maxEnergyBoost(
            intArrayOf(), intArrayOf()
        )
    }
}
