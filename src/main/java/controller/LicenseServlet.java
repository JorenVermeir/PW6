package controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.LicensePlateDBInMemory;
import domain.AcceptType;
import domain.LicensePlate;
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
            action = "overview";
        }
        switch (action) {
            case "scan":
                doScan(request, response);
                break;
            case "overview":
                doOverview(request, response);
                break;
            case "addpage":
                doAddPerson(request, response);
                break;
            case "add":
                doAdd(request, response);
                break;
            case "licenseAdd":
                doLicensePage(request, response);
                break;
            case "addlicense":
                addLicence(request, response);
                break;
            case "genplate":
                System.out.println("test");
                genPlate(request, response);
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
        String confidences = request.getParameter("confidences");

        AcceptType returns = AcceptType.DENY;
        if (plates == null || "".equals(null)){
            sendObject(response, returns);
            return;
        }

        //List<LicensePlateResult> result = Arrays.asList(plates.split("-")).stream().map(s -> new LicensePlateResult(s)).collect(Collectors.toList());

        List<LicensePlate> licenseList = Arrays.asList(plates.split("-")).stream().map(s -> new LicensePlate(s)).collect(Collectors.toList());
        List<String> confidenceList = Arrays.asList(confidences.split("-"));

        for (int i = 0; i<licenseList.size();i++){
            if(db.contains(licenseList.get(i))){
                if((new LicensePlateResult(" "  + confidenceList.get(i))).getPercent() >= 80){
                    returns = AcceptType.ACCEPT;
                    break;
                } else if((new LicensePlateResult(" "  + confidenceList.get(i))).getPercent() > 50){
                    returns = AcceptType.UNSURE;
                }
            }
        }
        sendObject(response, returns);
    }
    public void doOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        db.update();
        request.setAttribute("db", db.getPersondb());
        request.setAttribute("plates", db.getLicensePlates());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void doAddPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("adduser.jsp").forward(request, response);
    }

    public void doLicensePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rnumber = request.getParameter("rnumber");
        request.setAttribute("rnumber", rnumber);
        request.getRequestDispatcher("licenseadd.jsp").forward(request, response);

    }

    public void genPlate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String test = request.getParameter("gen");
        String url = "https://licenceplate.be/gen/";
        response.sendRedirect(url + test);

    }

    public void addLicence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String license = request.getParameter("license");
        String rnumber = request.getParameter("rnumber");
        LicensePlate l = new LicensePlate(license);
        Person p = (Person) db.getPersonWithRnumber(rnumber);
        if(p.getPlates().size()<3){
            db.addLicencePlateToPerson(p, l);
            doOverview(request, response);
        }else{
            request.setAttribute("error", "je hebt te veel nummerplaten bij deze gebruiker");
            doOverview(request, response);
        }
        //request.getRequestDispatcher("licenseAdd.jsp").forward(request, response);
    }

    public void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String rnumber = request.getParameter("rnumber");
        String plate = request.getParameter("plate");
        LicensePlate l = new LicensePlate(plate);

        Person p = new Person();
        p.setFirstName(firstname);
        p.setLastName(lastname);
        p.setrNumber(rnumber);
        p.addLicensePlate(l);

        db.addPerson(p);
        doOverview(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
