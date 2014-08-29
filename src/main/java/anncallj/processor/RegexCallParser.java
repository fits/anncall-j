package anncallj.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.ConvertUtils;

import anncallj.annotation.Call;

public class RegexCallParser implements CallParser<String> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] parse(String callValue, Call ann, Class<?>[] paramTypes) {
		Pattern ptn = Pattern.compile(ann.value());
		Matcher m =  ptn.matcher(callValue);
		
		return m.matches()? createParameters(m, paramTypes): null;
	}

	private Object[] createParameters(Matcher m, Class<?>[] paramTypes) {
		int size = Math.min(paramTypes.length, m.groupCount());
		
		Object[] result = new Object[size];

		for (int i = 0; i < result.length; i++) {
			result[i] = ConvertUtils.convert(m.group(i + 1), paramTypes[i]);
		}
		return result;
	}
}
