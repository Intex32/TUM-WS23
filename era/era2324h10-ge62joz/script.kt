fun main() {
    val cache = List<MutableList<String>>(8) { mutableListOf() }.toMutableList()
    cache[0] += listOf("0x14EAAB", "0x9F30", "0x29D556", "0x126A")
    cache[1] += listOf("0x55EF", "0x7A20A", "0x55D55")
    cache[2] += listOf("0x55EF", "0x33C19", "0xCFB28", "0x7521")
    cache[3] += listOf("0x17B74", "0x55EF")
    cache[4] += listOf("0x1227")
    cache[5] += listOf("0xBDB")
    cache[6] += listOf("0x3D72A")
    println(cache)

    cache.forEach {
        it += List(4) { "" }
    }

    listOf(
        "0x706F8",
        "0x2FCB80",
        "0x150CF6",
        "0xE55289",
        "0xF68088",
        "0xD1740C",
        "0xAB7F93",
        "0xFD1AC2",
        "0xF8C132",
        "0x595308",
        "0x9DB773",
        "0x2728B",
        "0x9D2DDF",
        "0xF3D465",
        "0xB3FF07",
        "0x515BFC",
        "0x4D512A",
        "0x2CA60F",
        "0x8F466",
        "0x714BBA",
        "0x83FCB5",
        "0x2F60A",
        "0xF33618",
        "0x798015",
        "0x4717DC",
    ).forEach { new ->
        val binary = hexToBinary(new.substring(2))
        val tagBinary = binary.substring(0, binary.length - 3 - 3)
        val tagHex = binToHex(tagBinary)
        val setBin = binary.substring(binary.length - 3 - 3, binary.length - 3)
        val set = setBin.toInt(2)
        cache[set].add(0, "0x$tagHex")
    }

    println(cache)
    println()

    println(cache.joinToString("\n") {
        it.take(4).joinToString(" | ", "| ", " |") +
                "\n|-----------|-----------|-----------|-----------|"
    })
}

fun hexToBinary(hex: String): String {
    val i = hex.toInt(16)
    val bin = Integer.toBinaryString(i)
    return bin
}

fun binToHex(bin: String): String {
    val decimal: Int = bin.toInt(2)
    return decimal.toString(16)
}