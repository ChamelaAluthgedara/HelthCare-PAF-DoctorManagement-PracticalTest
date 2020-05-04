
$(document).ready(function()
	{
	 $("#editHostId").hide();
	 $("#docID").prop("readonly",false);
	});


$(document).ready(function(){
    
    getRegisteredDoctors();
    $("#updateDoctorBtn").hide();
 
    function getRegisteredDoctors(){
        $('#doctorsDetailssBody').html('');
        $.ajax({
            url: 'webapi/doctors/',
            method: 'get',
            dataType: 'json',
            success: function(data) {
                $(data).each(function(i, doctor){
                    $('#doctorsDetailssBody').append($("<tr>")
                                            .append($("<td>").append(doctor.docID))
                                            .append($("<td>").append(doctor.docFName))
                                            .append($("<td>").append(doctor.docLName))
                                            .append($("<td>").append(doctor.docPosition))
                                            .append($("<td>").append(doctor.docFee))
                                            .append($("<td>").append(doctor.docAddress))
                                            .append($("<td>").append(doctor.mobileNo))
                                            .append($("<td>").append(doctor.hosID))
                                            .append($("<td>").append(`
                                                <i class="far fa-edit editTut" data-docid="`+doctor.docID+`"></i>
                                                <i class="fas fa-trash deleteTut" data-docid="`+doctor.docID+`"></i>
                                            `)));
                    });
                $("#updateForm").hide();
                $("#submitDoctorBtn").show();
                loadButtons();
                getRegisteredHospitaIDs();
                }
        });
    }
    
// ------- Buttons Initialization----------------------------------
    function loadButtons() {
        $(".editTut").click(function(e){
            getSingleDoctor($($(this)[0]).data("docid"));
            $("#submitDoctorBtn").hide();
            $("#updateDoctorBtn").show();
            
            $("#docID").prop("readonly",true);
            e.preventDefault();
           
            
        });
        
        $(".deleteTut").click(function(e){
            deleteDoctorDetails($($(this)[0]).data("docid"));
            e.preventDefault();
        })
    }
//------------------------------------------------------------------------

    
    
// ------ Getting Registered Hospital Id to set Drop down ----------------
    function getRegisteredHospitaIDs(){
        $.ajax({
            url: 'doctorAPI',
            method: 'get',
            dataType: 'json',
            success: function(data) {
                console.log("Recieved: " + data);
                
                var listItems= "";
                var jsonData = data;
                    for (var i = 0; i < jsonData.length; i++){
                      listItems+= "<option value='" + i + "'>" + data[i] + "</option>";
                    }

                    $("#hosID").html(listItems);
            },
        
        });
    }
// --------------------------------------------------------------------------- 
    
// --------- Insert New Doctor API -----------------------------------------
     function createDoctor(data) {
    	 console.log(data);
         $.ajax({
             url: 'doctorAPI',
             method: 'POST',
             dataType: 'json',
             data: data,
             success: function(state) {
                 console.log(state);
                 
                 var stateGot = state;
                 
                 if(stateGot > 0){
                	 getRegisteredDoctors();
                	 $.notify("Data Successfully Submitted.", "success");
                 }
                 if(stateGot == 0){
                		$.notify("Doctor ID already Extist.", "error");
                 }
                 
             },
         
         });
         
     }
//---------------------------------------------------------------------------
     
//---- Get Selected Doctor details and fill Update fields --------------     
     function getSingleDoctor(id){
    	 
    	 console.log(id);
    	 var data = {id};
    	 
         $.ajax({
             url: 'webapi/doctors/doctor/' + id,
             method: 'get',
             dataType: 'json',
             success: function(data) {
            	 console.log(data);
                 $($("#newForm")[0].docID).val(data.docID);
                 $($("#newForm")[0].docFname).val(data.docFName);
                 $($("#newForm")[0].docLname).val(data.docLName);
                 $($("#newForm")[0].docPosition).val(data.docPosition);
                 $($("#newForm")[0].docFee).val(data.docFee);
                 $($("#newForm")[0].docAddress).val(data.docAddress);
                 $($("#newForm")[0].mobileNo).val(data.mobileNo);
                 
                 var listItems= "";
                 listItems+= "<option value='" + data.hosID + "'>" + data.hosID + "</option>";
                  $("#hosID").html(listItems);
                 $("#updateForm").show();
                 $("#editHostId").show();
             }
         });
     }
//-----------------------------------------------------
     
     
// -------- Edit hospitals ID button ------------------
     $("#editHostId").on("click", function(e) {
    	 $("#editHostId").hide();
    		 getRegisteredHospitaIDs();
      });
//----------------------------------------------------
     
     
//--------- Update doctor API -----------------------------
     function updateDoctorDetails(id, data){
     	console.log(data);
         $.ajax({
             url: 'doctorAPI',
             method: 'PUT',
             dataType: 'json',
             data: data,
             success: function(state)
             {
            		 $.notify("Data Successfully Updated.", "success");
                 	 getRegisteredDoctors();
                 	$("#docID").prop("readonly",false);
                 	 $("#editHostId").hide();
          
             }
         });
        
     }
//------------------------------------------------------------
     
     


//--------- Delete Doctor details API ---------------------------------    
     function deleteDoctorDetails(id){
     	console.log(id);
     	var data = {id};
         $.ajax({
             url: 'doctorAPI',
             method: 'DELETE',
             dataType: 'json',
             data: data,
             success: function(data) {
                 console.log(data);
                 getRegisteredDoctors();
              	$.notify("Doctor Details Delete Completed.", "warn");

                $("#updateDoctorBtn").hide();
                $("#submitDoctorBtn").show();
             }
         });
     }
//-------------------------------------------------------------------  
     
     
//=============================== Button Clicks =============================================================
     
// -------------- Submit Button Click Event ----------------------
     $("#submitDoctorBtn").on("click", function(e) {
         let data = {
      		   docID: $($("#newForm")[0].docID).val(),
      		   docFname: $($("#newForm")[0].docFname).val(),
      		   docLname: $($("#newForm")[0].docLname).val(),
      		   docPosition: $($("#newForm")[0].docPosition).val(),
      		   docFee: $($("#newForm")[0].docFee).val(),
      		   docAddress: $($("#newForm")[0].docAddress).val(),
      		   mobileNo: $($("#newForm")[0].mobileNo).val(),
      		   hosID: $('#hosID :selected').text()
         } 
         console.log($('#hosID option:selected').text());
          

        	 var status = validateItemForm();
        	
        	// If not valid-------------------
        	if (status != true)
        	 {
 	       	$.notify(status, "error");
        	 }
        	if(status == true){
        		createDoctor(data);
        		
        	}
             $("#newForm").trigger("reset");
             e.preventDefault();
        	 
      });
//-----------------------------------------------------
     
// -------------- Update Button Click Event -----------
     $("#updateDoctorBtn").on("click", function(e) {
     	
         let data = {
      		   docID: $($("#newForm")[0].docID).val(),
      		   docFname: $($("#newForm")[0].docFname).val(),
      		   docLname: $($("#newForm")[0].docLname).val(),
      		   docPosition: $($("#newForm")[0].docPosition).val(),
      		   docFee: $($("#newForm")[0].docFee).val(),
      		   docAddress: $($("#newForm")[0].docAddress).val(),
      		   mobileNo: $($("#newForm")[0].mobileNo).val(),
      		   hosID: parseInt($($("#newForm")[0].hosID).val())+1
         } 
        
       	// Form validation----------------
       	 var status = validateItemForm();
       	// If not valid-------------------
       	if (status != true)
       	 {
 	     	$.notify(status, "error");
 	       	$("#updateDoctorBtn").show();
 	       	
       	 }
       	
         if(status == true){
 	        updateDoctorDetails($($("#newForm")[0].docID).val(), data);
        	}
          
          $("#newForm").trigger("reset");
          $("#updateDoctorBtn").hide();
          $("#submitDoctorBtn").show();
          e.preventDefault();
          
         
      });
});
 // -----------------------------------------------------------------

//==================================================================================================

//--------- Form Validation ------------------------------

function validateItemForm()
	{


		if ($("#docID").val().trim() == "")
		 {
			return "Empty Field Detected: Doctor's ID.";
		 }
		if ($("#docFname").val().trim() == "")
		 {
			return "Empty Field Detected: Doctor's Firstname.";
		 }
		if ($("#docLname").val().trim() == "")
		 {
			return "Empty Field Detected: Doctor's Lastname.";
		 }
		if ($("#docPosition").val().trim() == "")
		 {
			return "Empty Field Detected: Doctor's Position.";
		 }
		if ($("#docFee").val().trim() == "")
		 {
			return "Empty Field Detected: Doctor's Fee.";
		 }
		if ($("#docAddress").val().trim() == "")
		 {
			return "Empty Field Detected: Doctor's Address.";
		 }
		if ($("#mobileNo").val().trim() == "")
		 {
			return "Empty Field Detected: Doctor's Mobile Number.";
		 }
			return true;
	}

//--------------------------------------------------------

