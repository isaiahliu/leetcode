package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Node(val value: Int) {
        var prev: Node? = null
        var next: Node? = null
    }

    class MyLinkedList {
        val head = Node(Int.MIN_VALUE)
        val tail = Node(Int.MAX_VALUE)
        var size = 0

        init {
            head.next = tail
            tail.prev = head
        }

        fun get(index: Int): Int {
            return find(index)?.value ?: -1
        }

        fun addAtHead(`val`: Int) {
            val newNode = Node(`val`)

            head.next?.prev = newNode
            newNode.next = head.next
            head.next = newNode
            newNode.prev = head

            size++
        }

        fun addAtTail(`val`: Int) {
            val newNode = Node(`val`)

            tail.prev?.next = newNode
            newNode.prev = tail.prev
            tail.prev = newNode
            newNode.next = tail

            size++
        }

        fun addAtIndex(index: Int, `val`: Int) {
            if (index == size) {
                addAtTail(`val`)
            } else {
                find(index)?.also {
                    val newNode = Node(`val`)

                    it.prev?.next = newNode
                    newNode.prev = it.prev
                    it.prev = newNode
                    newNode.next = it
                    size++
                }
            }
        }

        fun deleteAtIndex(index: Int) {
            find(index)?.also {
                it.prev?.next = it.next
                it.next?.prev = it.prev
                size--
            }
        }

        private fun find(index: Int): Node? {
            when {
                index >= size || index < 0 -> {
                    return null
                }

                index < size / 2 -> {
                    var t = head.next
                    repeat(index) {
                        t = t?.next
                    }

                    return t
                }

                else -> {
                    var t = tail.prev
                    repeat(size - index - 1) {
                        t = t?.prev
                    }

                    return t
                }
            }
        }
    }

    measureTimeMillis {
        val l = MyLinkedList()

        l.addAtIndex(1, 2)

        l.get(0).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}