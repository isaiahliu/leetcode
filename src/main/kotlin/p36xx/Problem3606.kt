package p36xx

import util.expect

fun main() {
    class Solution {
        fun validateCoupons(code: Array<String>, businessLine: Array<String>, isActive: BooleanArray): List<String> {
            val validChars = buildSet {
                addAll('A'..'Z')
                addAll('a'..'z')
                addAll('0'..'9')
                add('_')
            }

            val validBusinessLines = mapOf("electronics" to 0, "grocery" to 1, "pharmacy" to 2, "restaurant" to 3)

            return code.indices.filter {
                code[it].isNotEmpty() && code[it].all { it in validChars } && businessLine[it] in validBusinessLines && isActive[it]
            }.sortedWith(compareBy<Int> { validBusinessLines[businessLine[it]] ?: 0 }.thenBy { code[it] }).map { code[it] }
        }
    }

    expect {
        Solution().validateCoupons(
            arrayOf(), arrayOf(), booleanArrayOf()
        )
    }
}
