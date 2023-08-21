package p09xx

import util.expect

fun main() {
    class Solution {
        fun equationsPossible(equations: Array<String>): Boolean {
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

            val groups = Array(26) { Group() }

            equations.forEach {
                if (it[1] == '=') {
                    groups[it[0] - 'a'].join(groups[it[3] - 'a'])
                }
            }

            equations.forEach {
                if (it[1] == '!') {
                    if (groups[it[0] - 'a'].parent == groups[it[3] - 'a']) {
                        return false
                    }
                }
            }

            return true
        }
    }

    expect {
        Solution().equationsPossible(
            arrayOf()
        )
    }
}
