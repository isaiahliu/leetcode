package p25xx

import util.expect
import java.util.*

fun main() {
    class Allocator(val n: Int) {
        val segs = TreeSet<Pair<Int, Int>>(compareBy { it.first })

        val ids = hashMapOf<Int, MutableSet<Pair<Int, Int>>>()

        fun assign(start: Int, size: Int, mID: Int) {
            segs.add(start to start + size - 1)

            ids.computeIfAbsent(mID) { hashSetOf() }.add(start to start + size - 1)
        }

        fun allocate(size: Int, mID: Int): Int {
            if (size > n) {
                return -1
            }

            if (segs.isEmpty()) {
                assign(0, size, mID)
                return 0
            }

            var pre = segs.first()
            if (pre.first >= size) {
                assign(0, size, mID)
                return 0
            }

            segs.forEach {
                if (it.first - pre.second - 1 >= size) {
                    assign(pre.second + 1, size, mID)
                    return pre.second + 1
                }

                pre = it
            }

            if (n - pre.second - 1 >= size) {
                assign(pre.second + 1, size, mID)
                return pre.second + 1
            }

            return -1
        }

        fun free(mID: Int): Int {
            return ids.remove(mID)?.let {
                it.sumOf {
                    segs.remove(it)
                    it.second - it.first + 1
                }
            } ?: 0
        }
    }

    /**
     * Your Allocator object will be instantiated and called as such:
     * var obj = Allocator(n)
     * var param_1 = obj.allocate(size,mID)
     * var param_2 = obj.free(mID)
     */

    val allocator = Allocator(7)
    expect {
        allocator.allocate(1, 2)
    }
}