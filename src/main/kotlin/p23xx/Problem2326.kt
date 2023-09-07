package p23xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun spiralMatrix(m: Int, n: Int, head: ListNode?): Array<IntArray> {
            var minR = 0
            var maxR = m - 1

            var minC = 0
            var maxC = n - 1

            val result = Array(m) { IntArray(n) { -1 } }

            var moveRow = false
            var delta = 1

            var row = 0
            var column = 0

            var t = head
            while (t != null) {
                result[row][column] = t.`val`

                if (moveRow) {
                    if (row + delta !in minR..maxR) {
                        moveRow = !moveRow

                        if (delta > 0) {
                            maxC--
                        } else {
                            minC++
                        }

                        delta = -delta
                    }
                } else {
                    if (column + delta !in minC..maxC) {
                        moveRow = !moveRow

                        if (delta > 0) {
                            minR++
                        } else {
                            maxR--
                        }
                    }
                }

                if (moveRow) {
                    row += delta
                } else {
                    column += delta
                }

                t = t.next
            }

            return result
        }
    }

    expect {
        Solution().spiralMatrix(
            1, 2, null
        )
    }
}