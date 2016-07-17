package com.taoswork.tallycheck.authentication.servo;

import com.taoswork.tallycheck.servo.DemoServo;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by gaoyuan on 7/15/16.
 */
public class DemoDebugServoTest {
    // mvn -Dmaven.surefire.debug="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5006 -Xnoagent -Djava.compiler=NONE" test
    @Test
    public void runAsMain() throws IOException {

        String configXml = "META-INF/spring/dubbo-tallyuser-authentication-servo.xml";

        DemoServo.runServo(configXml);
    }

}
