package test

fun main() {
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
            arrayMax(intArrayOf(1, 6, 2, 3))
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

fun arrayMax(arr: IntArray): Int {
    if (arr.size == 1) return arr[0]
    val localMax = arrayMax(arr.copyOfRange(1, arr.size))
    return if (arr[0] > localMax) arr[0] else localMax
}