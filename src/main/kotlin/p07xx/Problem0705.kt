package p07xx

import util.expect

fun main() {
    class MyHashSet {
        val arr = BooleanArray(1000001)

        fun add(key: Int) {
            arr[key] = true
        }

        fun remove(key: Int) {
            arr[key] = false
        }

        fun contains(key: Int): Boolean {
            return arr[key]
        }
    }

    expect {
        MyHashSet()
    }
}