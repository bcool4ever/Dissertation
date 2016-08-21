package data;

/**
 * Created by OLAYIWOLA on 06/08/2016.
 */
public class ratingData {
    private String title;
    private float value;

    public ratingData(){
    }

    public ratingData(String title, float value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
