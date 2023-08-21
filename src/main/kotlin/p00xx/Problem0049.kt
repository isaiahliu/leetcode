package p00xx

import util.expect

fun main() {
    class Solution {
        fun groupAnagrams(strs: Array<String>): List<List<String>> {
            return strs.groupBy { String(it.toCharArray().apply { sort() }) }.values.toList()
        }
    }

    expect {
        Solution().groupAnagrams(arrayOf(""))
    }
}


