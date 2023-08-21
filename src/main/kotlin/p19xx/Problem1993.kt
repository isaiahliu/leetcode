package p19xx

import util.expect
import java.util.concurrent.ConcurrentSkipListSet

fun main() {
    class LockingTree(val parent: IntArray) {
        val locks = arrayOfNulls<Int>(parent.size)
        val childrenLocks = Array(parent.size) { ConcurrentSkipListSet<Int>() }

        fun lock(num: Int, user: Int): Boolean {
            return if (locks[num] == null) {
                locks[num] = user

                var p = parent[num]
                while (p >= 0) {
                    childrenLocks[p].add(num)
                    p = parent[p]
                }
                true
            } else {
                false
            }
        }

        fun unlock(num: Int, user: Int): Boolean {
            return if (locks[num] == user) {
                locks[num] = null

                var p = parent[num]
                while (p >= 0) {
                    childrenLocks[p].remove(num)
                    p = parent[p]
                }
                true
            } else {
                false
            }
        }

        fun upgrade(num: Int, user: Int): Boolean {
            if (childrenLocks[num].isEmpty()) {
                return false
            }

            var p = num
            while (p >= 0) {
                if (locks[p] != null) {
                    return false
                }
                p = parent[p]
            }

            childrenLocks[num].forEach { unlock(it, locks[it] ?: user) }
            lock(num, user)

            return true
        }
    }

    expect {
        LockingTree(intArrayOf()).lock(
            1, 2
        )
    }
}