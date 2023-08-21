package p06xx

import util.expect

fun main() {
    class Solution {
        fun validPalindrome(s: String): Boolean {
            fun check(startIndex: Int, endIndex: Int, ignoredError: Boolean): Boolean {
                var l = startIndex
                var r = endIndex

                while (l < r) {
                    when {
                        s[l] == s[r] -> {
                            l++
                            r--
                        }

                        ignoredError -> {
                            return false
                        }

                        else -> {
                            return check(l + 1, r, true) || check(l, r - 1, true)
                        }
                    }
                }

                return true
            }

            return check(0, s.lastIndex, false)
        }
    }

    expect {
        Solution().validPalindrome("")
    }
}