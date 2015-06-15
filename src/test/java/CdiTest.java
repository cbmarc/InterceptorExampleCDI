import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.smallpawn.example.MessageInterceptor;
import org.smallpawn.example.MessageManager;

import javax.inject.Inject;

@RunWith(CdiRunner.class)
@AdditionalClasses(MessageInterceptor.class)
public class CdiTest {

    @Inject
    MessageManager messageManager;

    @Test
    public void cdiTest() {
        String msg1 = "This is a message that should be printed, because it is 75 characters long";
        String msg2 = "This is a message that should NOT be printed, because it is more than 75 characters long";
        String msg3 = "This is a message that should be printed";
        String result1 = messageManager.prepareMessage(msg1);
        String result2 = messageManager.prepareMessage(msg2);
        String result3 = messageManager.prepareMessage(msg3);
        Assert.assertEquals(msg1.toLowerCase(), result1);
        Assert.assertEquals(null, result2);
        Assert.assertEquals(msg3.toLowerCase(), result3);
    }
}
