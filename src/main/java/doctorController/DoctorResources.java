package doctorController;


import java.util.List;
/*import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;*/
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("doctors")
public class DoctorResources {

	DoctorService repo = new DoctorService();
	Doctor beforeUpdate;
	List<String> changes;
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Doctor> getDoctors() 
	{
		System.out.println(repo.getDoctors().size());
		return repo.getDoctors();	
	}
	
	
	@GET
	@Path ("doctor/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Doctor getDoctor(@PathParam("id")int id) 
	{
		
		System.out.println("Single fdoctor api Clled.. ");
//		if(repo.getDoctor(id).getDocID() == 0) {
//			return "Invalid DoctorID.";	
//		}else {
//			return repo.getDoctor(id).toString();	
//		}
		return repo.getDoctor(id);
	}
	
	
//	@POST
//	@Path("doctor")
//	@Consumes({MediaType.APPLICATION_XML,MediaType.TEXT_HTML,MediaType.APPLICATION_JSON,MediaType.MULTIPART_FORM_DATA})
//	public String createDoctor(Doctor d1)
//	{
//		System.out.println("POST Called....");
//		
//		if(d1.getDocFName().equals(null) || d1.getDocLName().equals(null) || d1.getDocAddress().equals(null) || d1.getDocFee() == 0 || d1.getDocID() == 0|| d1.getDocPosition().equals(null) || d1.getMobileNo() == 0 || d1.getHosID() == 0) {
//			return "Error. Values cannot be null.";
//		}
//		else
//		{
//			System.out.println(d1);
//			
//			if(repo.Create(d1).equals("true"))
//			{
//				
//				return "New data Successfully Inserted." + "\nDetails: " + d1;
//			}
//			if(repo.Create(d1).equals("InvalidhosID"))
//			{
//				
//				return  "Invalid. Unregistered HospitalID.";
//			}
//			else {
//				return "Task Failed. Input DocID already exist.";
//			}
//		}
//		
//	}
//	
//
//	@PUT
//	@Path("doctor")
//	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//	public String updateDoctor(Doctor d1)
//	{
//		 beforeUpdate = repo.getDoctor(d1.getDocID());
//		if(d1.getDocFName().equals(null) || d1.getDocLName().equals(null) || d1.getDocAddress().equals(null) || d1.getDocFee() == 0 || d1.getDocID() == 0|| d1.getDocPosition().equals(null) || d1.getMobileNo() == 0 || d1.getHosID() == 0) {
//			return "Error. Values cannot be null.";
//		}
//		else
//		{
//			System.out.println(d1);
//			if(repo.getDoctor(d1.getDocID()).getDocID() == 0) 
//			{
//				if(repo.Create(d1).equals("true"))
//				{
//					
//					return "Cannot update.Entered DocID not found in database. \nNew doctor deatils row created. \nInfo : " + d1;
//				}
//				if(repo.Create(d1).equals("InvalidhosID"))
//				{
//					
//					return  "Invalid. Unregistered HospitalID.";
//				}
//				else {
//					return "Data Update Task Failed.";
//				}
//			}
//			else
//			{
//				if(repo.Update(d1).equals("true"))
//				{
//					changes = new ArrayList<String>();
//					
//					updateTracker(d1);
//					
//					if(changes.size() == 0) {
//						return "No Changes Detected!";
//
//					}
//					else
//					{
//						return "Doctor details updated Successfully.\nAffected Summery = " + changes;
//					}
//				}
//				if(repo.Update(d1).equals("InvalidhosID"))
//				{
//					
//					return  "Cannot update. Unregistered HospitalID.";
//				}
//				else {
//					return "Update Task Faild.";
//				}
//
//			}
//		}
//		
//	}
//	
//	@DELETE
//	@Path ("doctor/{id}")
//	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//	public Doctor deleteDoctorDetails(@PathParam("id")int id) 
//	{
//		Doctor d = repo.getDoctor(id);
//		
////		if(d.getDocID()!=0) {
////			
////			if(repo.kill(id))
////			{
////				System.out.println("Deleted Data: " + d.toString());
////				return "Doctor details deleted Successfully. \nDeleted details: " + d;
////			}
////			else
////			{
////				return "Task Faild.";
////			}
////		}
////		else {
////			return "Please Insert Valid DocID.";
////		}
//		if(d.getDocID()!=0) {
//			repo.kill(id);
//			
//		}
//		return d;
//		
//	}
//	
//	public void updateTracker(Doctor d1)
//	{
//		
//		char op = '"';
//		
//		if( !beforeUpdate.getDocFName().equals(d1.getDocFName())) {
//			changes.add("\n" + "Doctor Firstname,\n " + op+beforeUpdate.getDocFName()+op + " Changed To " + op +d1.getDocFName()+op +"\n");
//		}
//		if(!beforeUpdate.getDocLName().equals(d1.getDocLName())) {
//			changes.add("\n" + "Doctor Lastname,\n " + op+beforeUpdate.getDocLName()+op + " Changed To " + op +d1.getDocLName()+op +"\n");
//		}
//		if(!beforeUpdate.getDocPosition().equals(d1.getDocPosition())) {
//			changes.add("\n" + "Doctor Position,\n " + op+beforeUpdate.getDocPosition()+op + " Changed To " + op +d1.getDocPosition()+op +"\n");
//		}
//		if(beforeUpdate.getDocFee() != d1.getDocFee()) {
//			changes.add("\n" + "Doctor Fee,\n Rs." + op+beforeUpdate.getDocFee()+op + " Changed To " + "Rs."+ op +d1.getDocFee()+op +"\n");
//		}
//		if(beforeUpdate.getMobileNo() != d1.getMobileNo()) {
//			changes.add("\n" + "Doctor Mobile No,\n " + op+beforeUpdate.getMobileNo()+op + " Changed To " + op +d1.getMobileNo()+op +"\n");
//		}
//		if(!beforeUpdate.getDocAddress().equals(d1.getDocAddress())) {
//			changes.add("\n" + "Doctor Address,\n " + op+beforeUpdate.getDocAddress()+op + " Changed To " + op +d1.getDocAddress()+op +"\n");
//		}
//		if(beforeUpdate.getHosID() != d1.getHosID()) {
//			changes.add("\n" + "Doctor Hospital ID,\n " + op+beforeUpdate.getHosID()+op + " Changed To " + op +d1.getHosID()+op +"\n");
//		}
//		
//		
//	}
	
}
