package anncallj.processor;

import anncallj.annotation.Call;

import anncallj.data.Tuple2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.ognl.Ognl;

public class OgnlCallParser implements CallParser<Object> {
	private Log log = LogFactory.getLog(OgnlCallParser.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tuple2<Boolean, Object[]> parse(Object callValue, Call ann, Class<?>[] paramTypes) {
		try {
			if (isParameterType(callValue, paramTypes)) {
				boolean res = Ognl.getValue(ann.value(), callValue, Boolean.class);

				return new Tuple2<>(res, new Object[] { callValue });
			}
		} catch (Exception e) {
			log.error("failed ognl", e);
		}
		return new Tuple2<>(false, null);
	}

	private boolean isParameterType(Object callValue, Class<?>[] paramTypes) {
		return paramTypes.length == 1 && paramTypes[0].isInstance(callValue);
	}
}
