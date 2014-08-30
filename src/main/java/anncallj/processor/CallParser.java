package anncallj.processor;

import anncallj.annotation.Call;
import anncallj.data.Tuple2;

public interface CallParser<S> {
	/**
	 *
	 * @param callValue
	 * @param ann
	 * @param paramTypes
	 * @return
	 */
	Tuple2<Boolean, Object[]> parse(S callValue, Call ann, Class<?>[] paramTypes);
}
