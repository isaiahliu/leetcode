package p20xx

import util.expect

fun main() {
    class Solution {
        fun kMirror(k: Int, n: Int): Long {
            fun String.isMirror(): Boolean {
                var left = 0
                var right = lastIndex
                while (left < right) {
                    if (this[left] != this[right]) {
                        return false
                    }

                    left++
                    right--
                }

                return true
            }

            var remain = n
            var result = 0L

            for (num in 1..9) {
                if (num.toString(k).isMirror()) {
                    remain--
                    result += num
                }

                if (remain == 0) {
                    break
                }
            }

            var firstPartLength = 1
            loop@ while (true) {
                var base = 1
                repeat(firstPartLength - 1) {
                    base *= 10
                }

                for (left in base until base * 10) {
                    val num = left.toString().let {
                        "$it${it.reversed().padStart(firstPartLength, '0')}"
                    }.toLong()

                    if (num.toString(k).isMirror()) {
                        remain--
                        result += num
                    }

                    if (remain == 0) {
                        break@loop
                    }
                }

                for (left in base until base * 10) {
                    for (mid in 0..9) {
                        val num = left.toString().let {
                            "$it$mid${it.reversed().padStart(firstPartLength, '0')}"
                        }.toLong()

                        if (num.toString(k).isMirror()) {
                            remain--
                            result += num
                        }

                        if (remain == 0) {
                            break@loop
                        }
                    }
                }

                firstPartLength++
            }

            return result
        }
    }

    expect {
        Solution().kMirror(
            5, 30
        )
    }
}