package doctorController;


import java.util.List;
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
		
		return repo.getDoctor(id);
	}
	
	

	
}
