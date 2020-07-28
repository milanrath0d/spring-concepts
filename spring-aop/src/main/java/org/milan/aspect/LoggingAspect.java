package org.milan.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {

    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("Before Advice");
            proceedingJoinPoint.proceed();
            System.out.println("After Advice");
        } catch (Throwable e) {
            System.out.println("After Thrwoing");
        }
        System.out.println("Finally");
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
	
	@AfterThrowing(pointcut = "args(name)" , throwing = "ex")
	public void ExceptionArgumentMethods(String name,RuntimeException ex){
		System.out.println("string argument method called "+name);
		System.out.println("string Return method called "+ex.toString());
	}
	
	/*@Pointcut(value = "within(org.milan.springAop.model.Circle)")
	public void allCircleMethods(){
		
	}
	*/
    public void test() {
        System.out.println("test method");
    }

}
