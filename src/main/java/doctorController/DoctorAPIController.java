package doctorController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


/**
 * Servlet implementation class DoctorAPIController
 */
@WebServlet("/doctorAPI")
public class DoctorAPIController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoctorAPIController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Getting Registered Hospitals
		DoctorService d = new DoctorService();
		Gson gson = new Gson();
		List<Object> hostpitals = new ArrayList<Object>();
		for( Integer i : d.getAllHospitals()) {
			hostpitals.add(i);
		}
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(hostpitals));
		out.flush();
		out.close(); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DoctorService d = new DoctorService();
		
		List<Object> doc = new ArrayList<>();
		
		Map<String, String> docDetails = getParasMap(request);
		
		doc.add(Integer.parseInt(docDetails.get("docID").toString()));
		doc.add(docDetails.get("docFname").toString());
		doc.add(docDetails.get("docLname").toString());
		doc.add(docDetails.get("docPosition").toString());
		doc.add(Double.parseDouble((docDetails.get("docFee").toString())));
		doc.add(Integer.parseInt(docDetails.get("mobileNo").toString()));
		doc.add(docDetails.get("docAddress").toString());
		doc.add(Integer.parseInt(docDetails.get("hosID").toString()));
		
		if(d.Create(doc))
		{
			response.getWriter().write("1"); 
		}else {
			response.getWriter().write("0"); 
		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DoctorService d = new DoctorService();
		List<Object> doc = new ArrayList<>();
		
		Map<String, String> docUpdateDetails = getParasMap(request);
		
		doc.add(Integer.parseInt(docUpdateDetails.get("docID").toString()));
		doc.add(docUpdateDetails.get("docFname").toString());
		doc.add(docUpdateDetails.get("docLname").toString());
		doc.add(docUpdateDetails.get("docPosition").toString());
		doc.add(Double.parseDouble((docUpdateDetails.get("docFee").toString())));
		doc.add(Integer.parseInt(docUpdateDetails.get("mobileNo").toString()));
		doc.add(docUpdateDetails.get("docAddress").toString());
		doc.add(Integer.parseInt(docUpdateDetails.get("hosID").toString()));
		
		System.out.println(doc.toString());
		d.Update(doc);
		response.getWriter().write("true"); 
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		
		
		DoctorService repo = new DoctorService();
		Map<String, String> docDetails = getParasMap(request);
		int deleteID = Integer.parseInt(docDetails.get("id").toString());
		
		System.out.println("I'm In Delete API: " + deleteID );
			repo.kill(deleteID);
			response.getWriter().write("true"); 
	}
	
	
	private static Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
