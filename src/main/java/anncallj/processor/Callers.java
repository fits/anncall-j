package anncallj.processor;

public class Callers {
	public static <S> Caller<S> caller(CallParser<S> parser, Class<?> cls) {
		return new BasicCaller<>(parser, cls);
	}

	public static <S> Caller<S> caller(CallParser<S> parser, Object obj) {
		return new BasicCaller<>(parser, obj);
	}

	public static Caller<String> regexCaller(Class<?> cls) {
		return new BasicCaller<>(new RegexCallParser(), cls);
	}

	public static Caller<String> regexCaller(Object obj) {
		return new BasicCaller<>(new RegexCallParser(), obj);
	}

	public static Caller<Object> ognlCaller(Class<?> cls) {
		return new BasicCaller<>(new OgnlCallParser(), cls);
	}

	public static Caller<Object> ognlCaller(Object obj) {
		return new BasicCaller<>(new OgnlCallParser(), obj);
	}
}
