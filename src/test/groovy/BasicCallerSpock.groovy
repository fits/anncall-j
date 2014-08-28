import anncallj.annotation.Call
import anncallj.processor.RegexCallParser
import anncallj.processor.BasicCaller

import spock.lang.*

class BasicCallerSpock extends Specification {
	def caller

	def setup() {
		caller = new BasicCaller(new RegexCallParser(), SampleClass)
	}

	def "call static method with no args"() {
		when:
			def res = caller.call('sample')
		then:
			res == 'sample01'
	}

	def "call static method with one arg"() {
		when:
			def res = caller.call('test11call')
		then:
			res == 'test:11'
	}

	def "call static method with two args"() {
		when:
			def res = caller.call('10 plus 20')
		then:
			res == 30
	}

	def "call static method with String and int args"() {
		when:
			def res = caller.call('test : 20')
		then:
			res == 'test : 200'
	}

	def "call instance method with one arg"() {
		when:
			def caller = new BasicCaller(new RegexCallParser(), new SampleClass2(name: 'abc'))
			def res = caller.call('test1call')
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