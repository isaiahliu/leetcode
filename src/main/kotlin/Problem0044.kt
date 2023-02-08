import java.util.*

fun main() {
    class Solution {
        fun isMatch(s: String, p: String): Boolean {
            var sIndex = 0
            var pIndex = 0

            val washedP = p.replace("\\*+".toRegex(), "*")

            val stack = LinkedList<Int>()

            while (true) {
                if (sIndex == s.length && pIndex == washedP.length) {
                    return true
                }

                var match = pIndex < washedP.length
                if (match) {
                    when {
                        washedP[pIndex] == '*' -> {
                            pIndex++
                            stack.push(0)
                        }

                        sIndex >= s.length -> {
                            match = false
                        }

                        washedP[pIndex] == '?' -> {
                            sIndex++
                            pIndex++
                        }

                        washedP[pIndex] == s[sIndex] -> {
                            sIndex++
                            pIndex++
                        }

                        else -> {
                            match = false
                        }
                    }
                }

                if (!match) {
                    pIndex--

                    while (pIndex >= 0) {
                        if (washedP[pIndex] == '*') {
                            val matchedCount = stack.pop()

                            if (sIndex < s.length) {
                                pIndex++
                                sIndex++
                                stack.push(matchedCount + 1)
                                break
                            } else {
                                pIndex -= matchedCount
                            }
                        } else {
                            sIndex--
                        }

                        pIndex--
                    }

                    if (pIndex < 0) {
                        return false
                    }
                }
            }
        }
    }

    println(Solution().isMatch("abcabczzzde", "*abc???de*"))
}

