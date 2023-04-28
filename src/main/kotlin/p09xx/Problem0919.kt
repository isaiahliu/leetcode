package p09xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class CBTInserter(val root: TreeNode?) {
        val parents = LinkedList<TreeNode>()
        val children = LinkedList<TreeNode>()

        init {
            root?.also { parents.add(it) }
        }

        fun insert(`val`: Int): Int {
            while (true) {
                if (parents.isEmpty()) {
                    parents.addAll(children)
                    children.clear()
                }


                if (children.size % 2 == 0) {
                    val parent = parents.peek()
                    val left = parent.left
                    if (left != null) {
                        children.add(left)
                        continue
                    } else {
                        parent.left = TreeNode(`val`).also {
                            children.add(it)
                        }

                        parent.right = null
                        return parent.`val`
                    }
                } else {
                    val parent = parents.poll()
                    val right = parent.right
                    if (right != null) {
                        children.add(right)
                        continue
                    } else {
                        parent.right = TreeNode(`val`).also {
                            children.add(it)
                        }

                        return parent.`val`
                    }
                }
            }
        }

        fun get_root(): TreeNode? {
            return root
        }
    }


    measureTimeMillis {
        CBTInserter(null).insert(
            1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}