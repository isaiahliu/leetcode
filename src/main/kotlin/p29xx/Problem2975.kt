package p29xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun maximizeSquareArea(m: Int, n: Int, hFences: IntArray, vFences: IntArray): Int {
            fun find(fences: List<Int>): Set<Int> {
                return buildSet {
                    for (i in fences.indices) {
                        for (j in i + 1 until fences.size) {
                            add((fences[j] - fences[i]).absoluteValue)
                        }
                    }
                }
            }

            val h = find(hFences.toList() + 1 + m)
            val v = find(vFences.toList() + 1 + n)

            return h.intersect(v).maxOrNull()?.toLong()?.let {
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
