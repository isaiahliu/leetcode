package p11xx

import java.util.*
import util.expect

fun main() {
    class SnapshotArray(length: Int) {
        val map = hashMapOf<Int, TreeMap<Int, Int>>()

        var snap = 0

        fun set(index: Int, `val`: Int) {
            map.computeIfAbsent(index) { TreeMap() }[snap] = `val`
        }

        fun snap(): Int {
            return snap++
        }

        fun get(index: Int, snap_id: Int): Int {
            return map[index]?.lowerEntry(snap_id + 1)?.value ?: 0
        }
    }

    expect {
        SnapshotArray(1).snap()
    }
}

