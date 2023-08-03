package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Person(val name: String) {
        private var dead = false
        private var parent: Person? = null
        private val children = arrayListOf<Person>()

        private var innerOrder: List<String>? = null

        val inheritanceOrder: List<String>
            get() {
                return innerOrder ?: run {
                    val order = arrayListOf<String>()

                    if (!dead) {
                        order.add(name)
                    }

                    children.forEach {
                        order.addAll(it.inheritanceOrder)
                    }

                    order.also { innerOrder = it }
                }
            }

        fun addChild(child: Person) {
            child.parent = this
            children.add(child)
            notifyParent()
        }

        fun die() {
            dead = true
            notifyParent()
        }

        fun notifyParent() {
            innerOrder = null
            parent?.notifyParent()
        }
    }

    class ThroneInheritance(kingName: String) {
        val root = Person(kingName)
        val map = hashMapOf(kingName to root)
        fun birth(parentName: String, childName: String) {
            val child = Person(childName)
            map[childName] = child

            map[parentName]?.addChild(child)
        }

        fun death(name: String) {
            map[name]?.die()
        }

        fun getInheritanceOrder(): List<String> {
            return root.inheritanceOrder
        }
    }

    measureTimeMillis {
        ThroneInheritance("").birth(
            "", ""
        ).also { println("${it} should be ${it}") }
    }
}

