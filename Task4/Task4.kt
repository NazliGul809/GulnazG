import java.io.File

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Нужен 1 файл с данными")
        return
    }

    val fileName = args[0]
    val file = File(fileName)
    if (!file.exists()) {
        println("Файл $fileName не найден.")
        return
    }

    val nums = mutableListOf<Int>()
    file.forEachLine { line ->
        val numbers = line.split("\\s+".toRegex()).mapNotNull { it.toIntOrNull() }
        nums.addAll(numbers)
    }

    val minMovesRequired = minMoves(nums).also {
        println(it)
    }
}

fun minMoves(nums: MutableList<Int>): Int {
    val avg = nums.average().toInt()
    return nums.map { kotlin.math.abs(it - avg) }.sum()
}
