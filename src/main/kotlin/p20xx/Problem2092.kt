package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun findAllPeople(n: Int, meetings: Array<IntArray>, firstPerson: Int): List<Int> {
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

            val times = TreeMap<Int, MutableMap<Int, Group>>()
            val result = hashSetOf(0, firstPerson)

            meetings.forEach { (p1, p2, time) ->
                times.computeIfAbsent(time) { hashMapOf() }.also {
                    it.computeIfAbsent(p1) { Group() }.join(it.computeIfAbsent(p2) { Group() })
                }
            }

            fun match(set1: Set<Int>, set2: Set<Int>): Boolean {
                return if (set1.size < set2.size) {
                    set1.any { it in set2 }
                } else {
                    set2.any { it in set1 }
                }
            }

            while (times.isNotEmpty()) {
                times.pollFirstEntry().value.entries.groupBy({ it.value.parent }, { it.key }).values.forEach {
                    it.toSet().also {
                        if (match(result, it)) {
                            result.addAll(it)
                        }
                    }
                }
            }

            return result.toList()
        }
    }

    expect {
        Solution().findAllPeople(
            1, arrayOf(), 1
        )
    }
}