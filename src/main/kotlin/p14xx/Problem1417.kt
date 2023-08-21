package p14xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun reformat(s: String): String {
            val chars = LinkedList<Char>()
            val nums = LinkedList<Char>()

            s.forEach {
                if (it in '0'..'9') {
                    nums.add(it)
                } else {
                    chars.add(it)
                }
            }

            return if (chars.size in nums.size - 1..nums.size + 1) {
                val result = StringBuilder()

                if (chars.size > nums.size) {
                    result.append(chars.poll())
                    repeat(nums.size) {
                        result.append(nums.poll())
                        result.append(chars.poll())
                    }
                } else {
                    repeat(chars.size) {
                        result.append(nums.poll())
                        result.append(chars.poll())
                    }

                    nums.poll()?.also { result.append(it) }
                }

                result.toString()
            } else {
                ""
            }
        }
    }

    expect {
        Solution().reformat(
            "a0b1c2",
        )
    }
}

