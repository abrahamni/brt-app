package mobile.tracker.bribe.com.bribetracker.models.api;

import java.util.List;

/**
 * Created by Mikheil on 9/28/2016.
 */

public class JsonQuestionModel {
    private long id;
    private int section_id;
    private String label;
    private List<JsonAnswerModel> options;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<JsonAnswerModel> getOptions() {
        return options;
    }

    public void setOptions(List<JsonAnswerModel> options) {
        this.options = options;
    }

    public String[] getAnswersArray(){
        String[] list = new String[options.size()];

        for (int i = 0; i < options.size(); i++) {
            list[i] = options.get(i).getLabel();
        }
        return list;
    }


    public static String[] toAnswersArray(List<JsonAnswerModel> options){
        String[] list = new String[options.size()];

        for (int i = 0; i < options.size(); i++) {
            list[i] = options.get(i).getLabel();
        }
        return list;
    }
}
