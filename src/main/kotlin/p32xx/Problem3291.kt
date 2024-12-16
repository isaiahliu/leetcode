package p32xx

import util.expect

fun main() {
    class Solution {
        fun minValidStrings(words: Array<String>, target: String): Int {
            class Trie(val depth: Int) {
                val children = arrayOfNulls<Trie>(26)
            }

            val root = Trie(0)
            words.forEach {
                var current = root
                it.forEach { ch ->
                    current = current.children[ch - 'a'] ?: Trie(current.depth + 1).also {
                        current.children[ch - 'a'] = it
                    }
                }
            }

            var dp = hashMapOf(root to 1)

            target.forEach { ch ->
                val newDp = hashMapOf<Trie, Int>()
                var min = Int.MAX_VALUE
                dp.forEach { (node, count) ->
                    node.children[ch - 'a']?.also {
                        newDp[it] = count
                        min = minOf(min, count)
                    }
                }

                if (min < Int.MAX_VALUE) {
                    newDp[root] = min + 1
                }

                dp = newDp
            }

            return dp.values.minOrNull() ?: -1
        }
    }

    expect {
        Solution().minValidStrings(
            arrayOf(
                "abc", "aaaaa", "bcdef"
            ), "aabcdabc"
        )
    }
}
