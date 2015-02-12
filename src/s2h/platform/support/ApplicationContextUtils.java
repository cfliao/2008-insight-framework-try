package s2h.platform.support;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtils
{
    public static ApplicationContext getApplicationContext(Class<?> clazz, String[] configFiles)
    {
        ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        ApplicationContext ctx = null;
        Thread.currentThread().setContextClassLoader(clazz.getClassLoader());
        ctx =  new ClassPathXmlApplicationContext(configFiles);      
        Thread.currentThread().setContextClassLoader(oldLoader);
        return ctx;
    }
}
