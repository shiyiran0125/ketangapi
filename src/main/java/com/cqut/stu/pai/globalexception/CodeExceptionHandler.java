package com.cqut.stu.pai.globalexception;
import com.cqut.stu.pai.entity.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 石益然
 * @program: ketangpai
 * @description: 这是一个处理全局HTTPcode的类
 * @date 2020-11-11 11:20:00
 */
@RestControllerAdvice
public class CodeExceptionHandler {
    //日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(CodeExceptionHandler.class);

    /**
     * @param e
     * @return com.cqut.stu.ketangpai.entity.JsonData
     * @describe: 处理缺少请求参数错误
     * @date 2020/11/11 11:36
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonData handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        logger.error("缺少请求参数",e);
        return new JsonData(400,null,e.getMessage());
    }

    /**
     * @param e
     * @return com.cqut.stu.ketangpai.entity.JsonData
     * @describe: 
     * @date 2020/11/11 11:42
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonData handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        logger.error("缺少请求参数",e);
        return new JsonData(400,null,e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonData handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error("参数验证失败",e);
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String field = fieldError.getField();
        String desc = fieldError.getDefaultMessage();
        String message = String.format("%s%s",field,desc);
        return new JsonData(400,null,message);
    }

    /**
     * @param e
     * @return com.cqut.stu.ketangpai.entity.JsonData
     * @describe: 405 - Method Not Allowed
     * @date 2020/11/11 12:03
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonData handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        logger.error("不支持当前请求方法",e);
        return new JsonData(405,null,e.getMessage());
    }

    /**
     * @param e
     * @return com.cqut.stu.ketangpai.entity.JsonData
     * @describe: 415 - Unsupported Media Type
     * @date 2020/11/11 12:03
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonData handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        logger.error("不支持当前媒体类型",e);
        return new JsonData(415,null,e.getMessage());
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(Exception.class)
    public JsonData defaultErrorHandler(HttpMediaTypeNotSupportedException e){
        logger.error("服务器端异常",e);
        return new JsonData(500,null,e.getMessage());
    }
}