package p04xx

import util.expect

fun main() {
    class FreqNode(val freq: Int) {
        val children = hashSetOf<String>()

        var prev: FreqNode? = null
        var next: FreqNode? = null
    }

    class Node(val key: String, var freqNode: FreqNode) {
        init {
            freqNode.children.add(key)
        }
    }

    class AllOne {
        var head: FreqNode = FreqNode(Int.MIN_VALUE)
        var tail: FreqNode = FreqNode(Int.MAX_VALUE).also {
            it.prev = head
            head.next = it
        }

        val map = hashMapOf<String, Node>()

        private fun moveNode(node: Node, fromNode: FreqNode, toNode: FreqNode) {
            node.freqNode = toNode
            toNode.children.add(node.key)

            fromNode.children.remove(node.key)
            if (fromNode.children.isEmpty()) {
                fromNode.prev?.next = fromNode.next
                fromNode.next?.prev = fromNode.prev
            }
        }

        fun inc(key: String) {
            when (key) {
                in map -> {
                    val node = map[key] ?: return

                    val freqNode = node.freqNode
                    var nextFreqNode = freqNode.next ?: return
                    if (nextFreqNode.freq != freqNode.freq + 1) {
                        nextFreqNode = FreqNode(freqNode.freq + 1)

                        freqNode.next?.prev = nextFreqNode
                        nextFreqNode.next = freqNode.next
                        freqNode.next = nextFreqNode
                        nextFreqNode.prev = freqNode
                    }

                    moveNode(node, freqNode, nextFreqNode)
                }

                else -> {
                    var freqNode = head.next ?: return
                    if (freqNode.freq != 1) {
                        freqNode = FreqNode(1)
                        head.next?.prev = freqNode
                        freqNode.next = head.next
                        head.next = freqNode
                        freqNode.prev = head
                    }

                    map[key] = Node(key, freqNode)
                }
            }
        }

        fun dec(key: String) {
            map[key]?.also {
                val freqNode = it.freqNode

                if (freqNode.freq > 1) {
                    var prevFreqNode = freqNode.prev ?: return
                    if (prevFreqNode.freq != freqNode.freq - 1) {
                        prevFreqNode = FreqNode(freqNode.freq - 1)

                        freqNode.prev?.next = prevFreqNode
                        prevFreqNode.prev = freqNode.prev
                        freqNode.prev = prevFreqNode
                        prevFreqNode.next = freqNode
                    }

                    moveNode(it, freqNode, prevFreqNode)
                } else {
                    map.remove(key)
                    freqNode.children.remove(key)

                    if (freqNode.children.isEmpty()) {
                        freqNode.prev?.next = freqNode.next
                        freqNode.next?.prev = freqNode.prev
                    }
                }
            }
        }

        fun getMaxKey(): String {
            return tail.prev?.children?.firstOrNull() ?: ""
        }

        fun getMinKey(): String {
            return head.next?.children?.firstOrNull() ?: ""
        }
    }

    expect {
        val t = AllOne()
        t.inc("a")
        t.inc("b")
        t.inc("b")
        t.inc("c")
        t.inc("c")
        t.inc("c")
        t.dec("b")
        t.dec("b")
        t.dec("a")

        t.getMinKey()
        t.getMaxKey()
    }
}


