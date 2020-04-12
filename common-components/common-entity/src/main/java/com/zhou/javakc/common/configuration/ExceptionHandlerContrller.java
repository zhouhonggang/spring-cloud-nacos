package com.zhou.javakc.common.configuration;

import com.zhou.javakc.common.entity.base.error.ErrorMessage;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共组件(错误校验提示信息)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@RestControllerAdvice
public class ExceptionHandlerContrller {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public List<ErrorMessage> exception(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<ErrorMessage> errorMsgs = new ArrayList<>();

        allErrors.forEach(objectError -> {
            ErrorMessage errorMessage = new ErrorMessage();
            FieldError fieldError = (FieldError)objectError;
            errorMessage.setField(fieldError.getField());
            errorMessage.setObjectName(fieldError.getObjectName());
            errorMessage.setMessage(fieldError.getDefaultMessage());
            errorMsgs.add(errorMessage);
        });
        return errorMsgs;
    }

}
