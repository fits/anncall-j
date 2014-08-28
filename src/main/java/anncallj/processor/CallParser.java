package anncallj.processor;

import anncallj.annotation.Call;

public interface CallParser {
	/**
	 * 
	 * @param callValue
	 * @param ann
	 * @param paramTypes
	 * @return
	 */
	Object[] parse(String callValue, Call ann, Class<?>[] paramTypes);
}
