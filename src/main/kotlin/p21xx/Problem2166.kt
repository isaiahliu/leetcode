package p21xx

import util.expect

fun main() {
    class Bitset(val size: Int) {
        val ones = hashSetOf<Int>()
        var reversed = false

        fun fix(idx: Int) {
            if (reversed) {
                ones.remove(idx)
            } else {
                ones.add(idx)
            }
        }

        fun unfix(idx: Int) {
            if (reversed) {
                ones.add(idx)
            } else {
                ones.remove(idx)
            }
        }

        fun flip() {
            reversed = !reversed
        }

        fun all(): Boolean {
            return if (reversed) {
                ones.isEmpty()
            } else {
                ones.size == size
            }
        }

        fun one(): Boolean {
            return if (reversed) {
                ones.size < size
            } else {
                ones.isNotEmpty()
            }
        }

        fun count(): Int {
            return if (reversed) {
                size - ones.size
            } else {
                ones.size
            }
        }

        override fun toString(): String {
            return if (reversed) {
                CharArray(size) {
                    if (it in ones) {
                        '0'
                    } else {
                        '1'
                    }
                }
            } else {
                CharArray(size) {
                    if (it in ones) {
                        '1'
                    } else {
                        '0'
                    }
                }
            }.concatToString()
        }
    }

    expect {
        Bitset(5).fix(
            1
        )
    }
}