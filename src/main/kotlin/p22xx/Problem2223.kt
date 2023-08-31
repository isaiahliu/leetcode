package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun sumScores(s: String): Long {
            var result = s.length.toLong()

            var startIndex = 1

            val cache = IntArray(s.length)

            while (startIndex < s.length) {
                if (s[startIndex] != s[0]) {
                    startIndex++
                    continue
                }

                val matchIndices = LinkedList<Int>()
                matchIndices.push(startIndex)

                var checkPos = 1

                while (true) {
                    val checkChar = s.getOrNull(startIndex + checkPos)
                    if (checkChar == s[checkPos]) {
                        if (checkChar == s[0]) {
                            cache[startIndex + checkPos] = cache[checkPos % startIndex]
                            matchIndices.push(startIndex + checkPos)
                        }
                        checkPos++
                    } else {
                        break
                    }
                }

                var nextStartIndex = startIndex + checkPos
                matchIndices.forEach { index ->
                    if (startIndex + checkPos - index <= cache[index]) {
                        nextStartIndex = index
                        cache[index] = 0
                    } else {
                        if (cache[index] == 0) {
                            cache[index] = startIndex + checkPos - index
                        }
                        result += cache[index]
                    }
                }

                startIndex = nextStartIndex
            }

            return result
        }
    }

    expect(18) {
        Solution().sumScores(
            "hhzjhhzajsoiz"
        )
    }

    expect(12) {
        Solution().sumScores(
            "aabaaa"
        )
    }


    expect(124) {
        Solution().sumScores(
            "zsrrsnlpyndzzsrrsnlpyndzzsrrsnlpyndzzsrrsnlpyndz"
        )
    }
}