package anncallj.processor;

import anncallj.annotation.Call;

import java.lang.reflect.Method;

public class BasicCaller<S> implements Caller<S> {
	private CallParser<S> parser;
	private Class<?> targetClass; // TODO: 複数化
	private Object targetObj;

	public BasicCaller(CallParser<S> parser, Class<?> targetClass) {
		this.parser = parser;
		this.targetClass = targetClass;
	}

	public BasicCaller(CallParser<S> parser, Object targetObj) {
		this.parser = parser;
		this.targetClass = targetObj.getClass();
		this.targetObj = targetObj;
	}

	@Override
	public <T> T call(S callValue) {
		T result = null;

		for (Method method : targetClass.getMethods()) {
			Call ann = method.getAnnotation(Call.class);

			if (ann != null) {
				Object[] params = parser.parse(callValue, ann, method.getParameterTypes());

				if (params != null) {
					result = invoke(method, params, targetObj);
					break;
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private <T> T invoke(Method method, Object[] params, Object obj) {
		try {
			return (T)method.invoke(obj, params);
		} catch (Exception e) {
            // TODO: ログ出力
			e.printStackTrace();
		}
		return null;
	}
}
