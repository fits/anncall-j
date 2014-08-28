package anncallj.processor;

import anncallj.annotation.Call;
import anncallj.processor.CallProcessor;

import java.lang.reflect.Method;

public class RegexCallProcessor implements CallProcessor {
	private CallParser parser;

	public RegexCallProcessor(CallParser parser) {
		this.parser = parser;
	}

	@Override
	public <T> T call(String callValue, Class<?> cls) {
		return callMethod(callValue, cls, null);
	}

	@Override
	public <S, T> T call(String callValue, S obj) {
		return callMethod(callValue, obj.getClass(), obj);
	}

	private <S, T> T callMethod(String callValue, Class<?> cls, S obj) {
		T result = null;

		for (Method method : cls.getMethods()) {
			Call ann = method.getAnnotation(Call.class);

			if (ann != null) {
				Object[] params = parser.parse(callValue, ann, method.getParameterTypes());
				
				if (params != null) {
					result = invoke(method, params, obj);
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
			e.printStackTrace();
		}
		return null;
	}
}
