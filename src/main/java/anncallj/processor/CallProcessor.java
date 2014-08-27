package anncallj.processor;

public interface CallProcessor {
	/**
	 * 
	 * @param callValue
	 * @param cls
	 * @return
	 */
	<T> T call(String callValue, Class<?> cls);

	/**
	 * 
	 * @param callValue
	 * @param obj
	 * @return
	 */
	<S, T> T call(String callValue, S obj);
}
