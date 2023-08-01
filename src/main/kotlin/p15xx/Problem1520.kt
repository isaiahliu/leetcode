package p15xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxNumOfSubstrings(s: String): List<String> {
            class Group(var range: IntRange) {
                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.range =
                            value.range.first.coerceAtMost(range.first)..value.range.last.coerceAtLeast(range.last)
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

            val map = hashMapOf<Char, IntArray>()

            s.forEachIndexed { index, c ->
                map.computeIfAbsent(c) { intArrayOf(index, index) }[1] = index
            }

            val groups = map.map { it.key to Group(it.value[0]..it.value[1]) }.toMap()

            s.forEachIndexed { index, c ->
                map[c]?.also { cRange ->
                    map.forEach { (targetC, range) ->
                        if (index >= range[0] && index <= range[1] && (cRange[0] < range[0] || cRange[1] > range[1])) {
                            groups[c]?.join(groups[targetC] ?: return@forEach)
                        }
                    }
                }
            }

            val result = LinkedList<IntRange>()
            groups.values.map { it.parent }.distinct().sortedBy { it.range.first }.forEach {
                while (result.isNotEmpty()) {
                    if (it.range.first in result.peekLast()) {
                        result.pollLast()
                    } else {
                        break
                    }
                }
                result.add(it.range)
            }

            return result.map { s.substring(it) }
        }
    }

    measureTimeMillis {
        Solution().maxNumOfSubstrings(
            "ababa"
        ).also { println(it) }
    }
}

