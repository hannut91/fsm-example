interface State {
    fun process(value: Char)
}

class ReadingString(val parser: Parser): State {
    override fun process(value: Char) {
        if (value == "\""[0]) {
            parser.appends()
            parser.changeState(FindingString(parser))
        } else {
            parser.putToTemp(value)
        }
    }
}

class FindingString(val parser: Parser): State {
    override fun process(value: Char) {
        if (value == "\""[0]) {
            parser.clearTemp()
            parser.changeState(ReadingString(parser))
        }
    }
}

class Parser(val data: String) {
    var state: State = FindingString(this)

    var cursor: Int = 0

    var temp: String = ""
    var result: String = ""

    fun read(): String {
        while (true) {
            val char = current()
            if (char == null) {
                return result
            }

            moveCursor()

            state.process(char)
        }
    }

    fun current(): Char? {
        if (cursor >= data.length) {
            return null
        }

        return data[cursor]
    }

    fun putToTemp(value: Char) {
        temp += value
    }

    fun clearTemp() {
        temp = ""
    }

    fun appends() {
        result += temp
    }

    fun changeState(s: State) {
        state = s
    }

    fun moveCursor() {
        this.cursor = this.cursor + 1
    }
}