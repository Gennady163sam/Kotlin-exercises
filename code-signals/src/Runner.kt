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
            traverseTree(root)
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

data class Tree<T>(var value: T) {
    var left: Tree<T>? = null;
    var right: Tree<T>? = null;
}

fun traverseTree(t: Tree<Int>?): MutableList<Int> {
    val result = mutableListOf<Int>()
    if (t == null) return result
    val nodes = mutableListOf<Tree<Int>>()
    nodes.add(t)
    var currentIndex = 0
    while (currentIndex < nodes.size) {
        val currentNode = nodes[currentIndex]
        result.add(currentNode.value)
        if (currentNode.left != null) nodes.add(currentNode.left!!)
        if (currentNode.right != null) nodes.add(currentNode.right!!)
        currentIndex++
    }
    return result
}
