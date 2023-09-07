package p23xx

import util.expect
import java.util.*

fun main() {
    class SmallestInfiniteSet {
        val set = TreeSet<Int>()

        init {
            (1..1000).forEach { set.add(it) }
        }

        fun popSmallest(): Int {
            return set.pollFirst() ?: 0
        }

        fun addBack(num: Int) {
            set.add(num)
        }
    }

    expect {
        SmallestInfiniteSet().popSmallest(

        )
    }
}