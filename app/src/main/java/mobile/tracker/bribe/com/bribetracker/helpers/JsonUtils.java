package mobile.tracker.bribe.com.bribetracker.helpers;

import android.app.Activity;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Mikheil on 8/3/2016.
 */
public class JsonUtils {
    public static String loadJSONFromAsset(Activity activity, String path) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

//    public static List<SimpleModel> getPropertyList(Activity activity, String filePath){
//        List<SimpleModel> propertyTypeList;
//        String json = JsonUtils.loadJSONFromAsset(activity, filePath);
//
//        Type listType = new TypeToken<List<SimpleModel>>() {
//        }.getType();
//
//        propertyTypeList = new Gson().fromJson(json, listType);
//        return propertyTypeList;
//    }
//
//    public static String[] getPropertyTypeNames(List<SimpleModel> propertyTypeList) {
//        String[] propertyTypeNames = new String[propertyTypeList.size()];
//        for (int i = 0; i < propertyTypeList.size(); i++) {
//            propertyTypeNames[i] = propertyTypeList.get(i).getName();
//        }
//        return propertyTypeNames;
//    }


    public static <T> String[] getPropertyTypeNamesByFIeld(List<T> propertyTypeList, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        String[] propertyTypeNames = new String[propertyTypeList.size()];
        for (int i = 0; i < propertyTypeList.size(); i++) {
//            propertyTypeNames[i] = propertyTypeList.get(i).getName();
            T obj = propertyTypeList.get(i);
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            propertyTypeNames[i] = field.get(obj)+"";
        }
        return propertyTypeNames;
    }

    public static String getFormattedDate(String dateString){
        String[] dates = dateString.split("T");
        if (dates.length > 0) {
            return dates[0];
        }
        return "";
    }
}
