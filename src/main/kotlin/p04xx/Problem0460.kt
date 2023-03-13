package p04xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class FreqNode(val freq: Int) {
        val children = LinkedList<Int>()

        var prev: FreqNode? = null
        var next: FreqNode? = null
    }

    class Node(val key: Int, var value: Int, var freqNode: FreqNode) {
        init {
            freqNode.children.add(key)
        }
    }

    class LFUCache(val capacity: Int) {
        var head: FreqNode = FreqNode(Int.MIN_VALUE)
        var tail: FreqNode = FreqNode(Int.MAX_VALUE).also {
            it.prev = head
            head.next = it
        }

        val map = hashMapOf<Int, Node>()

        var size = 0

        fun get(key: Int): Int {
            return map[key]?.value?.also {
                inc(key, it)
            } ?: -1
        }

        fun put(key: Int, value: Int) {
            if (key !in map) {
                if (size == capacity) {
                    head.next?.children?.poll()?.let { map[it] }?.also {
                        map.remove(it.key)

                        if (it.freqNode.children.isEmpty()) {
                            it.freqNode.prev?.next = it.freqNode.next
                            it.freqNode.next?.prev = it.freqNode.prev
                        }
                    }
                } else {
                    size++
                }
            }

            inc(key, value)
        }

        private fun moveNode(node: Node, fromNode: FreqNode, toNode: FreqNode) {
            node.freqNode = toNode
            toNode.children.add(node.key)

            fromNode.children.remove(node.key)
            if (fromNode.children.isEmpty()) {
                fromNode.prev?.next = fromNode.next
                fromNode.next?.prev = fromNode.prev
            }
        }

        fun inc(key: Int, value: Int): Boolean {
            return when (key) {
                in map -> {
                    val node = map[key] ?: return false

                    node.value = value

                    val freqNode = node.freqNode
                    var nextFreqNode = freqNode.next ?: return false
                    if (nextFreqNode.freq != freqNode.freq + 1) {
                        nextFreqNode = FreqNode(freqNode.freq + 1)

                        freqNode.next?.prev = nextFreqNode
                        nextFreqNode.next = freqNode.next
                        freqNode.next = nextFreqNode
                        nextFreqNode.prev = freqNode
                    }

                    moveNode(node, freqNode, nextFreqNode)

                    false
                }

                else -> {
                    var freqNode = head.next ?: return false
                    if (freqNode.freq != 1) {
                        freqNode = FreqNode(1)
                        head.next?.prev = freqNode
                        freqNode.next = head.next
                        head.next = freqNode
                        freqNode.prev = head
                    }

                    map[key] = Node(key, value, freqNode)

                    true
                }
            }
        }
    }

    measureTimeMillis {
        val cache = LFUCache(2)
        cache.put(1, 1)
        cache.put(2, 2)
        cache.get(1).also { println(it) }
        cache.put(3, 3)
        cache.get(2).also { println(it) }
        cache.get(3).also { println(it) }
        cache.put(4, 4)
        cache.get(1).also { println(it) }
        cache.get(3).also { println(it) }
        cache.get(4).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}