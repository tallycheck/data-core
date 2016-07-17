package com.taoswork.tallycheck.tallyadmin.authority;

import com.taoswork.tallycheck.servo.DemoServo;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by gaoyuan on 7/15/16.
 */
public class AuthorityDemoServoTest {
    // mvn -Dmaven.surefire.debug="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5007 -Xnoagent -Djava.compiler=NONE" test
    @Test
    public void runAsMain() throws IOException {
        String confXml = "META-INF/spring/dubbo-tallyadmin-authority-servo.xml";
        DemoServo.runServo(confXml);
    }
}
