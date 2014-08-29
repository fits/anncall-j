package anncallj.processor;

public interface Caller<S> {
	/**
	 * 
	 * @param callValue
	 * @return
	 */
	<T> T call(S callValue);
}
