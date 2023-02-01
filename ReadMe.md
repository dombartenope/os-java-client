# Set Up
- Make sure that you have Maven( `mvn --version` ) and Gradle( `gradle --version` ) available from your terminal
- From your machine, pull down the git repo of the OneSignal-Java-Client by running:
 > `git clone https://github.com/OneSignal/onesignal-java-client`
- Go into the directory you've just cloned and run `mvn clean install`
- Create a new project in your IDE of choice - **IntelliJ IDE is suggested here**
- Configure the new project to build with Gradle
- After building, go to build.gradle and add the following:
```
repositories {
    mavenCentral()     // Needed if the 'onesignal-java-client' jar has been published to maven central.
    mavenLocal()       // Needed if the 'onesignal-java-client' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "org.openapitools:onesignal-java-client:1.0.1"
  }
```
- You now want to sync the gradle by running the gradle file with the play button
- If it runs successfully, go to your main.java file and try running to see the "Hello World" print to the console
- If successful, replace logic in the file to match the following:
```
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
        //Change the below parameters depending on how you want to target users:
        notification.setIncludedSegments(Arrays.asList(new String[]{"Subscribed Users"}));
//        notification.setIncludePlayerIds(List.of("4fafcb30-d771-4893-a8e6-65096c42fa15"));
//        notification.setIncludeExternalUserIds(List.of("Dom123", "12345"));
//        notification.channelForExternalUserIds("push");
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
```
- Run the build to determine if you get a notification once connected with your app