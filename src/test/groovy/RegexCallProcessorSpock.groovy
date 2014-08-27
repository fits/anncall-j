import anncallj.annotation.Call
import anncallj.processor.RegexCallProcessor
import spock.lang.*

class RegexCallProcessorSpock extends Specification {
	def processor

	def setup() {
		processor = new RegexCallProcessor()
	}

	def "call static method with no args"() {
		when:
			def res = processor.call('sample', SampleClass)
		then:
			res == 'sample01'
	}

	def "call static method with one arg"() {
		when:
			def res = processor.call('test11call', SampleClass)
		then:
			res == 'test:11'
	}

	def "call static method with two args"() {
		when:
			def res = processor.call('10 plus 20', SampleClass)
		then:
			res == 30
	}

	def "call static method with String and int args"() {
		when:
			def res = processor.call('test : 20', SampleClass)
		then:
			res == 'test : 200'
	}

	def "call instance method with one arg"() {
		when:
			def res = processor.call('test1call', new SampleClass2(name: 'abc'))
		then:
			res == 'abc-1'
	}


	class SampleClass {
		@Call('^sample$')
		static String sample() {
			'sample01'
		}

		@Call('^test([0-9]+)call$')
		static String test(String value) {
			"test:${value}"
		}

		@Call('^([0-9]+) plus ([0-9]+)$')
		static int calc(String first, String second) {
			(first as int) + (second as int)
		}

		@Call('^([a-zA-Z]+) : ([0-9]+)$')
		static String sample2(String first, int second) {
			"${first} : ${second * 10}"
		}
	}

	class SampleClass2 {
		def name

		@Call('^test([0-9]+)call$')
		String test(String value) {
			"${name}-${value}"
		}
	}
}