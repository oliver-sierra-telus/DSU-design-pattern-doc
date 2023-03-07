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
