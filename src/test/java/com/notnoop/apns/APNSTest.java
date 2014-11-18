package com.notnoop.apns;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.ArrayList; 

/**
 * Silly Tests
 */
public class APNSTest {

    @Test
    public void testInstances() {
        assertThat(APNS.newPayload(), isA(PayloadBuilder.class));
        assertThat(APNS.newService(), isA(ApnsServiceBuilder.class));
    }

    @Test
    public void payloadShouldGetNewInstances() {
        assertNotSame(APNS.newPayload(), APNS.newPayload());
    }

    @Test
    public void newServiceGetNewInstances() {
        assertNotSame(APNS.newService(), APNS.newService());
    }


    @Test
    @Ignore public void MY_TEST_FOR_ONE_DEVICE_WITH_CORRECT_CERTFIFICATE() {
ApnsService service =
    APNS.newService()
        .withCert("/Users/corinne/Documents/UPS_Documents/Certificates_Prod.p12", "password")
        .withProductionDestination()
        .withDelegate(new ApnsDelegate() {
            @Override
            public void messageSent(ApnsNotification message, boolean resent) {
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>messageSent");
            }

            @Override
            public void messageSendFailed(ApnsNotification message, Throwable e) {
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>messageSendFailed" + message.getDeviceToken().toString());
            }

            @Override
            public void connectionClosed(DeliveryError e, int messageIdentifier) {
                 System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>Closed: " + e);
            }

            @Override
            public void cacheLengthExceeded(int newCacheLength) {
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>cacheLengthExceeded");
            }

            @Override
            public void notificationsResent(int resendCount) {
                 System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>Retry: " + resendCount);
            }
        })
        .build();


        String payload = APNS.newPayload().alertBody("1.").build();
        ArrayList<String> mixedTokens = new ArrayList();
        //correct device
        mixedTokens.add("9d860e6ec706611db76afb419cee897ffb3af1317b8b90ded4196c9044e4087e");

        service.push(mixedTokens, payload);   

        // String payload2 = APNS.newPayload().alertBody("1. with BOTH IN FIRST TEST").build();
        // ArrayList<String> mixedTokens2 = new ArrayList();
        // mixedTokens2.add("54668ba370d79ad065de03d02627e8d035b2ce499ce5322fd1041de6c6c3d254");
        // mixedTokens2.add("9d860e6ec706611db76afb419cee897ffb3af1317b8b90ded4196c9044e4087e");

        // service.push(mixedTokens2, payload2); 


    }

        @Test
    public void MY_TEST_FOR_TWO_DEVICES_ONE_IS_WRONG_CERTFIFICATE() {
ApnsService service =
    APNS.newService()
        .withCert("/Users/corinne/Documents/UPS_Documents/Certificates_Prod.p12", "password")
        .withProductionDestination()
        .withDelegate(new ApnsDelegate() {
            @Override
            public void messageSent(ApnsNotification message, boolean resent) {
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>messageSent");
            }

            @Override
            public void messageSendFailed(ApnsNotification message, Throwable e) {
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>messageSendFailed" + message.getDeviceToken().toString());
            }

            @Override
            public void connectionClosed(DeliveryError e, int messageIdentifier) {
                 System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>Closed: " + e);
            }

            @Override
            public void cacheLengthExceeded(int newCacheLength) {
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>cacheLengthExceeded");
            }

            @Override
            public void notificationsResent(int resendCount) {
                 System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::>>>Retry: " + resendCount);
            }
        })
        .build();



        String payload2 = APNS.newPayload().alertBody("2.").build();
        ArrayList<String> mixedTokens2 = new ArrayList();
        mixedTokens2.add("54668ba370d79ad065de03d02627e8d035b2ce499ce5322fd1041de6c6c3d254");
        mixedTokens2.add("9d860e6ec706611db76afb419cee897ffb3af1317b8b90ded4196c9044e4087e");

        service.push(mixedTokens2, payload2);    

    }
}
