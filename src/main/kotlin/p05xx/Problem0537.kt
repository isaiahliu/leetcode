package p05xx

import util.expect

fun main() {
    class Solution {
        fun complexNumberMultiply(num1: String, num2: String): String {
            class Plural(val realNum: Int, val unrealNum: Int) {
                operator fun times(target: Plural): Plural {
                    return Plural(
                        this.realNum * target.realNum - this.unrealNum * target.unrealNum,
                        this.realNum * target.unrealNum + target.realNum * this.unrealNum
                    )
                }

                override fun toString(): String {
                    return "${realNum}+${unrealNum}i"
                }
            }

            val regex = "(-?\\d+)\\+?(-?\\d+)i".toRegex()

            fun String.toPlural(): Plural {
                return regex.matchEntire(this)!!.groupValues.drop(1).let { (r, u) -> Plural(r.toInt(), u.toInt()) }
            }

            return (num1.toPlural() * num2.toPlural()).toString()
        }
    }

    expect {
        Solution().complexNumberMultiply("1+1i", "1+1i")
    }
}