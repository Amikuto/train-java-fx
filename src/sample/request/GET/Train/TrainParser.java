package sample.request.GET.Train;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class TrainParser {

    private TrainGet trainGet;

    public void parse(String string){

        JSONArray obj = new JSONArray(string);
        System.out.println(obj.getJSONObject(0));

        JSONObject train = obj.getJSONObject(0);

        Long id = Long.parseLong(train.get("id").toString());
        System.out.println(id);

        LocalTime timeDep = LocalTime.parse(train.getString("time_dep"));
        System.out.println(timeDep);

        LocalTime timeArr = LocalTime.parse(train.getString("time_arr"));
        System.out.println(timeArr);

        LocalDate dateDep = LocalDate.parse(train.getString("date_dep"));
        System.out.println(dateDep);

        LocalDate dateArr = LocalDate.parse(train.getString("date_arr"));
        System.out.println(dateArr);

//        for (int i=0;i<obj.length();i++) {
//            System.out.println(obj.getJSONObject(i));
//        }
    }
}
