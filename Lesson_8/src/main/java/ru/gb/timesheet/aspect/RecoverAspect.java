package ru.gb.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class RecoverAspect {

  @Pointcut("@annotation(ru.gb.timesheet.aspect.Recover)") // method
  public void recoverMethodsPointcut() {}

  @Around(value = "recoverMethodsPointcut()")
  public Object aroundTimesheetServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
    try {
      return pjp.proceed();
    } catch (Exception e) {
      log.info("Recovering TimesheetService#{} after Exception [{}], exception message [{}]", pjp.getSignature().getName(), e.getClass(), e.getMessage());
      Signature signature = pjp.getSignature();
      Class returnType = ((MethodSignature) signature).getReturnType();
      if (WRAPPER_TYPE_MAP.containsKey(returnType) || WRAPPER_TYPE_MAP.containsValue(returnType)) {
        return 0;
      } else if (void.class.equals(returnType)) {
        return void.class;
      }
      return null;
    }
  }

  private static final Map<Class<?>, Class<?>> WRAPPER_TYPE_MAP;
  static {
    WRAPPER_TYPE_MAP = new HashMap<Class<?>, Class<?>>();
    WRAPPER_TYPE_MAP.put(Integer.class, int.class);
    WRAPPER_TYPE_MAP.put(Byte.class, byte.class);
    WRAPPER_TYPE_MAP.put(Character.class, char.class);
    WRAPPER_TYPE_MAP.put(Boolean.class, boolean.class);
    WRAPPER_TYPE_MAP.put(Double.class, double.class);
    WRAPPER_TYPE_MAP.put(Float.class, float.class);
    WRAPPER_TYPE_MAP.put(Long.class, long.class);
    WRAPPER_TYPE_MAP.put(Short.class, short.class);
  }

}
