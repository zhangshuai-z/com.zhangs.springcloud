import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

/**
 * @PROJECT_NAME: com.zhangs.springcloud
 * @DATE: 2022/8/2 9:37
 * @author: zhangs
 */
public class T1 {

    @Test
    public void TimeString() {
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println("dateTime = " + dateTime);
    }
}
