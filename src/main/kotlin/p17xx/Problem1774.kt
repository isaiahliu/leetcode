package p17xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun closestCost(baseCosts: IntArray, toppingCosts: IntArray, target: Int): Int {
            val set = TreeSet<Int>()
            set.add(0)

            toppingCosts.forEach { topping ->
                set.toSet().forEach {
                    set.add(it + topping)
                    set.add(it + topping * 2)
                }
            }

            var minDelta = Int.MAX_VALUE
            var result = 0
            baseCosts.forEach { base ->
                val remain = target - base

                set.ceiling(remain)?.also {
                    (base + it).also {
                        if (it - target < minDelta) {
                            minDelta = it - target
                            result = it
                        }

                        if (it - target == minDelta) {
                            result = result.coerceAtMost(it)
                        }
                    }
                }

                set.floor(remain)?.also {
                    (base + it).also {
                        if (target - it < minDelta) {
                            minDelta = target - it
                            result = it
                        }

                        if (target - it == minDelta) {
                            result = result.coerceAtMost(it)
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().closestCost(
            intArrayOf(), intArrayOf(), 1
        )
    }
}
