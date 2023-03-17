package p00xx

fun main() {
    class Solution {
        inner class Node(val char: Char) {
            var multiMatch: Boolean = false

            fun match(target: Char): Boolean {
                return if (char == '.') {
                    true
                } else {
                    char == target
                }
            }
        }

        fun isMatch(s: String, p: String): Boolean {
            val matchNodes = arrayListOf<Node>()
            p.forEach {
                if (it == '*') {
                    matchNodes.last().multiMatch = true
                } else {
                    matchNodes += Node(it)
                }
            }

            found@ while (true) {
                loop@ for (i in 0 until matchNodes.lastIndex) {
                    val left = matchNodes[i]
                    val right = matchNodes[i + 1]
                    if (left.multiMatch && right.multiMatch) {
                        when {
                            left.char == '.' -> {
                                matchNodes.removeAt(i + 1)
                            }

                            right.char == '.' -> {
                                matchNodes.removeAt(i)
                            }

                            left.char == right.char -> {
                                matchNodes.removeAt(i + 1)
                            }

                            else -> {
                                continue@loop
                            }
                        }
                        continue@found
                    }
                }
                break
            }

            return match(s, 0, matchNodes.toTypedArray(), 0)
        }

        fun match(s: String, sIndex: Int, matchNodes: Array<Node>, matchIndex: Int): Boolean {
            if (sIndex == s.length) {
                return matchNodes.drop(matchIndex).all { it.multiMatch }
            }

            val matchNode = matchNodes.getOrNull(matchIndex) ?: return false

            return if (matchNode.multiMatch) {
                var t = sIndex
                while (t < s.length) {
                    if (!matchNode.match(s[t])) {
                        break
                    }
                    t++
                }

                (sIndex..t).any {
                    match(s, it, matchNodes, matchIndex + 1)
                }
            } else if (matchNode.match(s[sIndex])) {
                match(s, sIndex + 1, matchNodes, matchIndex + 1)
            } else {
                false
            }
        }
    }

    println(Solution().isMatch("ab", ".*c"))
}

