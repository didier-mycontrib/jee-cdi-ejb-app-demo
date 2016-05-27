package tp.myapp.minibank.core.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


/*
 utilisation:
 
 
@Stateless
@Interceptors({ExecutionTimeInterceptor.class, AnotherInterceptor.class})
public class GestionComptesImpl {
	...
}   

NB : @Interceptors({ExecutionTimeInterceptor.class, AnotherInterceptor.class}) peut être placé au dessus d'une ou plusieurs méthode(s) d'un EJB
 
 

OU BIEN (dans META-INF/ejb-jar.xml):
 
 
 <assembly-descriptor>
   <!-- Default interceptor that will apply to all methods for all beans in deployment -->
   <interceptor-binding>
      <ejb-name>*</ejb-name>
      <interceptor-class>tp.myapp.minibank.core.interceptors.ExecutionTimeInterceptor</interceptor-class>
      <interceptor-class>tp.myapp.minibank.core.interceptors.AnotherInterceptor</interceptor-class>
   </interceptor-binding>
   ...
</assembly-descriptor>

sachant qu'il existe @ExcludeDefaultInterceptors à placer sur un EJB (ex: stateless)
 
 */

public class ExecutionTimeInterceptor {
	
	@AroundInvoke
    public Object timeLogging(InvocationContext context) throws Exception{
        //System.out.println("**** " + context.getMethod());
        long td = System.nanoTime();
        	Object res=  context.proceed();
        long tf = System.nanoTime();
        //System.out.println("**** " + context.getMethod() + " avec temps d'execution (ms)= " + (tf-td)/1000000);
        System.out.println("**** " + context.getMethod().getName() + " avec temps d'execution (ms)= " + (tf-td)/1000000);
        return res;
    }

}
