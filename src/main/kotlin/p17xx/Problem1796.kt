package p17xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun secondHighest(s: String): Int {
            val treeSet = TreeSet<Int>()

            s.forEach {
                if (it in '0'..'9') {
                    treeSet.add(it - '0')
                }
            }

            treeSet.pollLast()

            return treeSet.pollLast() ?: -1
        }
    }

    expect {
        Solution().secondHighest(
            ""
        )
    }
}
