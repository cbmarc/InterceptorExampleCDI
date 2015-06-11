package org.smallpawn.example;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@MessageBinding @Interceptor
public class MessageInterceptor {

    private Logger logger = Logger.getLogger(MessageInterceptor.class.getName());

    @AroundInvoke
    public Object checkMessageLengthIsOk(InvocationContext context) throws Exception {
        logger.info("INTERCEPTOR checkMessageLengthIsOk invoked");
        Object[] params = context.getParameters();
        String message = (String) params[0];
        // Here we could even modify parameters passed to the method
        try {
            // Check message length, if 75 or less proceed, otherwise return null (aka. stop method execution)
            return message.length() <= 75 ? context.proceed() : null;
        } catch (Exception e) {
            logger.warning("Error calling context.proceed() in checkMessageLengthIsOk() interceptor method.");
            return null;
        }
    }

}
