package anncallj.processor;

import anncallj.annotation.Call;
import anncallj.processor.CallProcessor;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCallProcessor implements CallProcessor {

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
				Matcher m = match(callValue, ann);

				if (m.matches()) {
					result = invoke(method, m, obj);
					break;
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private <T> T invoke(Method method, Matcher m, Object obj) {
		try {
			return (T)method.invoke(obj, parseArgs(method, m));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object[] parseArgs(Method method, Matcher m) {
		Class<?>[] paramTypes = method.getParameterTypes();

		int size = Math.min(paramTypes.length, m.groupCount());
		
		Object[] result = new Object[size];

		for (int i = 0; i < result.length; i++) {
			result[i] = ConvertUtils.convert(m.group(i + 1), paramTypes[i]);
		}
		return result;
	}

	private Matcher match(String callValue, Call ann) {
		Pattern ptn = Pattern.compile(ann.value());
		return ptn.matcher(callValue);
	}
}
