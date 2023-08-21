package p07xx

import util.expect

fun main() {
    class MyHashMap {
        val arr = IntArray(1000001) { -1 }

        fun put(key: Int, value: Int) {
            arr[key] = value
        }

        fun get(key: Int): Int {
            return arr[key]
        }

        fun remove(key: Int) {
            put(key, -1)
        }
    }

    expect {
        MyHashMap()
    }
}