package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reverseVowels(s: String): String {
            val charArray = s.toCharArray()

            val vowels = setOf('A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u')

            var leftIndex = 0
            var rightIndex = charArray.size - 1

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

    measureTimeMillis {
        Solution().reverseVowels(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

