package p04xx

import util.expect

fun main() {
    class Solution {
        fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
            val result = hashSetOf<String>()

            words.sortBy { it.length }

            val wordSet = words.toSet()
            val visited = hashSetOf<String>()
            val match = hashSetOf<String>()

            fun walk(word: String, route: Set<String>) {
                if (!visited.add(word) && route.isNotEmpty()) {
                    if (word in match) {
                        route.forEach {
                            match.add(it)

                            if (it in wordSet) {
                                result.add(it)
                            }
                        }
                    }

                    return
                }

                for (w in words) {
                    if (w.length > word.length) {
                        break
                    } else if (w == word) {
                        if (route.isNotEmpty()) {
                            match.add(w)
                            route.forEach {
                                match.add(it)

                                if (it in wordSet) {
                                    result.add(it)
                                }
                            }
                        }
                    } else if (word.startsWith(w)) {
                        walk(word.substring(w.length), route + word)
                    }
                }
            }

            for (i in words.lastIndex downTo 0) {
                walk(words[i], emptySet())
            }

            return result.toList()
        }
    }

    expect {
        Solution().findAllConcatenatedWordsInADict(
            arrayOf("cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat")
        )
    }
}