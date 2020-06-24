// package com.example.springdemo.common;

// import javax.validation.ConstraintViolationException;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.converter.HttpMessageNotReadableException;
// import org.springframework.validation.BindException;
// import org.springframework.web.HttpRequestMethodNotSupportedException;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.MissingServletRequestParameterException;
// import org.springframework.web.bind.ServletRequestBindingException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     /**
//      * 400 - Bad Request
//      */
//     @ResponseStatus(HttpStatus.BAD_REQUEST)
//     @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, BindException.class,
//             ServletRequestBindingException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
//     public Result handleHttpMessageNotReadableException(Exception e) {
//         System.out.println("参数解析失败 " + e);
//         if (e instanceof BindException){
//             //return new Result.error(ResultStatusCode.BAD_REQUEST.getCode(), ((BindException)e).getAllErrors().get(0).getDefaultMessage());
//             return Result.error(((BindException)e).getAllErrors().get(0).getDefaultMessage());
//         }
//         //return Result.error(ResultStatusCode.BAD_REQUEST.getCode(), e.getMessage());
//         return Result.error(e.getMessage());
//     }

//     /**
//      * 405 - Method Not Allowed
//      * 带有@ResponseStatus注解的异常类会被ResponseStatusExceptionResolver 解析
//      */
//     @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//     @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//     public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//         System.out.println("不支持当前请求方法 " + e);
//         //return new Result(ResultStatusCode.METHOD_NOT_ALLOWED, null);
//         return Result.error();
//     }

//     /**
//      * 其他全局异常在此捕获
//      * @param e
//      * @return
//      */
//     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//     @ExceptionHandler(Throwable.class)
//     public Result handleException(Throwable e) {
//         System.out.println("服务运行异常 " + e);
//         return Result.error();
//     }

// }