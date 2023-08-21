package p09xx

import util.expect

fun main() {
    class Solution {
        fun isLongPressedName(name: String, typed: String): Boolean {
            var l = 0
            var r = 0

            fun moveR(): Boolean {
                var moved = false
                while (r < typed.length && typed[r] == typed.getOrNull(r - 1)) {
                    moved = true
                    r++
                }

                return moved
            }

            while (l < name.length && r < typed.length) {
                if (name[l] == typed[r]) {
                    l++
                    r++
                } else if (!moveR()) {
                    return false
                }
            }

            moveR()

            return l == name.length && r == typed.length
        }
    }

    expect {
        Solution().isLongPressedName(
            "", ""
        )
    }
}