package anncallj.processor;

public class Callers {
	public static <S> Caller<S> caller(CallParser<S> parser, Class<?>... clsList) {
		return new BasicCaller<>(parser, createTarget(clsList));
	}

	public static <S> Caller<S> caller(CallParser<S> parser, Object... objList) {
		return new BasicCaller<>(parser, createTarget(objList));
	}

	public static Caller<String> regexCaller(Class<?>... clsList) {
		return new BasicCaller<>(new RegexCallParser(), createTarget(clsList));
	}

	public static Caller<String> regexCaller(Object... objList) {
		return new BasicCaller<>(new RegexCallParser(), createTarget(objList));
	}

	public static Caller<Object> ognlCaller(Class<?>... clsList) {
		return new BasicCaller<>(new OgnlCallParser(), createTarget(clsList));
	}

	public static Caller<Object> ognlCaller(Object... objList) {
		return new BasicCaller<>(new OgnlCallParser(), createTarget(objList));
	}


	private static CallTarget createTarget(Class<?>... clsList) {
		return new ObjectCallTarget(clsList);
	}

	private static CallTarget createTarget(Object... objList) {
		return new ObjectCallTarget(objList);
	}
}
