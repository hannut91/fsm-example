import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ParserTest : DescribeSpec({
    val DATA = "console.log(\"안녕하세요\");console.log(\"반갑습니다\");"

    describe("read") {
        it("returns only string") {
            val parser = Parser(DATA)
            parser.read().shouldBe("안녕하세요반갑습니다")
        }
    }
})
