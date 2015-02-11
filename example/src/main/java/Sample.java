
import anncallj.annotation.Call;
import anncallj.processor.Caller;
import anncallj.processor.Callers;

class Sample {
	public static void main(String... args) {
		Caller<String> c1 = Callers.regexCaller(Def1.class);

		String r1 = c1.call("testdata105");
		System.out.println(r1);

		int r2 = c1.call("109 plus 130");
		System.out.println(r2);

		Data d1 = new Data("sample", 100);
		Data d2 = new Data("sample", 1);

		Caller<Object> c2 = Callers.ognlCaller(Def2.class);

		c2.call(d1);
		int r3 = c2.call(d2);

		System.out.println(r3);
	}

	public static class Def1 {
		@Call("testdata([0-9]+)")
		public static String testData(int num) {
			return "test" + num;
		}

		@Call("([0-9]+) plus ([0-9]+)")
		public static int testData(int first, int second) {
			return first + second;
		}
	}

	public static class Def2 {
		@Call("name eq \"sample\" and value > 50")
		public static void printData(Data data) {
			System.out.println("*** Data: " + data.getName() + ", " + data.getValue());
		}

		@Call("value < 10")
		public static int dataValue(Data data) {
			return data.getValue() * 3;
		}
	}

	public static class Data {
		private String name;
		private int value;

		public Data(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return this.name;
		}

		public int getValue() {
			return this.value;
		}
	}
}
