package play.modules.redis.generator;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * Model of a Redis method.  See RedisGenerator.
 * 
 * @author Tim Kral
 */
class RedisMethod implements Comparable<Object> {
	
	private static final CtClass SHARDED_JEDIS_CLASS;
	static {
		try {
			SHARDED_JEDIS_CLASS = ClassPool.getDefault().get("redis.clients.jedis.ShardedJedis");
		} catch (NotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private final CtMethod rawMethod;
	private boolean isAvailableForShards;
	
	RedisMethod(CtMethod rawMethod) throws NotFoundException {
		this.rawMethod = rawMethod;
		
		// TODO: Better test for has method. 
		try {
			this.isAvailableForShards =
				SHARDED_JEDIS_CLASS.getMethod(rawMethod.getName(), rawMethod.getMethodInfo().getDescriptor()) != null;
		} catch (NotFoundException e) {
			this.isAvailableForShards = false;
		}
		
	}
	
	public boolean getIsAvailableForShards() {
		return this.isAvailableForShards;
	}
	
	public boolean getIsVoidReturnType() throws NotFoundException {
		return rawMethod.getReturnType() == CtClass.voidType;
	}
	
	CtMethod getRawMethod() {
		return this.rawMethod;
	}

	@Override
	public int compareTo(Object o) {
		if (o == null) {
			throw new RuntimeException("Cannot compare " + RedisMethod.class.getName() + " to null object");
		} else if (!(o instanceof RedisMethod)) {
			throw new RuntimeException("Cannot compare " + RedisMethod.class.getName() + " to " + o.getClass().getName());
		}
		
		RedisMethod other = (RedisMethod) o;
		try {
			int methodNameCompare = getRawMethod().getName().compareTo(other.getRawMethod().getName());
			if (methodNameCompare != 0) {
				return methodNameCompare;
			} else {
				int numParamsCompare = Integer.valueOf(getRawMethod().getParameterTypes().length).compareTo(other.getRawMethod().getParameterTypes().length);
				if (numParamsCompare != 0) {
					return numParamsCompare;
				} else {
					return getRawMethod().getReturnType().getName().compareTo(getRawMethod().getReturnType().getName());
				}
			}
		} catch (NotFoundException e) {
			throw new RuntimeException("Error during compareTo", e);
		}
	}
}
