package p19xx

import util.expect
import util.input

fun main() {
    class Solution {
        fun gcdSort(nums: IntArray): Boolean {
            class Group {
                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
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

            val primeGroups = hashMapOf<Int, Group>()

            val groups = Array(nums.size) { Group() }
            nums.forEachIndexed { index, num ->
                val group = groups[index]

                var t = num
                var prime = 2
                while (prime * prime <= t) {
                    while (t % prime == 0) {
                        t /= prime
                        primeGroups.computeIfAbsent(prime) { Group() }.join(group)
                    }

                    prime++
                }

                if (t > 1) {
                    primeGroups.computeIfAbsent(t) { Group() }.join(group)
                }
            }

            val sorted = IntArray(nums.size) { nums[it] }

            groups.indices.groupBy { groups[it].parent }.values.forEach { g ->
                if (g.size > 1) {
                    val writeIndices = g.sorted()

                    var i = 0
                    g.sortedBy { nums[it] }.forEach {
                        sorted[writeIndices[i++]] = nums[it]
                    }
                }
            }

            for (i in 1 until sorted.size) {
                if (sorted[i] < sorted[i - 1]) {
                    return false
                }
            }
            return true
        }
    }

    expect {
        Solution().gcdSort(
            intArrayOf(8, 9, 4, 2, 3)
        )
    }

    expect {
        Solution().gcdSort(
            input.first().split(",").map { it.toInt() }.toIntArray()
        )
    }
}