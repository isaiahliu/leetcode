fun main() {
    class Solution {
        fun longestPalindrome(s: String): String {
            var centerIndex = 0
            var singleCenter = true

            var maxLength = 1

            for (index in 0 until s.length - 1) {
                var spreadLength = 0
                while (true) {
                    val left = s.getOrNull(index - spreadLength - 1) ?: break
                    val right = s.getOrNull(index + spreadLength + 1) ?: break

                    if (left == right) {
                        spreadLength++
                    } else {
                        break
                    }
                }

                (spreadLength * 2 + 1).takeIf { it > maxLength }?.also {
                    maxLength = it
                    centerIndex = index
                    singleCenter = true
                }

                spreadLength = 0
                while (true) {
                    val left = s.getOrNull(index - spreadLength) ?: break
                    val right = s.getOrNull(index + spreadLength + 1) ?: break

                    if (left == right) {
                        spreadLength++
                    } else {
                        break
                    }
                }

                (spreadLength * 2).takeIf { it > maxLength }?.also {
                    maxLength = it
                    centerIndex = index
                    singleCenter = false
                }
            }

            val startIndex = if (singleCenter) {
                centerIndex - maxLength / 2
            } else {
                centerIndex - maxLength / 2 + 1
            }

            return s.substring(startIndex, startIndex + maxLength)
        }
    }

    println(Solution().longestPalindrome("cbbd"))
}

