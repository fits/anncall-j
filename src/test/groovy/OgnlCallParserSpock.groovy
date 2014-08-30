import anncallj.annotation.Call
import anncallj.processor.OgnlCallParser
import spock.lang.*

class OgnlCallParserSpock extends Specification {
	def parser

	def setup() {
		parser = new OgnlCallParser()
	}

	def "true ognl with equals name"() {
		when:
			def data = new SampleData(name: 'sample01', value: 10)
			def ann = { 'name == "sample01"' } as Call
			def res = parser.parse(data, ann, [SampleData] as Class[])
		then:
			res._1() == true
			res._2().length == 1
			res._2()[0] == data
	}

	def "true ognl with equals name and value"() {
		when:
			def data = new SampleData(name: 'sample01', value: 10)
			def ann = { 'name eq "sample01" and value eq 10' } as Call
			def res = parser.parse(data, ann, [SampleData] as Class[])
		then:
			res._1() == true
			res._2().length == 1
			res._2()[0] == data
	}

	def "false ognl with not equals name"() {
		when:
			def data = new SampleData(name: 'sample01', value: 10)
			def ann = { 'name == "sampleAAA"' } as Call
			def res = parser.parse(data, ann, [SampleData] as Class[])
		then:
			res._1() == false
	}

	def "true ognl and Object parameter type"() {
		when:
			def data = new SampleData(name: 'sample01', value: 10)
			def ann = { 'name == "sample01"' } as Call
			def res = parser.parse(data, ann, [Object] as Class[])
		then:
			res._1() == true
			res._2().length == 1
			res._2()[0] == data
	}

	def "true ognl and illegal parameter type"() {
		when:
			def data = new SampleData(name: 'sample01', value: 10)
			def ann = { 'name == "sample01"' } as Call
			def res = parser.parse(data, ann, [String] as Class[])
		then:
			res._1() == false
	}

	class SampleData {
		String name
		int value
	}
}
