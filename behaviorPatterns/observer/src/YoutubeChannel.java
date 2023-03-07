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
        for (Subscriber sub :
                subscriberList) {
            sub.getUpdate();
        }
    }

    public ArrayList<String> getVideoTitles() {
        return this.videoTitles;
    }
}
