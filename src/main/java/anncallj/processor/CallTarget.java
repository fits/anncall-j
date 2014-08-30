package anncallj.processor;

import anncallj.annotation.Call;
import anncallj.data.Tuple3;

import java.lang.reflect.Method;

public interface CallTarget {
	Iterable<Tuple3<Call, Method, Object>> list();
}
