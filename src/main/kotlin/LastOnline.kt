const val SECONDS_PER_MINUTE = 60
const val SECONDS_PER_HOUR = 60 * 60
const val SECONDS_PER_DAY = 60 * 60 * 24

enum class PartsOfTime {
    HOUR, MINUTE
}

enum class TypesOfQuantity {
    ONE, FEW, MANY
}

fun main() {

//  Если количество секунд от 0 до 60, работает вариант с только что.
    println(agoToText(0))
    println(agoToText(SECONDS_PER_MINUTE))

//  Если количество секунд от 61 до 60 * 60 (один час), работает вариант с x минут назад.
    println(agoToText(SECONDS_PER_MINUTE + 1))
    println(agoToText(SECONDS_PER_MINUTE * 60))

//  Если количество секунд от 60 * 60 + 1 до 24 * 60 * 60 (сутки) и , работает вариант с x часов назад.
    println(agoToText(SECONDS_PER_HOUR + 1))
    println(agoToText(SECONDS_PER_HOUR * 24))

//  Если количество секунд от суток до двух, то - сегодня.
    println(agoToText(SECONDS_PER_DAY + 1))
    println(agoToText(SECONDS_PER_DAY * 2))

//  Если количество секунд от двух суток до трёх, то - вчера.
    println(agoToText(SECONDS_PER_DAY * 2 + 1))
    println(agoToText(SECONDS_PER_DAY * 3))

//  Если количество секунд больше трёх суток, то - давно.
    println(agoToText(SECONDS_PER_DAY * 3 + 1))

}

fun agoToText(agoSeconds: Int): String {

    return "был(а) " + when {
        agoSeconds < 0 ->
            error("Negative argument!")
        agoSeconds <= SECONDS_PER_MINUTE ->
            "только что"
        agoSeconds <= SECONDS_PER_HOUR -> {
            val minutes = agoSeconds / SECONDS_PER_MINUTE
            "$minutes ${declinePartOfTime(minutes, PartsOfTime.MINUTE)} назад"
        }
        agoSeconds <= SECONDS_PER_DAY -> {
            val hours = agoSeconds / SECONDS_PER_HOUR
            "в сети $hours ${declinePartOfTime(hours, PartsOfTime.HOUR)} назад"
        }
        agoSeconds <= 2 * SECONDS_PER_DAY -> "в сети сегодня"
        agoSeconds <= 3 * SECONDS_PER_DAY -> "в сети вчера"
        else -> "в сети давно"
    }

}

fun declinePartOfTime(quantity: Int, partOfTime: PartsOfTime): String {

    val typeOfQuantity = when {
        quantity % 10 == 1 && quantity % 100 != 11 -> TypesOfQuantity.ONE
        quantity % 10 in 2..4 && !(quantity % 100 in 12..14) -> TypesOfQuantity.FEW
        else -> TypesOfQuantity.MANY
    }

    return when (partOfTime) {
        PartsOfTime.MINUTE -> when (typeOfQuantity) {
            TypesOfQuantity.ONE -> "минуту"
            TypesOfQuantity.FEW -> "минуты"
            TypesOfQuantity.MANY -> "минут"
        }
        PartsOfTime.HOUR -> when (typeOfQuantity) {
            TypesOfQuantity.ONE -> "час"
            TypesOfQuantity.FEW -> "часа"
            TypesOfQuantity.MANY -> "часов"
        }
    }

}
