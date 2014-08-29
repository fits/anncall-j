package anncallj.processor;

public class Callers {
	public static Caller<String> regexCaller(Class<?> cls) {
		return new BasicCaller<>(new RegexCallParser(), cls);
	}

	public static Caller<String> regexCaller(Object obj) {
		return new BasicCaller<>(new RegexCallParser(), obj);
	}
}
