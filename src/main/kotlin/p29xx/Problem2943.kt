package p29xx

import util.expect

fun main() {
    class Solution {
        fun maximizeSquareHoleArea(n: Int, m: Int, hBars: IntArray, vBars: IntArray): Int {
            class Group {
                var size = 1

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.size += size
                    }
                    get() {
                        return innerParent?.parent?.also {
                            innerParent = it
                        } ?: this
                    }

                fun join(target: Group) {
                    val leftParent = parent
                    val rightParent = target.parent

                    if (leftParent != rightParent) {
                        leftParent.parent = rightParent
                    }
                }
            }

            fun findMax(bars: IntArray): Int {
                var result = 0

                val map = hashMapOf<Int, Group>()
                bars.forEach {
                    val group = Group()

                    map[it] = group

                    map[it - 1]?.join(group)
                    map[it + 1]?.join(group)

                    result = maxOf(result, group.parent.size)
                }

                return result + 1
            }

            return minOf(findMax(hBars), findMax(vBars)).let { it * it }
        }
    }

    expect {
        Solution().maximizeSquareHoleArea(
            1, 2, intArrayOf(2, 3), intArrayOf()
        )
    }
}
