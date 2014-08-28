package anncallj.processor;

public class Callers {
	public static Caller regexCaller(Class<?> cls) {
		return new BasicCaller(new RegexCallParser(), cls);
	}

	public static Caller regexCaller(Object obj) {
		return new BasicCaller(new RegexCallParser(), obj);
	}
}
