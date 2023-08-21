package p16xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun mostCompetitive(nums: IntArray, k: Int): IntArray {
            class Node(val num: Int) {
                lateinit var prev: Node
                lateinit var next: Node
            }

            val head = Node(Int.MIN_VALUE)
            val tail = Node(Int.MAX_VALUE)
            head.next = tail
            tail.prev = head

            val decNodes = LinkedList<Node>()

            nums.forEachIndexed { index, num ->
                val newNode = Node(num).also {
                    it.prev = tail.prev
                    it.next = tail
                    tail.prev.next = it
                    tail.prev = it
                }

                if (newNode.num < newNode.prev.num) {
                    decNodes.add(newNode)
                }

                if (index >= k) {
                    decNodes.poll()?.also {
                        it.prev.prev.next = it
                        it.prev = it.prev.prev

                        if (it.num < it.prev.num) {
                            decNodes.push(it)
                        }
                    } ?: run {
                        tail.prev.prev.next = tail
                        tail.prev = tail.prev.prev
                    }
                }
            }

            var t = head
            return IntArray(k) {
                t = t.next
                t.num
            }
        }
    }

    expect {
        Solution().mostCompetitive(
            intArrayOf(), 3
        )
    }
}

