$(document).ready(function(){
	
	$("#btnRankSearch").on("click", function(e){
		read();
	});
	
	function makeDataSource(data) {
		var grid = $("#rankGrid");
		removeDataSource();
		
		if(data && data.length > 0) {
			var rankSize;
			if(data.length < 10) {
				rankSize = data.length;
			} else {
				rankSize = 10;
			}
			for(var rank=0;rank<rankSize;rank++) {
				var row = 
					"<tr>"+
						"<th scope='row'>"+(rank+1)+"</th>"+
						"<td class='uid' style='display:none'>"+data.id+"</td>"+
						"<td>"+data[rank].keyword+"</td>"+
						"<td>"+data[rank].count+"</td>"+
					"</tr>";
				
				grid.append(row);
			}
		}
	};
	
	function removeDataSource() {
		var table = $("#rankGrid");
		table.empty();
		if($(".emptyData").length > 0) {
			$(".emptyData").remove();
		}
	};
	
	function read() {
		$.ajax({
			async : false,
			type : "GET",
			url :"http://localhost:8080/api/service/rank",
			dataType : "JSON",
			success: function(data) {
				if(data && data.length > 0) {
					makeDataSource(data);
				} else {
					emptyGrid($("#rankGrid"));
				}
			},
			complete : function(data) {
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
