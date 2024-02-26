import kotlinx.serialization.json.*
import java.io.File

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Нужно 2 файла с данными")
        return
    }

    val valuesFile = File(args[0])
    val testsFile = File(args[1])

    val jsonObjectValues = Json.parseToJsonElement(valuesFile.readText().trimIndent()).jsonObject
    val valuesArray = jsonObjectValues["values"]?.jsonArray

    val jsonObjectTests = Json.parseToJsonElement(testsFile.readText().trimIndent()).jsonObject
    val testsArray = jsonObjectTests["tests"]?.jsonArray

    val updatedTestsArray = replaceValueInTestsArray(testsArray, valuesArray)
    println(updatedTestsArray)
}

fun replaceValueInTestsArray(testsArray: JsonArray?, valuesArray: JsonArray?): JsonArray? {
    if (testsArray == null || valuesArray == null) return null

    val valuesMap = valuesArray.associateBy { it.jsonObject["id"]?.jsonPrimitive?.int }
    val updatedTestsArray = JsonArray(testsArray.map { testElement ->
        val id = testElement.jsonObject["id"]?.jsonPrimitive?.int
        val value = valuesMap[id]?.jsonObject?.get("value")?.jsonPrimitive?.content
        if (id != null && value != null) {
            JsonObject(testElement.jsonObject.toMutableMap().apply {
                put("value", JsonPrimitive(value))
            })
        } else {
            testElement
        }
    })
    return updatedTestsArray
}
