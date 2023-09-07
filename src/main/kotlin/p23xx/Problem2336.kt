package p23xx

import util.expect
import java.util.*

fun main() {
    class SmallestInfiniteSet {
        var next = 1

        val set = TreeSet<Int>()

        fun popSmallest(): Int {
            return set.pollFirst() ?: next++
        }

        fun addBack(num: Int) {
            if (num < next) {
                set.add(num)
            }
        }
    }

    expect {
        SmallestInfiniteSet().popSmallest(

        )
    }
}