package play.modules.redis.generator;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

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
        	} else if ("firstParamName".equals(format)) {
        		String[] paramNames = getMethodParamNames(redisMethod.getRawMethod());
        		if (paramNames.length < 1)
        			throw new RuntimeException(String.format("Cannot get first parameter name for method: %s", redisMethod.getRawMethod().getName()));
        		
        		return paramNames[0];
        	} else if ("paramList".equals(format)) {
        		return renderParamList(redisMethod.getRawMethod(), false);
        	} else if ("paramListWithTypes".equals(format)) {
        		return renderParamList(redisMethod.getRawMethod(), true);
        	} else if ("returnType".equals(format)) {
        		return renderType(redisMethod.getRawMethod().getReturnType());
        	}
        } catch (NotFoundException e) {
        	throw new RuntimeException(e);
        }
        
        throw new RuntimeException(String.format("Unrecognized format: %s", format));
	}

	private String renderParamList(CtMethod method, boolean withTypes) throws NotFoundException {
		StringBuffer sb = new StringBuffer();
		String[] paramNames =getMethodParamNames(method);
		
		int numParams = 0;
		for (CtClass paramType : method.getParameterTypes()) {
			if (withTypes) sb.append(renderType(paramType)).append(' ');
				
			sb.append(paramNames[numParams]).append(",");
			numParams++;
		}
        
		// Remove last comma
		if (numParams > 0) sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	// See http://www.codeweblog.com/access-method-parameter-name/
    private String[] getMethodParamNames(CtMethod method) throws NotFoundException {
    	// Load the local variable table
        MethodInfo methodInfo = method.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null)
                throw new RuntimeException(String.format("Class %s does not contain local variable table information. (Try using javac option -g)",
                											method.getDeclaringClass().getName()));

        String[] paramNames = new String[method.getParameterTypes().length];
        int startVarPos = 0;
        if (!Modifier.isStatic(method.getModifiers())) {
        	
        	// Start examining variable names after the 'this' variable
        	for (int i = 0; i < attr.tableLength(); i++) {
        		if ("this".equals(attr.variableName(i))) {
        			startVarPos = i + 1;
        			break;
        		}
        	}
        }
        
        for (int varPos = 0; varPos < paramNames.length; varPos++)
        	paramNames[varPos] = attr.variableName(varPos + startVarPos);
        return paramNames;
}
	
	private String renderType(CtClass type) throws NotFoundException {
		String typeName = type.getName();
		return typeName.replaceAll("\\$", ".");
	}
	
}
