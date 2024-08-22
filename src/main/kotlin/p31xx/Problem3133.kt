package p31xx

import util.expect

fun main() {
    class Solution {
        fun minEnd(n: Int, x: Int): Long {
            val bNum1 = x.toString(2).reversed()

            val bNum2 = (n - 1).toString(2).reversed()

            return buildString {
                var index2 = 0

                bNum1.forEach {
                    if (it == '1') {
                        insert(0, it)
                    } else {
                        insert(0, bNum2.getOrElse(index2++) { '0' })
                    }
                }

                while (index2 < bNum2.length) {
                    insert(0, bNum2[index2++])
                }
            }.toLong(2)
        }
    }

    expect {
        Solution().minEnd(
            3, 4
        )
    }
}
