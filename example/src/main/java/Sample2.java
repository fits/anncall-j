
import anncallj.annotation.Call;
import anncallj.processor.Caller;
import anncallj.processor.Callers;

public class Sample2 {
	private static Caller<Object> caller = Callers.ognlCaller(Sample2.class);

	public static void main(String... args) {
		System.out.println(caller.<Integer>call(5));
	}

	@Call("#this == 0")
	public static int zero(Integer n) {
		return 1;
	}

	@Call("#this > 0")
	public static int number(Integer n) {
		return n * caller.<Integer>call(n - 1);
	}
}
