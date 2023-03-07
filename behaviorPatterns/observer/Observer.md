# Observer

Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any event that happen to the object they're observing.

## Problem

You need to define a one to many dependency between objects so that once an object changes its state. all of it dependents are notified and updated automatically.

## Solution

A 'subject' contains a list of 'observers' to notify of any change in its state, so it should provide methods using which observers can register and unregister themselves. It contains a method to notify observers.

* Class Diagram

![Observer Class Diagram](/behaviorPatterns/observer/observer.svg)

## Pros and Cons

| Pros | Cons |
|---------------------|---------------------|
|You can introduce new subscriber classes without having to change the publisher's code.|Subscribers are notified in random order.|
|You can establish relations between objects at runtime.||

## Known uses

* It is heavily used in GUI toolkits and event listeners. In java the button(subject) and onClickListener(observer) are modeled with observer pattern.
* Social media, RSSS feeds, email subscription in which you have the option to follow or subscribe and you receive the latest notification.
* All users of an app on the Play Store get notified if there is an update.

## Example

An implementation of this pattern is YouTube, which notifies all channel subscribers when a new video is posted.

[Source code](/behaviorPatterns/observer/src/)

```java
import java.util.ArrayList;

public class YoutubeChannel {
    private ArrayList<Subscriber> subscriberList;
    private ArrayList<String> videoTitles;

    public YoutubeChannel() {
        this.subscriberList = new ArrayList<>();
        this.videoTitles = new ArrayList<>();
    }

    public void attachSubscribers(Subscriber subscriber){
        subscriberList.add(subscriber);
    }

    public void addVideo(String title){
        videoTitles.add(title); //updating the videoTitle field
        //broadcast to all subscribers
        broadcastToSubscribers();
    }

    public void broadcastToSubscribers(){
        for (Subscriber sub : subscriberList) {
            sub.getUpdate();
        }
    }

    public ArrayList<String> getVideoTitles() {
        return this.videoTitles;
    }
}
```

```java
public interface Subscriber {
    void getUpdate();
}
```

```java
import java.util.ArrayList;
import java.util.Arrays;

public class GenericSubscriber implements Subscriber {
    private YoutubeChannel youTubeChannel;

    public GenericSubscriber(YoutubeChannel youTubeChannel) {
        this.youTubeChannel = youTubeChannel;
        //when ever new subscriber object is being created
        // it's reference is collected by the youtube channel
        this.youTubeChannel.attachSubscribers(this);
    }

    @Override
    public void getUpdate() {
        ArrayList<String> videoTitles = this.youTubeChannel.getVideoTitles();
        System.out.println(this.getClass().getSimpleName()+" :> "+Arrays.toString(videoTitles.toArray()));

    }
}
```

```java
public class YouTubeApp {
    public static void main(String[] args) {
        YoutubeChannel youTubeChannel = new YoutubeChannel();
        new GenericSubscriber(youTubeChannel);
        youTubeChannel.addVideo("Hello World in Java");
        youTubeChannel.addVideo("Spring boot introduction");
    }
}
```
