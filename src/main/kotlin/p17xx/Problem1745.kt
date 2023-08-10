package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkPartitioning(s: String): Boolean {
            val str = s.toCharArray().joinToString("#")

            val r = IntArray(str.length) { 1 }

            var rightBorder = 0
            var center = 0

            for (i in 1 until r.size) {
                var size = 1
                if (i < rightBorder) {
                    size = r[center * 2 - i].coerceAtMost(rightBorder - i + 1)
                }

                if (i + size - 1 >= rightBorder) {
                    center = i
                    while (true) {
                        if ((str.getOrNull(i - size) ?: break) == (str.getOrNull(i + size) ?: break)) {
                            size++
                            rightBorder = i + size - 1
                        } else {
                            break
                        }
                    }
                }

                r[i] = size
            }

            val maxSuffixIndex = IntArray(r.size) { Int.MAX_VALUE }
            maxSuffixIndex[maxSuffixIndex.lastIndex] = maxSuffixIndex.lastIndex

            for (i in r.lastIndex downTo 0) {
                if (r[i] == r.size - i) {
                    (i * 2 - r.size).takeIf { it >= 0 }?.also {
                        maxSuffixIndex[it] = i
                    }
                }

                maxSuffixIndex.getOrNull(i + 1)?.also {
                    maxSuffixIndex[i] = maxSuffixIndex[i].coerceAtMost(it)
                }
            }

            r.forEachIndexed { index, i ->
                if (i == index + 1) {
                    val left = index + i - 1

                    maxSuffixIndex.getOrNull(left + 3 - (index % 2))?.also { suffix ->
                        val right = suffix - r[suffix] + 1

                        val mid = (left + right) / 2

                        if (r[mid] >= mid - left) {
                            return true
                        }
                    }
                }
            }

            return false
        }

        fun checkPartitioning2(s: String): Boolean {
            val leftMatches = hashSetOf(0)
            val rightMatches = hashSetOf(s.lastIndex)
            val midMatches = hashSetOf<Pair<Int, Int>>()
            if (s[0] == s[1]) {
                leftMatches.add(1)
            }

            for (midIndex in 1 until s.lastIndex) {
                midMatches.add(midIndex to midIndex)

                arrayOf(midIndex to midIndex + 1, midIndex - 1 to midIndex + 1).forEach {
                    var left = it.first
                    var right = it.second

                    while (left in s.indices && right in s.indices && s[left] == s[right]) {
                        when {
                            left == 0 -> {
                                leftMatches.add(right)
                            }

                            right == s.lastIndex -> {
                                rightMatches.add(left)
                            }

                            else -> {
                                midMatches.add(left to right)
                            }
                        }

                        left--
                        right++
                    }
                }
            }

            return midMatches.any { (left, right) -> left - 1 in leftMatches && right + 1 in rightMatches }
        }
    }

    measureTimeMillis {
        Solution().checkPartitioning(
            "aabc"
        ).also { println("${it} should be true") }

        Solution().checkPartitioning(
            "acab"
        ).also { println("${it} should be false") }

        Solution().checkPartitioning(
            "abcbdd"
        ).also { println("${it} should be true") }

//        Solution().checkPartitioning(
//            "abacdcee"
//        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
