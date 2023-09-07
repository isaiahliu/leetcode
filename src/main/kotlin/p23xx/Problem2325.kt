package p23xx

import util.expect

fun main() {
    class Solution {
        fun decodeMessage(key: String, message: String): String {
            val map = Array(26) { -1 }
            val visited = hashSetOf<Char>()
            key.forEach {
                if (it in 'a'..'z' && visited.add(it)) {
                    map[it - 'a'] = visited.size - 1
                }
            }

            return message.map {
                if (it in 'a'..'z') {
                    'a' + map[it - 'a']
                } else {
                    it
                }
            }.joinToString("")
        }
    }

    expect {
        Solution().decodeMessage(
            "the quick brown fox jumps over the lazy dog", "vkbs bs t suepuv"
        )
    }
}