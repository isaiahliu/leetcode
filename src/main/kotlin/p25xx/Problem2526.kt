package p25xx

import util.expect
import java.util.*

fun main() {
    class DataStream(private val value: Int, private val k: Int) {
        val list = LinkedList<Int>()

        var sameCount = 0

        fun consec(num: Int): Boolean {
            if (num == value) {
                sameCount++
            }

            list += num

            if (list.size > k && list.pollFirst() == value) {
                sameCount--
            }

            return sameCount == k
        }
    }

    expect {
        DataStream(1, 2)
    }
}