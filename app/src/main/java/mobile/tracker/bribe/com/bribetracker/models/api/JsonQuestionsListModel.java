package mobile.tracker.bribe.com.bribetracker.models.api;

import java.util.List;

/**
 * Created by Mikheil on 10/1/2016.
 */

public class JsonQuestionsListModel {
    private List<JsonQuestionModel> items;

    public List<JsonQuestionModel> getItems() {
        return items;
    }

    public void setItems(List<JsonQuestionModel> items) {
        this.items = items;
    }


    public JsonQuestionModel getQuestionById(int questionId) {
        for (JsonQuestionModel item : items) {
            if (item.getId() == questionId) return item;
        }
        return null;
    }
}
