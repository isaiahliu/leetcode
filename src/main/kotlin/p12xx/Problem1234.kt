package p12xx

fun main() {
    class Solution {
        fun balancedString(s: String): Int {
            fun Char.index(): Int {
                return when (this) {
                    'Q' -> 0
                    'W' -> 1
                    'E' -> 2
                    else -> 3
                }
            }

            val counts = IntArray(4)

            val countsPerChar = s.length / 4

            s.forEach { c ->
                counts[c.index()]++
            }

            repeat(4) {
                if (counts[it] > countsPerChar) {
                    counts[it] = counts[it] - countsPerChar
                } else {
                    counts[it] = Int.MIN_VALUE
                }
            }

            if (counts.all { it == Int.MIN_VALUE }) {
                return 0
            }

            if (counts.filter { it != Int.MIN_VALUE }.sum() == 1) {
                return 1
            }

            var leftIndex = 0
            while (counts[s[leftIndex].index()] < 0) {
                leftIndex++
            }

            var rightIndex = leftIndex

            counts[s[leftIndex].index()]--

            var result = Int.MAX_VALUE
            while (rightIndex < s.length) {
                if (counts.all { it <= 0 }) {
                    var found = false
                    found@ while (true) {
                        if (!found) {
                            result = (rightIndex - leftIndex + 1).coerceAtMost(result)
                        }

                        if (counts[s[leftIndex].index()] >= 0) {
                            if (found) {
                                break@found
                            } else {
                                found = true
                            }
                        }

                        counts[s[leftIndex].index()]++

                        leftIndex++
                    }

                } else {
                    rightIndex++
                    found@ while (rightIndex < s.length) {
                        when (counts[s[rightIndex].index()]) {
                            Int.MIN_VALUE -> {
                                rightIndex++
                            }

                            1 -> {
                                counts[s[rightIndex].index()]--
                                break@found
                            }

                            else -> {
                                counts[s[rightIndex].index()]--
                                rightIndex++
                            }
                        }

                    }
                }
            }
            return result
        }
    }

    println(Solution().balancedString("QQWE"))
}

