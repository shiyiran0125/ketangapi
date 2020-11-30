package com.cqut.stu.pai.config;

import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.entity.response.LogVo;
import com.cqut.stu.pai.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 石益然
 * @program: pai
 * @description: 日志记录
 * @date 2020-11-24 14:40:15
 */
@Aspect
@Component
public class StudentLog {
    @Autowired
    LogService logService;
    private final static Logger logger = LoggerFactory.getLogger(StudentLog.class);

    /**
     * 定义切面
     */
    @Pointcut("execution(* com.cqut.stu.pai.controller.StudentController.*(..))")
    public void controllerLog() {
    }

    @Before(value = "controllerLog()")
    public void doBeforeController(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String format = sdf.format(new Date());
        String ip = request.getRemoteAddr();
        String url = request.getRequestURI();
        String methodType = request.getMethod();

        //打印日志
        logger.debug("--------------------------------------------student-------------------------------------------------");
        logger.debug("时间 = {}", format);
        logger.debug("访问url = {}", url);
        logger.debug("请求方法类型 = {}", methodType);
        logger.debug("来源ip地址 = {}", ip);
        logger.debug("调用方法 = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.debug("-----------------------------------------------------------------------------------------------------");
    }

    @AfterReturning(value = "controllerLog()")
    public void doAfterReturning(JoinPoint joinPoint){
        Student user = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LogVo logVo = new LogVo(user.getUsername(),new Date(),joinPoint.getSignature().getName(),true);
        logService.studentLog(logVo);
    }

    @AfterThrowing(value = "controllerLog()")
    public void doAfterThrowing(JoinPoint joinPoint){
        Student user = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LogVo logVo = new LogVo(user.getUsername(),new Date(),joinPoint.getSignature().getName(),false);
        logService.studentLog(logVo);
    }

    @Around(value = "controllerLog()")
    public Object doAfterController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        logger.debug("执行时间 = {}", time + "ms");
        logger.info("执行时间 = {}", time + "ms");
        return object;
    }
}
