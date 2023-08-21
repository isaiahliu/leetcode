package p01xx

import util.expect

fun main() {
    class LRUCache(private val capacity: Int) {
        val cache = LinkedHashMap<Int, Int>()

        fun get(key: Int): Int {
            return cache[key]?.also {
                cache.remove(key)
                cache[key] = it
            } ?: -1
        }

        fun put(key: Int, value: Int) {
            cache.remove(key)

            cache[key] = value

            if (cache.size > capacity) {
                cache.remove(cache.keys.first())
            }
        }
    }

    expect {
        LRUCache(1).get(
            1
        )
    }
}

