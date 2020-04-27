
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <title>Doctor Management</title>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        
      
		<script src="script.js"></script>
		
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <h1 align="center">Doctor Management</h1>
            <div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
           <!--  <button id="newTutBtn" class="btn btn-primary btn-block">Add New Doctor</button><br /><br /> -->
            <br /><br />
            <form id="newForm">
            
              <div class="form-group row">
                <label for="docID" class="col-sm-2 col-form-label">Doctor ID</label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="docID" placeholder="Doctor ID">
                  </div>
              </div>
              
              <div class="form-group row">
                <label for="docFname" class="col-sm-2 col-form-label">Firstname</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="docFname" placeholder="Firstname">
                  </div>
              </div>
              
              <div class="form-group row">
                <label for="docLname" class="col-sm-2 col-form-label">Lastname</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="docLname" placeholder="Lastname">
                  </div>
              </div>
              
              <div class="form-group row">
                <label for="docPosition" class="col-sm-2 col-form-label">Position</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="docPosition" placeholder="Position">
                  </div>
              </div>
              
              <div class="form-group row">
                <label for="docFee" class="col-sm-2 col-form-label">Fee</label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="docFee" placeholder="Fee">
                  </div>
              </div>
              
              <div class="form-group row">
                <label for="docAddress" class="col-sm-2 col-form-label">Address</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="docAddress" placeholder="Address">
                  </div>
              </div>
              
              <div class="form-group row">
                <label for="mobileNo" class="col-sm-2 col-form-label">Mobile</label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="mobileNo" placeholder="Mobile">
                  </div>
              </div>
              
              <div class="form-group row">
                <label for="hosID" class="col-sm-2 col-form-label">Hospital ID</label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="hosID" placeholder="Hospital ID">
                  </div>
              </div>
              
              
              <button id="submitDoctorBtn" type="submit" class="btn btn-primary">Submit Details</button>
              <button id="updateDoctorBtn" type="submit" class="btn btn-primary">Update Details</button>
              
            </form>
            
           <div id="updateFormDiv">
            </div> 
            
            <table id="doctorDetailsTable" class="table table-bordered table-hover">
                <thead>
                    <th>Doc ID</th>
                    <th>Doctor Firstname</th>
                    <th>Doctor Lastname</th>
                    <th>Doctor Position</th>
                    <th>Doctor Fee</th>
                    <th>Doctor Address</th>
                    <th>Doctor Mobile</th>
                    <th>Doctor Hospital ID</th>
                </thead>
                <tbody id="doctorsDetailssBody"></tbody>
            </table>
        </div>
    </body>
</html>