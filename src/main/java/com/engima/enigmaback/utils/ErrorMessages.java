package com.engima.enigmaback.utils;

import com.engima.enigmaback.exceptions.ApiException;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;

public interface ErrorMessages {
    public static final String MSG_PRESTAMO_INVITADO="El usuario con identificación {} ya tiene un libro prestado por lo cual no se puede realizar otro préstamo";
    public static final String MSG_TIPO_USUARIO="Tipo de usuario no permitido en la biblioteca";

    public static final String MSG_NO_SE_ENCUENTRA="No se encuentra el registro";

    public static ApiException get(HttpStatus code, String message, Object... params) {
        if (params != null && params.length > 0) {
            message = MessageFormatter.arrayFormat(message, params).getMessage();
        }
        return new ApiException(code,message);
    }
}
