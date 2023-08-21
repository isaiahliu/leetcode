package p03xx

import util.expect

fun main() {
    class Solution {
        fun reverseVowels(s: String): String {
            val charArray = s.toCharArray()

            val vowels = setOf('A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u')

            var leftIndex = 0
            var rightIndex = charArray.lastIndex

            while (leftIndex < rightIndex) {
                when {
                    charArray[leftIndex] !in vowels -> {
                        leftIndex++
                    }

                    charArray[rightIndex] !in vowels -> {
                        rightIndex--
                    }

                    else -> {
                        val t = charArray[leftIndex]
                        charArray[leftIndex] = charArray[rightIndex]
                        charArray[rightIndex] = t

                        leftIndex++
                        rightIndex--
                    }
                }
            }

            return String(charArray)
        }
    }

    expect {
        Solution().reverseVowels(
            ""
        )
    }
}

