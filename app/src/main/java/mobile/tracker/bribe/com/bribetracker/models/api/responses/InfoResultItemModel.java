package mobile.tracker.bribe.com.bribetracker.models.api.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mikheil on 10/3/2016.
 */

public class InfoResultItemModel  implements Serializable{
    @SerializedName("item_id")
    private int itemId;
    @SerializedName("item_label")
    private String label;
    @SerializedName("option_label")
    private String optionValue;
    @SerializedName("item_type")
    private String optionType;

    @SerializedName("value")
    private String value;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
