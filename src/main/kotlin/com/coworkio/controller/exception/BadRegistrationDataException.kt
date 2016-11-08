package com.coworkio.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Can't create user with given credentials")
open class BadRegistrationDataException: RuntimeException() {

}