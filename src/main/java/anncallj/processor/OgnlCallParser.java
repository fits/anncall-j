package anncallj.processor;

import anncallj.annotation.Call;

import org.apache.commons.ognl.Ognl;

public class OgnlCallParser implements CallParser<Object> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] parse(Object callValue, Call ann, Class<?>[] paramTypes) {
		try {
			if (isParameterType(callValue, paramTypes)) {
				boolean res = Ognl.getValue(ann.value(), callValue, Boolean.class);

				if (res) {
					return new Object[] { callValue };
				}
			}
		} catch (Exception e) {
			// TODO: write to log
			e.printStackTrace();
		}
		return null;
	}

	private boolean isParameterType(Object callValue, Class<?>[] paramTypes) {
		return paramTypes.length == 1 && paramTypes[0].isInstance(callValue);
	}
}
