package p13xx

import util.expect

fun main() {
    class Solution {
        fun findGoodStrings(n: Int, s1: String, s2: String, evil: String): Int {
            val m = 1000000007L

            var samePrefixIndex = 0

            while (samePrefixIndex < n && s1[samePrefixIndex] == s2[samePrefixIndex]) {
                samePrefixIndex++
            }

            if (evil in s1.take(samePrefixIndex)) {
                return 0
            }

            val lastEvilPos = Array(evil.length) {
                IntArray(26) { -1 }
            }

            lastEvilPos.forEachIndexed { p, chars ->
                repeat(26) next@{
                    // aac p = 2 char = 'a'
                    for (i in p downTo 0) {
                        if (evil.take(i + 1) == evil.substring(p - i until p) + ('a' + it)) {
                            chars[it] = i
                            break
                        }
                    }
                }
            }

            val dp = Array(n + 1) {
                Array(4) {
                    LongArray(evil.length)
                }
            }

            val FREE = 0
            val LOWER = 1
            val HIGHER = 2
            val BOTH = 3

            dp[0][BOTH][evil.lastIndex]++

            val statusMap = hashMapOf(
                FREE to hashSetOf(FREE, LOWER, HIGHER, BOTH),
                LOWER to hashSetOf(LOWER, BOTH),
                HIGHER to hashSetOf(HIGHER, BOTH),
                BOTH to hashSetOf(BOTH)
            )

            for (charIndexP1 in 1..n) {
                val charIndex = charIndexP1 - 1

                val statusDp = dp[charIndexP1]
                val lastDp = dp[charIndex]

                statusDp.forEachIndexed nextStatus@{ status, evilDp ->
                    if (!(charIndex < samePrefixIndex && status == BOTH || charIndex >= samePrefixIndex && status != BOTH)) {
                        return@nextStatus
                    }

                    statusMap[status]?.forEach { lastStatus ->
                        var lower = 0
                        var higher = 25

                        if (lastStatus and LOWER > 0) {
                            lower = s1[charIndex] - 'a'
                        }

                        if (lastStatus and HIGHER > 0) {
                            higher = s2[charIndex] - 'a'
                        }

                        if (status and LOWER > 0) {
                            higher = s1[charIndex] - 'a'
                        }

                        if (status and HIGHER > 0) {
                            lower = s2[charIndex] - 'a'
                        }

                        if (status == FREE) {
                            if (lastStatus and LOWER > 0) {
                                lower++
                            }

                            if (lastStatus and HIGHER > 0) {
                                higher--
                            }
                        }

                        (lower..higher).forEach { c ->
                            lastDp[lastStatus].forEachIndexed { lastEvilIndex, num ->
                                if (num > 0) {
                                    val evilIndex =
                                        (lastEvilPos[(lastEvilIndex + 1) % evil.length][c] + evil.length) % evil.length
                                    if ((lastEvilIndex + 1) % evil.length != evil.lastIndex || c != evil[evil.lastIndex] - 'a' || evilIndex != evil.lastIndex) {
                                        evilDp[evilIndex] += num
                                        evilDp[evilIndex] %= m
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return (dp.last().map { it.sum() % m }.sum() % m).toInt()
        }
    }

    expect {
        Solution().findGoodStrings(
            2,
            "aa",
            "da",
            "b"
        )

        Solution().findGoodStrings(
            4,
            "aaac",
            "aaad",
            "aac"
        )

        Solution().findGoodStrings(
            93,
            "kazxkmmctdgtrplfggliycskmbfzjkrsthahcrxaaylpdykqfyejwteexytvxgjrbviconioomfpznawsseisfcpfkuvx",
            "lajtokckoizywvirjhccouuhjkkshdtzchzmiccujzpeqzcvfekdqjgrbkzrwfnfwhyvzrrrakiausbleeimmthaqqouh",
            "kpvphwnkrtxuiuhb"
        )

        Solution().findGoodStrings(
            8, "pzdanyao", "wgpmtywi", "sdka"
        )

    }
}

