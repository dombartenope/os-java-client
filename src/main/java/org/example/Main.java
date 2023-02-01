package org.example;


// Import classes:
import com.onesignal.client.ApiClient;
import com.onesignal.client.ApiException;
import com.onesignal.client.Configuration;
import com.onesignal.client.auth.*;
import com.onesignal.client.model.*;
import com.onesignal.client.api.DefaultApi;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String appId = "APP ID";
    private static final String appKeyToken = "YOUR API KEY";
    private static final String userKeyToken = "YOUR_USER_TOKEN";

    private static Notification createNotification() {
        Notification notification = new Notification();
        notification.setAppId(appId);
        notification.setIsChrome(true);
        notification.setIsAnyWeb(true);
        notification.setIncludedSegments(Arrays.asList(new String[]{"Subscribed Users"}));
//        notification.setIncludePlayerIds(List.of("4fafcb30-d771-4893-a8e6-65096c42fa15"));
//        notification.setIncludeExternalUserIds(List.of("Dom123", "12345"));
        notification.channelForExternalUserIds("push");
        StringMap contentStringMap = new StringMap();
        contentStringMap.en("Test");
        notification.setContents(contentStringMap);

        return notification;
    }

    public static void main(String[] args) {
        // Setting up the client
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        HttpBearerAuth appKey = (HttpBearerAuth) defaultClient.getAuthentication("app_key");
        appKey.setBearerToken(appKeyToken);
        HttpBearerAuth userKey = (HttpBearerAuth) defaultClient.getAuthentication("user_key");
        userKey.setBearerToken(userKeyToken);
        DefaultApi api = new DefaultApi(defaultClient);

        // Setting up the notification
        Notification notification = createNotification();

        // Sending the request
        try {

            System.out.println("1st");
            CreateNotificationSuccessResponse response = api.createNotification(notification);
            System.out.println("hey");
            // Checking the result
            System.out.print(response);

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
