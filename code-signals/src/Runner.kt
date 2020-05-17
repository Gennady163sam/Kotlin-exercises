package test

fun main() {
    val root = Tree(1).also {
        it.left = Tree(2).also {
            it.left = null
            it.right = Tree(3)
        }
        it.right = Tree(4).also {
            it.left = Tree(5)
        }
    }
    println(
            /*
            hashMap(
                    mutableListOf("insert",
                            "addToValue",
                            "get",
                            "insert",
                            "addToKey",
                            "addToValue",
                            "get"
                    ),
                    mutableListOf(
                            mutableListOf(1, 2),
                            mutableListOf(2),
                            mutableListOf(1),
                            mutableListOf(2,3),
                            mutableListOf(1),
                            mutableListOf(-1),
                            mutableListOf(3)
                    )
            )*/
            removeKFromList(ListNode(3).also {
                it.next = ListNode(1).also {
                    it.next = ListNode(2).also {
                        it.next = ListNode(3).also {
                            it.next = ListNode(4).also {
                                it.next = ListNode(5)
                            }
                        }
                    }
                }
            }, 3)
    )
}

fun hashMap(queryType: MutableList<String>, query: MutableList<MutableList<Int>>): Long {
    val myHashMap = mutableListOf<Int>()
    var shift = 0
    var result: Long = 0
    for(i in 0 until queryType.size) {
        if (queryType[i] == "insert") {
            val index = query[i][0]
            val value = query[i][1]
            while (myHashMap.size - 1 < index + shift) {
                myHashMap.add(0)
            }
            myHashMap[index] = value
        }
        if (queryType[i] == "get") {
            val index = query[i][0]
            while (myHashMap.size - 1 < index + shift) {
                myHashMap.add(0)
            }
            result += myHashMap[index - shift]
        }
        if (queryType[i] == "addToKey") {
            shift += query[i][0]
        }
        if (queryType[i] == "addToValue") {
            for (j in 0 until myHashMap.size) {
                myHashMap[j] += query[i][0]
            }
        }
    }
    return result
}

data class ListNode<T>(var value: T) {
    var next: ListNode<T>? = null;
}

fun removeKFromList(l: ListNode<Int>?, k: Int): ListNode<Int>? {
    var prev: ListNode<Int>? = null
    var current = l
    var head: ListNode<Int>? = null
    while (current != null) {
        if (current.value == k) {
            if (prev != null) {
                prev.next = current.next
            }
            current = current.next
            continue
        }
        if (head == null) head = current
        prev = current
        current = current.next
    }
    return head
}
