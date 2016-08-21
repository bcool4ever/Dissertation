package data;

import com.shaded.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by OLAYIWOLA on 26/07/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class subjectData {
    private String Title;

    public subjectData() {
    }

    public subjectData(String title) {
        this.setTitle(title);
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }
}
