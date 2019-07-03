$(document).ready(function(){
	$("#btnHistorySearch").on("click", function(e){
		read(true);
	});
	
	function makeDataSource(data) {
		var historyGrid = $("#historyGrid");
		removeDataSource();
		
		data.forEach(function(data, index) {
			var row = 
				"<tr>"+
					"<th scope='row'>"+(index+1)+"</th>"+
					"<td class='uid' style='display:none'>"+data.id+"</td>"+
					"<td>"+data.keyword+"</td>"+
					"<td>"+new Date(data.createDate).format('yyyy-MM-dd(KL) a/p HH:mm:ss')+"</td>"+
				"</tr>";
			
			historyGrid.append(row);
		});
	};
	
	function removeDataSource() {
		var table = $("#historyGrid");
		table.empty();
		
		if($(".emptyData").length > 0) {
			$(".emptyData").remove();
		}
	};
	
	function read(isNew) {
		$.ajax({
			async : false,
			type : "GET",
			url :"http://localhost:8080/api/service/history",
			dataType : "JSON",
			success: function(data) {
				if(data && data.length > 0) {
					makeDataSource(data)
				} else {
					debugger;
					emptyGrid($("#historyGrid"));
				}
			},
			error : function(xhr, status, error) {
				console.error(error);
			}
		});
	};
	
	function emptyGrid(gridElement){
		if($(".emptyData").length > 0) {
			$(".emptyData").remove();
		}
		gridElement.closest("table").after("<div style='height:300px;text-align:center;padding:90px;font-weight:bold;' class='emptyData'>데이터가 없습니다.</div>");
	};
});
