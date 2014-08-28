package anncallj.processor;

public interface Caller {
	/**
	 * 
	 * @param callValue
	 * @return
	 */
	<T> T call(String callValue);
}
