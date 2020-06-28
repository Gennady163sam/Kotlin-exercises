package test

import java.lang.StringBuilder
import java.util.stream.IntStream
import kotlin.math.abs
import kotlin.math.max

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
            /*isListPalindrome(ListNode(1).also {
                it.next = ListNode(1000000000).also {
                    it.next = ListNode(-1000000000).also {
                        it.next = ListNode(-1000000000).also {
                            it.next = ListNode(1000000000)
                        }
                    }
                }
            })*/
        additionWithoutCarrying(456, 1734)
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

fun isListPalindrome(l: ListNode<Int>?): Boolean {
    if (l == null) return true
    var slow = l
    var fast = l
    var size = 0
    // find size
    while (fast != null) {
        size+=2
        slow = slow!!.next
        fast = fast.next?.next

    }
    return true
}

/*fun kthLargestElement(nums: MutableList<Int>, k: Int): Int {
    val queue = sortedOf<Int>()
    queue.addAll(nums)
    return if (queue.size - k > 0) queue.elementAt(queue.size - k) else queue.elementAt(0)
}*/
/*fun goodStringsCount(len: Int): Int {
    if (len == 1) return 0
    var sum = 0
    for(i in 26 - (len - 1) downTo 0) {
        sum += i *
    }
    return sum
}*/


