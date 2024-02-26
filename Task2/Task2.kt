import kotlin.math.sqrt

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Нужно 2 файла с данными")
        return
    }

    val circleFile = args[0]
    val pointFile = args[1]

    val readCircleFile = File(circleFile).readLines()
    val circleCenter = readCircleFile[0].split(" ").map { it.toDouble() }.let { Pair(it[0], it[1]) }
    val circleRadius = readCircleFile[1].toDouble()

    val points = readPointFile(pointFile)

    for (point in points) {
        val position = pointPosition(circleCenter, circleRadius, point)
        println(position)
    }
}

fun readPointFile(pointFile: String): List<Pair<Double, Double>> {
    val pointCoordinates = mutableListOf<Pair<Double, Double>>()
    File(pointFile).forEachLine {
        val (x, y) = it.split(" ").map { coord -> coord.toDouble() }
        pointCoordinates.add(Pair(x, y))
    }
    return (pointCoordinates)
}

fun distance(point1: Pair<Double, Double>, point2: Pair<Double, Double>): Double {
    val resx = point1.first - point2.first
    val resy = point1.second - point2.second
    return sqrt(resx * resx + resy * resy)
}

fun pointPosition(circleCenter: Pair<Double, Double>, circleRadius: Double, point: Pair<Double, Double>): Int {
    val dist = distance(circleCenter, point)
    return when {
        dist < circleRadius -> 1
        dist == circleRadius -> 0
        else -> 2
    }
}
