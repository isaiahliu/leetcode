package p19xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun deleteDuplicateFolder(paths: List<List<String>>): List<List<String>> {
            class Node(val name: String) {
                var delete = false
                var parent: Node? = null
                var degree = 0

                var subFolderSerialize = ""
                val children = hashMapOf<String, Node>()

                fun init(folders: List<String>, index: Int = 0) {
                    folders.getOrNull(index)?.also {
                        children.computeIfAbsent(it) {
                            degree++
                            Node(it).also {
                                it.parent = this
                            }
                        }.init(folders, index + 1)
                    }
                }
            }

            val leaves = hashMapOf<String, MutableSet<Node>>()
            val map = hashMapOf<String, Node>()

            val queue = LinkedList<Node>()

            fun Node.mark() {
                parent?.also { p ->
                    p.degree--

                    if (p.degree == 0) {
                        p.subFolderSerialize = p.children.map {
                            "${it.value.name}(${it.value.subFolderSerialize})"
                        }.sorted().joinToString(",")

                        map[p.subFolderSerialize]?.also {
                            if (!it.delete) {
                                it.delete = true
                                queue.add(it)
                            }
                            p.delete = true
                            queue.add(p)
                        } ?: run {
                            map[p.subFolderSerialize] = p
                        }
                    }
                }
            }

            fun Node.findLeaves() {
                if (children.isEmpty()) {
                    leaves.computeIfAbsent(name) { hashSetOf() }.add(this)
                } else {
                    children.forEach {
                        it.value.findLeaves()
                    }
                }
            }

            val root = Node("/")

            paths.forEach { root.init(it) }

            root.findLeaves()

            leaves.forEach {
                if (it.value.size > 1) {
                    it.value.forEach {
                        it.mark()
                    }
                }
            }

            while (queue.isNotEmpty()) {
                queue.poll().mark()
            }

            val result = arrayListOf<List<String>>()

            fun Node.output(route: List<String>) {
                if (route.isNotEmpty()) {
                    result.add(route)
                }

                children.map { (childName, node) ->
                    if (!node.delete) {
                        node.output(route + childName)
                    }
                }
            }

            root.output(emptyList())

            return result
        }
    }

    expect {
        Solution().deleteDuplicateFolder(
            listOf(
                listOf("a"),
                listOf("a", "b"),
                listOf("c"),
                listOf("c", "b"),
                listOf("d"),
                listOf("d", "a"),
            )
        )
    }
}