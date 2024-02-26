fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Введите 2 аргумента")
        return
    }

    val n = args[0].toIntOrNull()
    val m = args[1].toIntOrNull()

    if (n == null || m == null) {
        println("Введите валидные значения")
        return
    }

    var nums = mutableListOf<Int>()
    for (en in 1..n) nums.addAll(listOf(en))

    var index = 0
    var result = nums[index].toString()
    index = (index + m - 1) % n
    result += nums[index].toString()

    while (true) {
        if (index != 0) {
            index = (index + m - 1) % n
            if (index == 0) {
                println(result)
                break
            }
            else result += nums[index].toString()
        }
    }
}
