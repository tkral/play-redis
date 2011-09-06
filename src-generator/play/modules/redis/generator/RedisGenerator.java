package play.modules.redis.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import org.antlr.stringtemplate.NoIndentWriter;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

/**
 * Generator for the Redis class.
 * 
 * @author Tim Kral
 */
public class RedisGenerator {
	
    public static void main(String[] args) throws NotFoundException, IOException {
		
        StringTemplate template = new StringTemplateGroup("Redis", "src-generator", null).getInstanceOf("Redis");
        template.setAttributeRenderers(Collections.singletonMap(RedisMethod.class, new RedisMethodRenderer()));
        
        CtClass jedisClass = ClassPool.getDefault().get("redis.clients.jedis.Jedis");
        List<RedisMethod> redisMethods = new ArrayList<RedisMethod>();
        for (CtMethod jedisMethod : jedisClass.getMethods()) {
        	if (skipMethodGeneration(jedisMethod)) {
    			continue;
        	}
        	
        	redisMethods.add(new RedisMethod(jedisMethod));
        }
        
        Collections.sort(redisMethods);
        template.setAttribute("redisMethods", redisMethods);
        
        FileWriter fw = null;
        try {
        	fw = new FileWriter("src/play/modules/redis/Redis.java");
        	NoIndentWriter templateWriter = new NoIndentWriter(fw);
        	template.write(templateWriter);
        } finally {
        	if (fw != null) fw.close();
        }
	}

    private static final CtClass OBJECT_CLASS;
    static {
    	try {
    		OBJECT_CLASS = ClassPool.getDefault().get("java.lang.Object");
    	} catch (NotFoundException e) {
    		throw new RuntimeException(e);
    	}
    }
    
    private static boolean skipMethodGeneration(CtMethod method) {
    	// TODO: Better test for has method.
    	boolean inheritedFromObjectClass;
    	try {
    		inheritedFromObjectClass = 
    			OBJECT_CLASS.getMethod(method.getName(), method.getMethodInfo().getDescriptor()) != null;
    	} catch (NotFoundException e) {
    		inheritedFromObjectClass = false;
    	}
    	
    	return method.getMethodInfo().isConstructor() || !Modifier.isPublic(method.getModifiers()) ||
    	       "disconnect".equals(method.getName()) || "quit".equals(method.getName()) ||
    	       inheritedFromObjectClass;
    }
}