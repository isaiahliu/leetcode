import java.util.*

fun main() {
    class Solution {
        fun findSubstring(s: String, words: Array<String>): List<Int> {
            val wordsLength = words.map { it.length }.sum()

            val result = arrayListOf<Int>()

            val wordCounts = words.toList().groupingBy { it }.eachCount()
            val entries = wordCounts.entries.toTypedArray()

            for (startIndex in 0..s.length - wordsLength) {
                var matchedLength = 0
                val used = IntArray(entries.size)

                var usedEntryIndex = -1

                val stack = LinkedList<Int>()
                found@ while (true) {
                    if (stack.size == words.size) {
                        result += startIndex
                        break@found
                    }

                    val matched = entries.withIndex().drop(usedEntryIndex + 1).firstOrNull { (index, entry) ->
                        used[index] < entry.value && s.startsWith(entry.key, startIndex + matchedLength)
                    }

                    if (matched == null) {
                        if (stack.isEmpty()) {
                            break@found
                        } else {
                            val previousIndex = stack.pop()

                            matchedLength -= entries[previousIndex].key.length
                            used[previousIndex]--
                            usedEntryIndex = previousIndex
                        }
                    } else {
                        val (index, entry) = matched

                        matchedLength += entry.key.length
                        used[index]++
                        usedEntryIndex = -1
                        stack.push(index)
                    }
                }
            }
            return result.toList()
        }
    }

    println(Solution().findSubstring("barfoothefoobarman", arrayOf("foo", "bar")))
}

