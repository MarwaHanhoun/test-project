package com.airretailer.advice;

import com.airretailer.advice.AppError;
import com.airretailer.exptions.NoSeatAvailable;
import com.airretailer.exptions.SeatAvailabilityNotExist;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.airretailer")
public class ErrorHandling extends ResponseEntityExceptionHandler  {
    @ExceptionHandler(NoSeatAvailable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody

    public AppError handleSeatNotAvailable(NoSeatAvailable ex){
        return new AppError("404","No Seat Available");
    }

    @ExceptionHandler(SeatAvailabilityNotExist.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    AppError handleSeatAvailabilityNotExist(){
        return new AppError("404","This Seat is not available");
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    AppError handle(){
        return new AppError("1","111");
    }
}
