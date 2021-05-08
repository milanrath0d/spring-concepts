package org.milan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * Logging Aspect
 *
 * @author Milan Rathod
 */
@Aspect
public class LoggingAspect {

    /*public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("Before Advice");
            proceedingJoinPoint.proceed();
            System.out.println("After Advice");
        } catch (Throwable e) {
            System.out.println("After Throwing");
        }
        System.out.println("Finally");
    }*/

    @AfterThrowing(pointcut = "execution(* org.milan.service..*Impl.*(..))", throwing = "ex")
    public void afterExceptionThrowing(JoinPoint joinPoint, RuntimeException ex) {
        System.out.println("string argument method called " + joinPoint.getArgs().toString());
        System.out.println("string Return method called " + ex.toString());
    }

    /*@Pointcut(value = "execution(public String get*(..))")
    public void allGetters(){

    }*/
	/*
	public void beforeAdvice(JoinPoint joinPoint){
		System.out.println(joinPoint.getTarget());
	}
	
	@AfterReturning(pointcut = "args(name)" , returning = "returnString")
	public void stringArgumentMethods(String name,String returnString){
		System.out.println("string argument method called "+name);
		System.out.println("string Return method called "+returnString);
	}
	
	/*@Pointcut(value = "within(org.milan.springAop.model.Circle)")
	public void allCircleMethods(){
		
	}
	*/

    public void test() {
        System.out.println("test method");
    }

}
