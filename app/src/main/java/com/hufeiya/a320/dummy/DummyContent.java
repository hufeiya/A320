package com.hufeiya.a320.dummy;

import com.hufeiya.a320.application.CustomApplication;
import com.hufeiya.a320.bean.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 */
public class DummyContent {

    private static String[] RAW = CustomApplication.getRAW();
    public static final List<Info> ITEMS = new ArrayList<Info>();
    private static List<Info> currentQueryResult;
    //public static final Map<Integer, Info> ITEM_MAP = new HashMap<Integer, Info>();
    private static final int COUNT = 1190;
    private static final int NULL_LIST = -1;
    private static final int NOT_FOUND = -2;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Info item) {
        ITEMS.add(item);
        //ITEM_MAP.put(item.getId(), item);
    }

    private static Info createDummyItem(int position) {
        int i = position * 10;
        return new Info(position,RAW[i],RAW[i+1],RAW[i+2],RAW[i+3],RAW[i+4],RAW[i+5]
                ,RAW[i+6],RAW[i+7],RAW[i+8],RAW[i+9]);
    }

    public static void removeById(int id){
        for(int i = 0 ;i < ITEMS.size();i++){
            if(ITEMS.get(i).getId() == id){
                ITEMS.remove(i);
                break;
            }
        }
    }
    /**  if the  currentQueryResult is null,return NULL_LIST  */
    public static int findIndexByIdInCurrentQueryResult(int id){
        if(currentQueryResult == null){
            return NULL_LIST;
        }
        for(int i = 0;i < currentQueryResult.size();i++){
            if(currentQueryResult.get(i).getId() == id){
                return i;
            }
        }
        return NOT_FOUND;
    }

    public static List<Info> getCurrentQueryResult() {
        return currentQueryResult;
    }

    public static void setCurrentQueryResult(List<Info> currentQueryResult) {
        DummyContent.currentQueryResult = currentQueryResult;
    }
}