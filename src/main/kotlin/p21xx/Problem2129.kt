package p21xx

import util.expect

fun main() {
    class Solution {
        fun capitalizeTitle(title: String): String {
            return title.lowercase().replace("((?<=\\s|^)[a-z](?=[a-z]{2}))".toRegex()) {
                it.value.uppercase()
            }
        }
    }

    expect {
        Solution().capitalizeTitle(
            "AAA BB CCC DDDD"
        )
    }
}