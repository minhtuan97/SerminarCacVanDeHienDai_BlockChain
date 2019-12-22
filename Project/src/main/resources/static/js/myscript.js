
//Load Trang - An Table Kha Nang Kham Va Xet Nghiem
$( document ).ready(function() {
	 $("#data_table").hide();
});



//Click Vao Mot Thanh Vien Tham Gia 
$(".MyMemeber").click(function(){
	if(!$('#data_table').is(':visible')){
		$("#data_table").show();
	}
	
	var valueATag = $(this).text();
	
	$("#keyBenhVien").text(valueATag);

});



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


