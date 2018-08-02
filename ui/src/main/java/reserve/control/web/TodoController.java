package reserve.control.web;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reserve.control.domain.*;

import java.util.ArrayList;

@Controller
public class TodoController {
    @Autowired
    private TodoRepository repository;


    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }		

	// Show all todos
    @RequestMapping(value="/employees")
    public String todoList(Model model) {
        JSONArray jArr = new JSONArray("[{\"first_name\":\"dmitry\", \"last_name\":\"kobzev\", \"age\":\"18\"}," +
                "{\"first_name\":\"egor\", \"last_name\":\"pravdin\", \"age\":\"17\"}," +
                "{\"first_name\":\"alex\", \"last_name\":\"demchenko\", \"age\":\"17\"}]");
        ArrayList<JSONObject> jList = new ArrayList<>();
        for(int i = 0; i < jArr.length(); i++)
            jList.add(jArr.getJSONObject(i));
        model.addAttribute("employeeList", jList);
        return "employees";
    }



}
