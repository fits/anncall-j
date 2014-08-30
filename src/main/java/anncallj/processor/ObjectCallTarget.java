package anncallj.processor;

import anncallj.annotation.Call;
import anncallj.data.Tuple3;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

public class ObjectCallTarget implements CallTarget {
	private List<Tuple3<Call, Method, Object>> list = new ArrayList<>();

	public ObjectCallTarget(Class<?>... clsList) {
		for (Class<?> cls : clsList) {
			for (Method method : cls.getMethods()) {
				addToList(method, null);
			}
		}
	}

	public ObjectCallTarget(Object... objList) {
		for (Object obj : objList) {
			for (Method method : obj.getClass().getMethods()) {
				addToList(method, obj);
			}
		}
	}

	@Override
	public Iterable<Tuple3<Call, Method, Object>> list() {
		return list;
	}

	private void addToList(Method method, Object obj) {
		Call ann = method.getAnnotation(Call.class);

		if (ann != null) {
			list.add(new Tuple3<>(ann, method, obj));
		}
	}
}
