package controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.LicensePlateDBInMemory;
import domain.AcceptType;
import domain.LicensePlateResult;
import domain.Person;
import db.PersonLicensePlateDB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/controller")
public class LicenseServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonLicensePlateDB db;
    public LicenseServlet() {
        super();
        db = new LicensePlateDBInMemory();
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
        AcceptType returns = AcceptType.DENY;
        if (plates == null || "".equals(null)){
            sendObject(response, returns);
            return;
        }
        List<LicensePlateResult> result = Arrays.asList(plates.split("-")).stream().map(s -> new LicensePlateResult(s)).collect(Collectors.toList());

        LicensePlateResult match = result.stream().filter(r -> r.getPrecent() >= 80 && db.contains(r.getPlate())).findFirst().orElse(null);

        if(match != null){
            Person p = db.getPersonWithPlate(match.getPlate());
            p.setPlateinside(p.getPlateinside() == null ? match.getPlate() : null);
            System.out.println(p);
            returns = AcceptType.ACCEPT;
        }else if(result.stream().anyMatch(r -> r.getPrecent() >= 50 && r.getPrecent() < 80)){
            returns = AcceptType.UNSURE;
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
