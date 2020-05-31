package de.paul.rest.error;

import de.paul.util.Log;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * author: JP
 * date: 31.05.20
 *
 * Preventing stack trace to go outside.
 */
@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    private static final Log log = Log.getLog(GeneralExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        log.error("unexpected error: " + e.getLocalizedMessage());
        return Response.serverError().build();
    }
}
