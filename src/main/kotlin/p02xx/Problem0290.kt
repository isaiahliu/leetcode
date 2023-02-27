package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun wordPattern(pattern: String, s: String): Boolean {
            val words = s.split(" ")

            if (words.size != pattern.length) {
                return false
            }

            val patternMap = hashMapOf<Char, String>()
            val wordMap = hashMapOf<String, Char>()
            pattern.forEachIndexed { index, c ->
                val word = words[index]

                val allEmpty = c !in patternMap && word !in wordMap
                val allMatch = patternMap[c] == word && wordMap[word] == c

                if (allEmpty) {
                    patternMap[c] = word
                    wordMap[word] = c
                } else if (!allMatch) {
                    return false
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().wordPattern("abba", "dog cat cat fish").also { println(it) }
    }
}

