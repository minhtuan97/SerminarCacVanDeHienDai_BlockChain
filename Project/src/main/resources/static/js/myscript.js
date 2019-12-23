
//Load Trang - An Table Kha Nang Kham Va Xet Nghiem
var listBenhVien=[];

$( document ).ready(function() {
	 $("#data_table").hide();
	 
	searchHospital((data)=>{
		listBenhVien = JSON.parse(data);

		var publicKeys = "";
		listBenhVien.forEach(element => {
			publicKeys += '<a class="dropdown-item MyMemeber "onClick="memberClick()">'+element.publicKey+'</a>';
		});

		$("#listDropdownHospital").append(publicKeys);
	});
});



//Click Vao Mot Thanh Vien Tham Gia 
$(".MyMemeber").click(function(){
	if(!$('#data_table').is(':visible')){
		$("#data_table").show();
	}
	
	var valueATag = $(this).text();
	
	$("#keyBenhVien").text(valueATag);
	
	$("#listKhaNangKham").text("ashjdakjsdhakjsdhkjh");
	$("#listKhaXetNghiem").text("ABCJjkashdjksfhskjf");
});

//Onclick của MyMember 
function memberClick(){
	if(!$('#data_table').is(':visible')){
		$("#data_table").show();
	}
	alert("sjahdjashd");
	// var valueATag = $(this).text();
	
	// $("#keyBenhVien").text(valueATag);
	
	// $("#listKhaNangKham").text("ashjdakjsdhakjsdhkjh");
	// $("#listKhaXetNghiem").text("ABCJjkashdjksfhskjf");
}


//Insert thanh vien tham gia vào TextBox
$(function(){
    $('#Inser_btn').click(function(){
    	var oldText = $("#danhsachThanhVien").val();
    	var span = $('#keyBenhVien').text();
    	
    	var result ;
    	
    	if(oldText=="") result = span;
    	else result = oldText+","+span;
    	
    	$('#danhsachThanhVien').val(result);
    	
    	
    });
});

//Hàm truy vấn danh sách bệnh viện
function searchHospital(callBack) {

	var publicKey = $("#publicKey").html();
	var privateKey = $("#privateKey").html();

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/ajax/getListHospital",
		data : {
			publicKey : publicKey,
			privateKey : privateKey
		},
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			callBack(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}


