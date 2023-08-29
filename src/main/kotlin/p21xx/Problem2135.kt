package p21xx

import util.expect

fun main() {
    class Solution {
        fun wordCount(startWords: Array<String>, targetWords: Array<String>): Int {
            fun String.counts(): IntArray {
                val result = IntArray(26)

                forEach { result[it - 'a']++ }

                return result
            }

            class DicNode {
                val children = hashMapOf<Int, DicNode>()

                fun add(counts: IntArray, index: Int) {
                    if (index == counts.size) {
                        return
                    }

                    children.computeIfAbsent(counts[index]) { DicNode() }.add(counts, index + 1)
                }

                fun query(counts: IntArray, index: Int, added: Boolean): Boolean {
                    if (index == counts.size) {
                        return added
                    }

                    val count = counts[index]

                    return children[count]?.query(counts, index + 1, added)?.takeIf { it } ?: run {
                        count.takeIf { it == 1 && !added }?.let {
                            children[0]?.query(counts, index + 1, true)
                        }
                    } ?: false
                }
            }

            val root = DicNode()

            startWords.forEach {
                root.add(it.counts(), 0)
            }

            return targetWords.count {
                root.query(it.counts(), 0, false)
            }
        }
    }

    expect {
        Solution().wordCount(
            arrayOf("ant", "act", "tack"), arrayOf("tack", "act", "acti")
        )
    }
}