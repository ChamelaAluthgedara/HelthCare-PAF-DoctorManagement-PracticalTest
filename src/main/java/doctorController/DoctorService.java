package doctorController;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {

	List<Doctor> doctors;

	Connection con = null;

	public DoctorService() {
		String url = "jdbc:mysql://localhost:3306/pafHospitalManagementDB2020";
		String username = "root";
		String password = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

			System.out.println("DB Connected Successfully !!!");

		} catch (Exception e) {
			System.out.println("DB Connection Lost ....");

			System.out.println(e);
		}
	}

	// retrive all registered doctors in db.
	public List<Doctor> getDoctors() {
		List<Doctor> doctors = new ArrayList<>();

		String sql = "select * from Doctor";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Doctor d = new Doctor();
				d.setDocID(rs.getInt(1));
				d.setDocFName(rs.getString(2));
				d.setDocLName(rs.getString(3));
				d.setDocPosition(rs.getString(4));
				d.setDocFee(rs.getDouble(5));
				d.setMobileNo(rs.getInt(6));
				d.setDocAddress(rs.getString(7));
				d.setHosID(rs.getInt(8));

				doctors.add(d);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		//getAllHospitals();
		return doctors;
	}

	// retrive particular registered doctor by id in db
	public Doctor getDoctor(int id) {
		String sql = "select * from Doctor where DocID=" + id;
		Doctor d = new Doctor();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {

				d.setDocID(rs.getInt(1));
				d.setDocFName(rs.getString(2));
				d.setDocLName(rs.getString(3));
				d.setDocPosition(rs.getString(4));
				d.setDocFee(rs.getDouble(5));
				d.setMobileNo(rs.getInt(6));
				d.setDocAddress(rs.getString(7));

				d.setHosID(rs.getInt(8));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return d;
	}

	// create new doctor instance
	public boolean Create(List<Object> docList) {

		Doctor d1 = new Doctor();

		d1.setDocID((int) docList.get(0));
		d1.setDocFName((String) docList.get(1));
		d1.setDocLName((String) docList.get(2));
		d1.setDocPosition((String) docList.get(3));
		d1.setDocFee((Double) docList.get(4));
		d1.setMobileNo((int) docList.get(5));
		d1.setDocAddress((String) docList.get(6));
		d1.setHosID((int) docList.get(7));

		String sql = "insert into Doctor values (?,?,?,?,?,?,?,?)";
		String isHosAvl = "select hostId from hospitals where hostId=" + d1.getHosID();

		try {
			int hostIDAvl = 0;
			Statement st1 = con.createStatement();
			ResultSet rs = st1.executeQuery(isHosAvl);
			if (rs.next()) {
				System.out.println(rs.getInt(1));
				hostIDAvl = rs.getInt(1);
			}

			if (hostIDAvl > 0) {

				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, d1.getDocID());
				st.setString(2, d1.getDocFName());
				st.setString(3, d1.getDocLName());
				st.setString(4, d1.getDocPosition());
				st.setDouble(5, d1.getDocFee());
				st.setInt(6, d1.getMobileNo());
				st.setString(7, d1.getDocAddress());
				st.setInt(8, d1.getHosID());

				st.executeUpdate();
				 
			}

//			else
//			{
//				
//				//return "InvalidhosID";
//			}

		} catch (Exception e) {
			System.out.println(e);
		 return false;

		}
		return true;

	}

	// update current doctor details in db
	public Boolean Update(List<Object> docList) {

		Doctor d1 = new Doctor();

		d1.setDocID((int) docList.get(0));
		d1.setDocFName((String) docList.get(1));
		d1.setDocLName((String) docList.get(2));
		d1.setDocPosition((String) docList.get(3));
		d1.setDocFee((Double) docList.get(4));
		d1.setMobileNo((int) docList.get(5));
		d1.setDocAddress((String) docList.get(6));
		d1.setHosID((int) docList.get(7));
		String sql = "update doctor set DocFName=?, DocLName=?, DocPosition=?, DocFee=?, MobileNo=?, DocAddress=?, HosID=? where DocID=?";
		String isHosAvl = "select hostId from hospitals where hostId=" + d1.getHosID();

		try {
			int hostIDAvl = 0;
			Statement st1 = con.createStatement();
			ResultSet rs = st1.executeQuery(isHosAvl);
			if (rs.next()) {
				System.out.println(rs.getInt(1));
				hostIDAvl = rs.getInt(1);
			}
			if (hostIDAvl > 0) {
				PreparedStatement st = con.prepareStatement(sql);

				st.setString(1, d1.getDocFName());
				st.setString(2, d1.getDocLName());
				st.setString(3, d1.getDocPosition());
				st.setDouble(4, d1.getDocFee());
				st.setInt(5, d1.getMobileNo());
				st.setString(6, d1.getDocAddress());
				st.setInt(7, d1.getHosID());
				st.setInt(8, d1.getDocID());

				st.executeUpdate();
				
			} 
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	// delete doctor details in db
	public void kill(int id) {

		String sql = "delete from doctor where DocID=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, id);

			st.executeUpdate();
			// return true;

		} catch (Exception e) {
			System.out.println(e);
			// return false;
		}

	}

	public List<Integer> getAllHospitals() {
		
		List<Integer> hospitals = new ArrayList<>();
		
		String sql = "select hostId from hospitals";
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				List<Integer> d = new ArrayList<>();
				 d.add(rs.getInt(1));
				
				hospitals.addAll(d);
			}
		
		}
		catch(Exception e){
		
		}
		//System.out.println("Hospitals: " + hospitals);
		return hospitals;
		
	}
	
}
