package mobile.tracker.bribe.com.bribetracker.models.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikheil on 9/28/2016.
 */

public class JsonAnswerModel {
    private int id;
    private String label;
    private JsonAnswerSettingsModel settings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public JsonAnswerSettingsModel getSettings() {
        return settings;
    }

    public void setSettings(JsonAnswerSettingsModel settings) {
        this.settings = settings;
    }

    public static ArrayList<JsonAnswerModel> getAnswersByParentId(List<JsonAnswerModel> options, long parentId){
        ArrayList<JsonAnswerModel> filteredList = new ArrayList<>();
        for (JsonAnswerModel option : options) {
            if (option.getSettings().getParent_area_option_id() == parentId) {
                filteredList.add(option);
            }
        }
        return filteredList;
    }
}
