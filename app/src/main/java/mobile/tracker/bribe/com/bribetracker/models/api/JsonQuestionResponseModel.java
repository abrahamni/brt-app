package mobile.tracker.bribe.com.bribetracker.models.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikheil on 10/1/2016.
 */

public class JsonQuestionResponseModel {
    @SerializedName("form_id")
    private int formId;
    private String lang;
    private List<JsonQuestionResponseItem> items;

    public JsonQuestionResponseModel(int formId) {
        this.formId = formId;
        items = new ArrayList<>();
        lang = "fa";
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public List<JsonQuestionResponseItem> getItems() {
        return items;
    }

    public void setItems(List<JsonQuestionResponseItem> items) {
        this.items = items;
    }
}
