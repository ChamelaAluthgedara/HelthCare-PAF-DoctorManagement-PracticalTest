
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Doctor Management</title>
		
		<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
		<script src="script.js"></script>
		<script src="notify.js"></script>
		
		<link rel="stylesheet"
			href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
			crossorigin="anonymous">
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="style.css">
		
		<style>
				input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
					-webkit-appearance: none;
					margin: 0;
				}
				/* Firefox */
				input[type=number] {
					-moz-appearance: textfield;
				}
				
				.alert {
					width: 350px;
				}
				
				.btn-group button {
					border: 1px solid black;
					margin-left: 15px;
					/*/ /* Float the buttons side by side */
				}
		</style>
		<script>
				function maxLengthCheck(object) {
					if (object.value.length > object.maxLength)
						object.value = object.value.slice(0, object.maxLength)
				}
		</script>
		

</head>
<body>
	<div class="container">
		<h1 align="center">Doctor Management</h1>
		<hr>
		<form id="newForm">

			<div class="form-group row">
				<label for="docID" class="col-sm-2 col-form-label">Doctor ID</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="docID"
						placeholder="Doctor ID" oninput="maxLengthCheck(this)"
						maxlength="4">
				</div>
			</div>

			<div class="form-group row">
				<label for="docFname" class="col-sm-2 col-form-label">Firstname</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="docFname"
						placeholder="Firstname">
				</div>
			</div>

			<div class="form-group row">
				<label for="docLname" class="col-sm-2 col-form-label">Lastname</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="docLname"
						placeholder="Lastname">
				</div>
			</div>

			<div class="form-group row">
				<label for="docPosition" class="col-sm-2 col-form-label">Position</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="docPosition"
						placeholder="Position">
				</div>
			</div>

			<div class="form-group row">
				<label for="docFee" class="col-sm-2 col-form-label">Fee</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="docFee"
						placeholder="Fee" oninput="maxLengthCheck(this)" maxlength="10">
				</div>
			</div>

			<div class="form-group row">
				<label for="docAddress" class="col-sm-2 col-form-label">Address</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="docAddress"
						placeholder="Address">
				</div>
			</div>

			<div class="form-group row">
				<label for="mobileNo" class="col-sm-2 col-form-label">Mobile</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="mobileNo"
						placeholder="Mobile" oninput="maxLengthCheck(this)" maxlength="10">
				</div>
			</div>

			<div class="form-group row">
				<label for="hosID" class="col-sm-2 col-form-label">Hospital
					ID</label>
				<div class="col-sm-10">
					<select id="hosID" name="hosID">
					</select> <label id="editHostId" class="col-form-label"> Edit </label>
				</div>
			</div>
			<hr>
			<div class="btn-group">
				<button id="submitDoctorBtn" type="submit" class="btn btn-primary">SubmitDetails</button>
				<!-- <button id="alertError" class="alert alert-danger" disabled ></button>
				<button id="alertSuccess" class="alert alert-success" disabled></button> -->
				<button id="updateDoctorBtn" type="submit" class="btn btn-primary">UpdateDetails</button>
			</div>
		</form>
		<hr>
		<table id="doctorDetailsTable"class="table table-bordered table-hover">
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