package mobile.tracker.bribe.com.bribetracker.models.api.responses;

import java.util.List;

/**
 * Created by Mikheil on 10/3/2016.
 */

public class InfoResultModel {
    private List<InfoSingleResponseModel> data;

    public List<InfoSingleResponseModel> getData() {
        return data;
    }

    public void setData(List<InfoSingleResponseModel> data) {
        this.data = data;
    }
}
