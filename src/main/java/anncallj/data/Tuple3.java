package anncallj.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Tuple3<F, S, T> extends Tuple2<F, S> {
	private T third;

	public Tuple3(F first, S second, T third) {
		super(first, second);
		this.third = third;
	}

	public T _3() {
		return third;
	}
/*
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	*/
}
