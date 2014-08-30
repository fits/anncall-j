import anncallj.annotation.Call
import anncallj.processor.RegexCallParser
import spock.lang.*

class RegexCallParserSpock extends Specification {
	def parser

	def setup() {
		parser = new RegexCallParser()
	}

	def "parse with no arg"() {
		when:
			def ann = { '^sample$' } as Call
			def res = parser.parse('sample', ann, [] as Class[])
		then:
			res._1() == true
			res._2().length == 0
	}

	def "parse with one string arg"() {
		when:
			def ann = { '^test([0-9]+)call$' } as Call
			def res = parser.parse('test11call', ann, [ String ] as Class[])
		then:
			res._1() == true
			res._2().length == 1
			res._2()[0] == '11'
	}

	def "parse with two string args"() {
		when:
			def ann = { '^([0-9]+) plus ([0-9]+)$' } as Call
			def res = parser.parse('10 plus 20', ann, [ String, String ] as Class[])
		then:
			res._1() == true
			res._2().length == 2
			res._2()[0] == '10'
			res._2()[1] == '20'
	}

	def "parse with string and int args"() {
		when:
			def ann = { '^([a-z]+) : ([0-9]+)$' } as Call
			def res = parser.parse('test : 12', ann, [ String, int ] as Class[])
		then:
			res._1() == true
			res._2().length == 2
			res._2()[0] == 'test'
			res._2()[1] == 12
	}

	def "parse failed"() {
		when:
			def ann = { '^sample$' } as Call
			def res = parser.parse('test', ann, [] as Class[])
		then:
			res._1() == false
	}
}