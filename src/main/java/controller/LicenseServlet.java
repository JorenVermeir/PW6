package controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.LicensePlateDB;
import domain.AcceptType;
import domain.LicensePlate;
import org.apache.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/controller")
public class LicenseServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LicensePlateDB db;
    public LicenseServlet() {
        super();
        db = new LicensePlateDB();
    }
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "scan";
        }
        switch (action) {
            case "scan":
                doScan(request, response);
                break;
        }
    }

    public String toJSON (Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }

    public void sendObject(HttpServletResponse response, Object o){
        try{
            response.getWriter().write(toJSON(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doScan(HttpServletRequest request, HttpServletResponse response) {
        String plates = request.getParameter("plates");
        //System.out.println(plates);
        if (plates == null || "".equals(null)){
            return;
        }
        String[] platess = plates.split("-");
        //Arrays.asList(platess).forEach(s -> System.out.println(s));

        String[][] platesss = new String[10][2];
        int i= 0;
        for(String s : platess){
            String[] sl = s.split(" ");
            platesss[i][0] = sl[0];
            platesss[i][1] = sl[1];
            i++;
        }

        //Arrays.asList(platesss).forEach( a -> Arrays.asList(a).forEach(s -> System.out.println(s)));

        AcceptType returns = AcceptType.DENY;

        for(int j=0;j<10;j++){
            String plate = platesss[j][0];
            if(plate != null && db.contains(new LicensePlate(plate))){
                try{
                    double p = Double.parseDouble(platesss[j][1]);
                    if(p > 80){
                        returns = AcceptType.ACCEPT;
                    }else if(p > 50 && returns != AcceptType.ACCEPT){
                        returns = AcceptType.UNSURE;
                    }
                }catch (NumberFormatException e){

                }
            }
        }

        sendObject(response, returns);
    }

    public void doIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void doOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("db", db.getPersondb());
        request.getRequestDispatcher("index.html").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
