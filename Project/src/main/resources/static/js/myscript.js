//Load Trang - An Table Kha Nang Kham Va Xet Nghiem
var listBenhVien=[];
var listHopDong=[];

$( document ).ready(function() {
	 $("#data_table").hide();
	 
	 //Gọi hàm ajax lấy ra danh sách bệnh viện
	searchHospital((data)=>{
		listBenhVien = JSON.parse(data);

		var publicKeys = "";
		listBenhVien.forEach(element => {
			publicKeys += '<a  class="dropdown-item MyMemeber" onClick="memberClick(this)">'+element.publicKey+'</a>';		
		});
		

		$("#listDropdownHospital").append(publicKeys);
	});
	
	//Gọi hàm ajax lấy ra danh sách hợp đồng
	searchSmartContract((data)=>{
		console.log(data);
		console.log(data.listSCDone[0].ChuanDoanBenhAn);
		listHopDong = data;
	});
});



// Click Vao Mot Thanh Vien Tham Gia
function memberClick(a){
	if(!$('#data_table').is(':visible')){
		$("#data_table").show();
	}
	
	let key = $(a).text();
	let khanangkham = "* ";
	let khanangxetnghiem = "* ";

	listBenhVien.forEach(element => {
		$("#keyBenhVien").val(element.publicKey);
		if(element.publicKey==key){
			element.ability.benhNhiemTrung? khanangkham+="Bệnh nhiễm trùng * " :  khanangkham+="";
			element.ability.buouTanSinh? khanangkham+="Bướu tân sinh * " :  khanangkham+="";
			element.ability.benhVeMau? khanangkham+="Bệnh về máu * " :  khanangkham+="";
			element.ability.benhNoiTietDinhDuongVaChuyenHoa? khanangkham+="Bệnh nội tiết dinh dưỡng và chuyển hóa * " :  khanangkham+="";
			element.ability.benhTamThan? khanangkham+="Bệnh tâm thần * " :  khanangkham+="";
			element.ability.benhHeThanKinh? khanangkham+="Bệnh hệ thần kinh * " :  khanangkham+="";
			element.ability.benhVeMat? khanangkham+="Bệnh về mắt * " :  khanangkham+="";
			element.ability.benhTaiMuiHong? khanangkham+="Bệnh tai mũi họng * " :  khanangkham+="";
			element.ability.benhHeTuanHoan? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhHeHoHap? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhHeTieuHoa? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhDaVaMoDuoiDa? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhCoXuongKhop? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.benhHeSinhDucTietNieu? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.thaiNghenSinhDe? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.vetThuongNgoDoc? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			element.ability.cacYeuToSauChuaTriYTe? khanangkham+="Benh nhiem trung" :  khanangkham+="";
			
			element.ability.xetNghiemMau? khanangxetnghiem+="Benh nhiem trung" :  khanangkham+="";
			element.ability.chupXQuang? khanangxetnghiem+="Benh nhiem trung" :  khanangkham+="";
		
			
		}
		
		$("#listKhaNangKham").text(khanangkham);
		$("#listKhaXetNghiem").text(khanangxetnghiem);
			
	});
	
}



// Insert thanh vien tham gia vào TextBox
$(function() {
	$('#Inser_btn').click(function() {
		var oldText = $("#danhsachThanhVien").val();
		var span = $('#keyBenhVien').val();

		var result;

		if (oldText == "")
			result = span;
		else
			result = oldText + "," + span;

		$('#danhsachThanhVien').val(result);

	});
});



// Hien thi giao dich can xac thuc
$(function() {
	$('#needPermit')
			.click(
					function() {
						// Thay ten button
						$("#dropdownMenuButton").text('Giao dịch cần xác thực');
						
						if (!$('#sectionXacNhan').is(':visible')) {
							$("#sectionXacNhan").show();
						}

						//Loai bo data truoc do
						$(".danhsachgiaodich").html("");
						
						var idGiaodich= "1234";
						var tenGiaoDich= "ASHKJAHSKJ";
						var ngaythangnam= "11/12/2019";
						
						for (i = 0; i < 4; i++) {
							$(".danhsachgiaodich")
									.append(
											"<li><i class='task-icon bg-c-green'></i> <a onClick='showDiv("+idGiaodich+");'  href='javascript:void(0)'>"+tenGiaoDich+"<span class='float-right text-muted'>"+ngaythangnam+"</span></a></li>");
						}
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


//Hien thi giao dich da xac thuc
$(function() {
	$('#permitted')
			.click(
					function() {
						// Thay ten button
						$("#dropdownMenuButton").text('Giao dịch đã xác thực');
						
						if ($('#sectionXacNhan').is(':visible')) {
							$("#sectionXacNhan").hide();
						}

						//Loai bo data truoc do
						$(".danhsachgiaodich").html("");
						
						var idGiaodich= "997878";
						var tenGiaoDich= "HHJFKGJKHGJ";
						var ngaythangnam= "1/1/2019";
						
						for (i = 0; i < 4; i++) {
							$(".danhsachgiaodich")
									.append(
											"<li><i class='task-icon bg-c-green'></i> <a onClick='showDiv("+idGiaodich+");'  href='javascript:void(0)'>"+tenGiaoDich+"<span class='float-right text-muted'>"+ngaythangnam+"</span></a></li>");
						}
					});
});

//Hàm truy vấn danh sách hợp đồng
function searchSmartContract(callBack) {

	var publicKey = $("#publicKey").html();
	var privateKey = $("#privateKey").html();

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/ajax/getListSmartContract",
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

//Hàm truy vấn hình ảnh
function searchPicture(fileHash, callBack) {

	var publicKey = $("#publicKey").html();
	var privateKey = $("#privateKey").html();

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/ajax/getPicture",
		data : {
			publicKey : publicKey,
			privateKey : privateKey,
			fileHash: fileHash
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

//Hàm cập nhật trạng thái đồng ý
function updateAgree(MSBenhAn, agreeState, callBack) {

	var publicKey = $("#publicKey").html();
	var privateKey = $("#privateKey").html();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/ajax/updateAgree",
		data : {
			publicKey : publicKey,
			privateKey : privateKey,
			MSBenhAn: MSBenhAn,
			agreeState: agreeState
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


//Hien thi chi tiet giao dich
function showDiv(a){
	var idGiaoDich = a;
	
	//Thông tin bệnh án
	$("#MSMau").text("Bệnh ABC");
	$("#SoMau").text("Bệnh ABC");
	$("#LoaiMau").text("Bệnh ABC");
	$("#ChuanDoanMau").text("Bệnh ABC");
	$("#SoLuongHC").text("Bệnh ABC");
	$("#HuyetSacTo").text("Bệnh ABC");
	$("#Hematocrit").text("Bệnh ABC");
	$("#MCV").text("Bệnh ABC");
	$("#MCH").text("Bệnh ABC");
	$("#MCHC").text("Bệnh ABC");
	$("#HCCoNhan").text("Bệnh ABC");
	$("#HCLuoi").text("Bệnh ABC");
	$("#SLTieuCau").text("Bệnh ABC");
	$("#KSVSotRet").text("Bệnh ABC");
	$("#SLBC").text("Bệnh ABC");
	$("#TPBC").text("Bệnh ABC");
	$("#DoanTrungTinh").text("Bệnh ABC");
	$("#DoanUaAxit").text("Bệnh ABC");
	$("#DoanUaBazo").text("Bệnh ABC");
	$("#Mono").text("Bệnh ABC");
	$("#Lympho").text("Bệnh ABC");
	$("#TeBaoBT").text("Bệnh ABC");
	$("#MauLang").text("Bệnh ABC");
	$("#TGMauChay").text("Bệnh ABC");
	$("#TGMauDong").text("Bệnh ABC");
	$("#HeABO").text("Bệnh ABC");
	$("#HeRh").text("Bệnh ABC");
	
	//Thông tin XQuang
	$("#MSXQuang").text("Bệnh ABC");
	$("#SoXQuang").text("Bệnh ABC");
	$("#ThoiGian").text("Bệnh ABC");
	$("#ChuanDoanXQuang").text("Bệnh ABC");
	$("#YeuCauChup").text("Bệnh ABC");
	$("#KetQuaChup").text("Bệnh ABC");
	$("#hashFile").text("Bệnh ABC");

	
	
}















$('#khongdongy').on("click", function() {
	if (document.querySelector('.Tengiaodich') !== null) {
	    alert("Da ton tai")
	}
})

