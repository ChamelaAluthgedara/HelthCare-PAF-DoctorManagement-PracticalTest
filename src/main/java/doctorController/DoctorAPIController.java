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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoctorAPIController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DoctorService d = new DoctorService();
		System.out.println("Servlet: " + d.getAllHospitals().get(0));
		
		Gson gson = new Gson();
		List<Object> hostpitals = new ArrayList<Object>();
		for( Integer i : d.getAllHospitals()) {
			hostpitals.add(i);
		}
		
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(hostpitals));
		out.flush();
		out.close();
		
		
		response.getWriter().print("Print hey: " + d.getAllHospitals()); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
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

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}
	
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
