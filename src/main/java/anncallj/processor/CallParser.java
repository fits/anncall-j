package anncallj.processor;

import anncallj.annotation.Call;

public interface CallParser<S> {
    /**
     *
     * @param callValue
     * @param ann
     * @param paramTypes
     * @return
     */
	Object[] parse(S callValue, Call ann, Class<?>[] paramTypes);
}
