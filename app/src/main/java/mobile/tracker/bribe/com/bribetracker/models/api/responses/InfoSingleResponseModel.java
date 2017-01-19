package mobile.tracker.bribe.com.bribetracker.models.api.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import mobile.tracker.bribe.com.bribetracker.helpers.JsonUtils;

/**
 * Created by Mikheil on 10/3/2016.
 */

public class InfoSingleResponseModel implements Serializable {
    @SerializedName("datetime")
    private String dateTime;
    @SerializedName("response_id")
    private int responseId;

    private List<InfoResultItemModel> items;

    public String getDateTime() {
        return dateTime;
    }

    public String getFormattedDate(){
        return JsonUtils.getFormattedDate(getDateTime());
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public List<InfoResultItemModel> getItems() {
        return items;
    }

    public void setItems(List<InfoResultItemModel> items) {
        this.items = items;
    }
}
