package data;

/**
 * Created by OLAYIWOLA on 26/07/2016.
 */
public class topicData {
    // private int topicID;
    private String topicName;
    // private String[] contents;
    // private int SubjectID;
    //private String topicAuthor;
    // private boolean completed;

    public topicData(String topicName) {
        this.topicName = topicName;

    }
    public topicData(){
        this.topicName = topicName;
    }


    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
