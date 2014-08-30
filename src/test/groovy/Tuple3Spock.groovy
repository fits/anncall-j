
import anncallj.data.Tuple3
import spock.lang.*

class Tuple3Spock extends Specification {
	def "property values"() {
		when:
			def res = new Tuple3("sample", 5, true)
		then:
			res._1() == "sample"
			res._2() == 5
			res._3() == true
	}

	def "same all property values"() {
		when:
			def res = new Tuple3("sample", 5, true)
		then:
			res == new Tuple3("sample", 5, true)
	}

	def "different property value"() {
		when:
			def res = new Tuple3("sample", 5, true)
		then:
			res != new Tuple3("sample", 5, false)
			res != new Tuple3("sample", 1, true)
	}
}
