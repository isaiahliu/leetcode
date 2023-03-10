package p04xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Codec {
        // Encodes a tree to a single string.
        fun serialize(root: TreeNode?): String {
            val result = StringBuilder()

            root?.also {
                result.append(it.`val`)

                val tasks = arrayListOf(it)

                while (tasks.isNotEmpty()) {
                    tasks.toList().also { tasks.clear() }.forEach {
                        result.append(",")
                        it.left?.also {
                            result.append(it.`val`)
                            tasks.add(it)
                        }
                        result.append(",")
                        it.right?.also {
                            result.append(it.`val`)
                            tasks.add(it)
                        }
                    }
                }
            } ?: run {
                result.append(",")
            }

            return result.toString()
        }

        // Decodes your encoded data to tree.
        fun deserialize(data: String): TreeNode? {
            val nodeValues = data.split(",")

            val root = nodeValues[0].toIntOrNull()?.let { TreeNode(it) } ?: return null

            val tasks = LinkedList<TreeNode>()
            tasks.add(root)

            var index = 1

            while (tasks.isNotEmpty()) {
                repeat(tasks.size) {
                    val node = tasks.pop()

                    nodeValues[index++].toIntOrNull()?.let { TreeNode(it) }?.also {
                        node.left = it
                        tasks.add(it)
                    }

                    nodeValues[index++].toIntOrNull()?.let { TreeNode(it) }?.also {
                        node.right = it
                        tasks.add(it)
                    }
                }
            }

            return root
        }
    }

    measureTimeMillis {
        Codec().serialize(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}