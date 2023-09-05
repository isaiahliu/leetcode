package p22xx

import util.expect

fun main() {
    class Node(val ch: Char) {
        lateinit var prev: Node
        var next: Node? = null
    }

    class TextEditor {
        val root = Node(' ')
        var cursor = root

        fun addText(text: String) {
            text.forEach {
                val n = Node(it)

                cursor.next?.prev = n
                n.next = cursor.next
                n.prev = cursor
                cursor.next = n

                cursor = n
            }
        }

        fun deleteText(k: Int): Int {
            var deleteCount = 0

            while (deleteCount < k && cursor != root) {
                cursor.next?.prev = cursor.prev
                cursor.prev.next = cursor.next

                cursor = cursor.prev
                deleteCount++
            }

            return deleteCount
        }

        private fun print(): String {
            val str = StringBuilder()

            var t = cursor
            while (str.length < 10 && t != root) {
                str.insert(0, t.ch)

                t = t.prev
            }

            return str.toString()
        }

        fun cursorLeft(k: Int): String {
            var removeCount = 0

            while (removeCount < k && cursor != root) {
                cursor = cursor.prev
                removeCount++
            }

            return print()
        }

        fun cursorRight(k: Int): String {
            var removeCount = 0

            while (removeCount < k) {
                cursor = cursor.next ?: break
                removeCount++
            }

            return print()
        }
    }

    expect {
        TextEditor().addText(
            ""
        )
    }
}