package mobile.tracker.bribe.com.bribetracker.models.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mikheil on 10/1/2016.
 */

public class JsonQuestionResponseItem {
    @SerializedName("item_id")
    private int itemId;
    @SerializedName("option_id")
    private Integer optionId;
    private String value;

    public JsonQuestionResponseItem(int itemId, Integer optionId, String value) {
        this.itemId = itemId;
        this.optionId = optionId;
        this.value = value;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
