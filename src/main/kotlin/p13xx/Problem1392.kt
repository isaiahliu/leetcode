package p13xx

fun main() {
    class Solution {
        fun longestPrefix(s: String): String {
            if (s.length <= 1) {
                return ""
            }

            val chars = arrayListOf<Char>()
            val counts = arrayListOf<Int>()

            var c = '0'
            var count = 0
            (s + '0').forEach {
                if (c == it) {
                    count++
                } else {
                    if (count > 0) {
                        chars.add(c)
                        counts.add(count)
                    }

                    c = it
                    count = 1
                }
            }

            if (chars.size == 1) {
                return s.substring(1)
            }

            var suffixStart = 1
            val result = StringBuilder()
            found@ while (suffixStart < chars.size) {
                result.clear()

                for (i in 0 until chars.size - suffixStart) {
                    if (chars[i] != chars[suffixStart + i]) {
                        suffixStart++
                        continue@found
                    }

                    var match = counts[i] == counts[suffixStart + i]

                    if (i == 0 && counts[i] <= counts[suffixStart + i]) {
                        match = true
                    }

                    if (suffixStart + i == chars.size - 1 && counts[i] >= counts[suffixStart + i]) {
                        match = true
                    }

                    if (match) {
                        result.append(chars[i].toString().repeat(counts[i].coerceAtMost(counts[suffixStart + i])))
                    } else {
                        result.clear()
                        suffixStart++
                        continue@found
                    }
                }

                break
            }

            return result.toString()
        }
    }

    println(Solution().longestPrefix("aaaaaaaaaaaaaaaaaaaaaabcaaaaa"))
}

