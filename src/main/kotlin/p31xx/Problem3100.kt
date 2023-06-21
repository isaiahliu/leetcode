package p31xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun pondSizes(land: Array<IntArray>): IntArray {
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

            val groups = hashMapOf<Pair<Int, Int>, Group>()

            val neighbors = arrayOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1)
            land.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, num ->
                    if (num == 0) {
                        val group = Group()

                        groups[rowIndex to columnIndex] = group

                        neighbors.forEach { (deltaR, deltaC) ->
                            groups[rowIndex + deltaR to columnIndex + deltaC]?.join(group)
                        }
                    }
                }
            }

            return groups.values.map { it.parent }.distinct().map { it.size }.sorted().toIntArray()
        }
    }

    measureTimeMillis {
        Solution().pondSizes(
            arrayOf(

            )
        ).also { println(it) }
    }
}

