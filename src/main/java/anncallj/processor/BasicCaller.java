package anncallj.processor;

import anncallj.annotation.Call;
import anncallj.data.Tuple2;
import anncallj.data.Tuple3;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;

public class BasicCaller<S> implements Caller<S> {
	private Log log = LogFactory.getLog(BasicCaller.class);

	private CallParser<S> parser;
	private CallTarget callTarget;

	public BasicCaller(CallParser<S> parser, CallTarget callTarget) {
		this.parser = parser;
		this.callTarget = callTarget;
	}

	@Override
	public <T> T call(S callValue) {
		T result = null;

		for (Tuple3<Call, Method, Object> target : callTarget.list()) {
			Method method = target._2();

			Tuple2<Boolean, Object[]> params = parser.parse(callValue, target._1(), method.getParameterTypes());

			if (params._1()) {
				result = invoke(method, params._2(), target._3());
				break;
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private <T> T invoke(Method method, Object[] params, Object obj) {
		try {
			return (T)method.invoke(obj, params);
		} catch (Exception e) {
			log.error("failed call method", e);
		}
		return null;
	}
}
