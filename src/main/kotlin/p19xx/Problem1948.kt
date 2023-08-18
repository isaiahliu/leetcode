package p19xx

import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun deleteDuplicateFolder(paths: List<List<String>>): List<List<String>> {
            val m = 9223372036854775807L.toBigInteger()

            class Node(val name: String) {
                var delete = false
                var parent: Node? = null
                var degree = 0

                var subFolderHash = 0L
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

            val nameHashes = hashMapOf<String, Long>()
            val leaves = hashMapOf<String, MutableSet<Node>>()
            val map = hashMapOf<Long, Node>()

            val queue = LinkedList<Node>()

            var hashIndex = 1L

            val hashBase = 256.toBigInteger()

            fun Node.mark() {
                parent?.also { p ->
                    p.degree--

                    if (p.degree == 0) {
                        p.subFolderHash = p.children.map {
                            it.value.subFolderHash.toBigInteger() + (nameHashes[it.value.name]?.toBigInteger()
                                ?: BigInteger.ZERO)
                        }.sorted().fold(BigInteger.ZERO) { hash, subHash ->
                            (hash * hashBase + subHash) % m
                        }.toLong()

                        map[p.subFolderHash]?.also {
                            if (!it.delete) {
                                it.delete = true
                                queue.add(it)
                            }
                            p.delete = true
                            queue.add(p)
                        } ?: run {
                            map[p.subFolderHash] = p
                        }
                    }
                }
            }

            fun Node.findLeaves() {
                val hash = nameHashes.computeIfAbsent(name) { hashIndex++ }

                if (children.isEmpty()) {
                    subFolderHash = hash
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

                hashIndex++
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

    measureTimeMillis {
        Solution().deleteDuplicateFolder(
            listOf(
                listOf("a"),
                listOf("a", "b"),
                listOf("c"),
                listOf("c", "b"),
                listOf("d"),
                listOf("d", "a"),
            )
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}