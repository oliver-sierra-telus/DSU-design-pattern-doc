public class YouTubeApp {
    public static void main(String[] args) {
        YoutubeChannel youTubeChannel = new YoutubeChannel();
        new GenericSubscriber(youTubeChannel);
        youTubeChannel.addVideo("Hello World in Java"); 
        youTubeChannel.addVideo("Spring boot introduction"); 
    }
}
