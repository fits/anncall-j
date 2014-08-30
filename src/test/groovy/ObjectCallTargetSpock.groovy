import anncallj.annotation.Call
import anncallj.processor.ObjectCallTarget

import spock.lang.*

class ObjectCallTargetSpock extends Specification {

	def "one class"() {
		when:
			def target = new ObjectCallTarget(SampleClass1)
			def res = target.list()
		then:
			def ite = res.iterator()
			ite.hasNext() == true
			def r1 = ite.next()
			r1._1().value() == '^sample$'
			r1._2().name == 'test1'

			ite.hasNext() == false
	}

	def "two class"() {
		when:
			def target = new ObjectCallTarget(SampleClass1, SampleClass2)
			def res = target.list()
		then:
			def ite = res.iterator()
			ite.hasNext() == true
			def r1 = ite.next()
			r1._1().value() == '^sample$'
			r1._2().name == 'test1'

			ite.hasNext() == true
			def r2 = ite.next()
			r2._2().name == 'first'

			ite.hasNext() == true
			def r3 = ite.next()
			r3._2().name == 'third'

			ite.hasNext() == false
	}


	class SampleClass1 {
		@Call('^sample$')
		static String test1() {
			'sample01'
		}

		static String test2(String value) {
			"test:${value}"
		}
	}

	class SampleClass2 {
		@Call('^([0-9]+) plus ([0-9]+)$')
		static int first(String first, String second) {
			(first as int) + (second as int)
		}

		static void second() {
		}

		@Call('^([a-zA-Z]+) : ([0-9]+)$')
		static String third(String first, int second) {
			"${first} : ${second * 10}"
		}
	}
}