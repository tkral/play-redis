package play.modules.redis.generator;

import javassist.CtClass;
import javassist.NotFoundException;

import org.antlr.stringtemplate.AttributeRenderer;

/**
 * StringTemplate renderer for methods in the Redis
 * connector class.
 * 
 * @author Tim Kral
 */
public class RedisMethodRenderer implements AttributeRenderer {

	@Override
	public String toString(Object o) {
		return "";
	}

	@Override
	public String toString(Object o, String format) {
        RedisMethod redisMethod = (RedisMethod) o;
        
        try {
        	if ("name".equals(format)) {
        		return redisMethod.getRawMethod().getName();
        	} else if ("paramList".equals(format)) {
        		return renderParamList(redisMethod, false);
        	} else if ("paramListWithTypes".equals(format)) {
        		return renderParamList(redisMethod, true);
        	} else if ("returnType".equals(format)) {
        		return renderType(redisMethod.getRawMethod().getReturnType());
        	}
        } catch (NotFoundException e) {
        	throw new RuntimeException(e);
        }
        
        throw new RuntimeException("Unrecognized format: " + format);
	}

	private String renderParamList(RedisMethod redisMethod, boolean withTypes) throws NotFoundException {
		StringBuffer sb = new StringBuffer();
		int numParams = 0;
		for (CtClass paramType : redisMethod.getRawMethod().getParameterTypes()) {
			if (withTypes) sb.append(renderType(paramType)).append(' ');
				
			sb.append("arg").append(numParams).append(",");
			numParams++;
		}
        
		// Remove last comma
		if (numParams > 0) sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	
	private String renderType(CtClass type) throws NotFoundException {
		String typeName = type.getName();
		return typeName.replaceAll("\\$", ".");
	}
}
