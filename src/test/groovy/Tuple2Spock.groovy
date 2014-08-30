
import anncallj.data.Tuple2
import spock.lang.*

class Tuple2Spock extends Specification {
	def "property values"() {
		when:
			def res = new Tuple2("sample", 5)
		then:
			res._1() == "sample"
			res._2() == 5
	}

	def "same all property values"() {
		when:
			def res = new Tuple2("sample", 5)
		then:
			res == new Tuple2("sample", 5)
	}

	def "different property value"() {
		when:
			def res = new Tuple2("sample", 5)
		then:
			res != new Tuple2("sample", 1)
	}
}
