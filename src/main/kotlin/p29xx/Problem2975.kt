package p29xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun maximizeSquareArea(m: Int, n: Int, hFences: IntArray, vFences: IntArray): Int {
            fun find(size: Int, fences: IntArray): Set<Int> {
                return buildSet {
                    add(size - 1)

                    for (i in fences.indices) {
                        val num = fences[i]

                        add(num - 1)
                        add(size - num)

                        for (j in i + 1 until fences.size) {
                            add((fences[j] - num).absoluteValue)
                        }
                    }
                }
            }

            return find(m, hFences).intersect(find(n, vFences)).maxOrNull()?.toLong()?.let {
                it * it % 1000000007
            }?.toInt() ?: -1
        }
    }

    expect {
        Solution().maximizeSquareArea(
            1, 2, intArrayOf(), intArrayOf()
        )
    }
}
