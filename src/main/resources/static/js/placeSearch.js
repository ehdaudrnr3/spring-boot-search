$(document).ready(function(){
	var keyword = "";
	const PAGE_SIZE = 10;
	var pagination = new Pagination($("#placePagination"));
	
	$("#btnLogout").on("click", function(e){
		$("#logout").get(0).click();
	});
	
	$("#btnPlaceSearch").on("click", function(e){
		keyword = $("#keyword").val();
		read(keyword, 1, true);
	});
	
	$("#keyword").on("keydown", function(e){
		//Enter input
		if(e.keyCode == 13) {	
			$("#btnPlaceSearch").trigger("click");
		}
	});
	
	function makeDataSource(data) {
		var placeGrid = $("#placeGrid");
		removeDataSource();
		
		data.documents.forEach(function(data, index) {
			if(!hasAddress(data.address_name)) {
				var row = 
					"<tr>"+
						"<th scope='row'>"+(index+1)+"</th>"+
						"<td class='uid' style='display:none'>"+data.id+"</td>"+
						"<td>"+data.address_name+"</td>"+
						"<td>"+data.phone+"</td>"+
						"<td>"+data.place_name+"</td>"+
						"<td>"+data.road_address_name+"</td>"+
						"<td><a target='_blank' href='https://map.kakao.com/?itemId="+data.id+"'>바로가기</a></td>"+
						"<td><a id='map"+(index+1)+"' href='#map'>이동</a></td>"+
					"</tr>";
				
				placeGrid.append(row);
				placeGrid.find("#map"+(index+1)).on("click", data, onMoveClick);
			}
		});
	};
	
	function hasAddress(address_name) {
		var has = false;
		var rows = $("#placeGrid").find("tr .value");
		$(rows).each(function(index, item) {
			if(address_name == item.textContent) {
				has = true;
				return false;
			}
		});
		return has;
	};
	
	function removeDataSource() {
		var table = $("#placeGrid");
		table.empty();
		if($(".emptyData").length > 0) {
			$(".emptyData").remove();
		}
	};
	
	function read(keyword, page, isNew) {
		if(keyword) {
			$.ajax({
				async : false,
				type : "GET",
				url :"http://localhost:8080/api/service/place",
				dataType : "JSON",
				data : {
					keyword : keyword,
					page : page
				},
				success: function(data) {
					if(data && data.documents.length > 0) {
						if(isNew) {
							pagination.setPageCount(data.meta.pageable_count);
							pagination.makePage(true);
						}
						makeDataSource(data);
					} else {
						emptyGrid($("#placeGrid"));
					}
				},
				complete : function(data) {
				},
				error : function(xhr, status, error) {
					console.error(error);
				}
			});
		} else {
			removeDataSource();
			emptyGrid($("#placeGrid"));
		}
		
	};
	
	function emptyGrid(gridElement){
		if($(".emptyData").length > 0) {
			$(".emptyData").remove();
		}
		pagination.removePage();
		gridElement.closest("table").after("<div style='height:300px;text-align:center;padding:90px;font-weight:bold;' class='emptyData'>데이터가 없습니다.</div>");
	};
	
	function onMoveClick(e) {
		var position = new kakao.maps.LatLng(e.data.y, e.data.x);
		
		map.setCenter(position);
		map.setLevel(3);
		clusterer.clear();
		clusterer.addMarker(
			new kakao.maps.Marker({
				position: position
			})
		);
		var content ='<div class="customoverlay">'+
						'<a href="https://map.kakao.com/link/map/"'+e.data.id+' target="_blank">'+
							'<span class="title">'+e.data.place_name+'</span>'+
							'<span class="title">'+
								'<span>'+e.data.address_name+'</span'+
							'</span>'+
						'</a>'+
					 '</div>';
		var customOverlay = new kakao.maps.CustomOverlay({
			position : position,
			content : content
		});

		customOverlay.setMap(map);
	}
	
	function Pagination(self) {
		this.pagination = self;
		this.pageable_count = 0;
		this.activeSelector;
		this.startPage = 1;
		this.endPage = PAGE_SIZE;
	};
	
	Pagination.prototype.setPageCount = function(pageable_count) {
		this.pageable_count = Math.ceil(pageable_count / 15);
		this.startPage = 1;
		this.endPage = PAGE_SIZE;
	};
	
	Pagination.prototype.setActiveSelector = function(element) {
		$(element).addClass("active");
		this.activeSelector = $(element);
	};
	
	Pagination.prototype.getActivePage = function() {
		return Number(this.activeSelector.text());
	};
	
	Pagination.prototype.findByIndex = function(index) {
		if(index && index > 0) {
			return this.pagination.find(".page:contains('"+index+"')");
		}
		return null;
	};
	
	Pagination.prototype.onPageClick = function(e){
		var that = e.data;
		
		that.pagination.find(".page.active").removeClass("active");
		that.setActiveSelector(this);
		
		read(keyword, that.getActivePage(), false);
	};
	
	Pagination.prototype.onPreviousClick = function(e){
		var that = e.data;
		if(that.getActivePage() > 1){
			that.pagination.find(".page.active").removeClass("active");
			var element = that.findByIndex(that.getActivePage()-1);
				
			if(element) {
				if(element && element.length > 0) {
					that.setActiveSelector(element);
				} else {
					that.changePage(false);
				}
			}
				
			read(keyword, that.getActivePage(), false);
		}
	};
	
	Pagination.prototype.onNextClick = function(e){
		var that = e.data;
		if(that.getActivePage() > 0 && that.getActivePage() < that.pageable_count){
			that.pagination.find(".page.active").removeClass("active"); 
			var element = that.findByIndex(that.getActivePage()+1);
			
			if(element && element.length > 0) {
				that.setActiveSelector(element);
			} else {
				that.changePage(true);
			}
			
			read(keyword, that.getActivePage(), false);
		}
	};
	
	Pagination.prototype.makePage = function(isFirst) {
		var pageCount;
		if(this.pageable_count >= this.endPage) {
			pageCount = PAGE_SIZE;
		} else {
			pageCount = this.pageable_count;
		}
		this.pagination.empty();
		this.pagination.append('<li class="page-item previous"><a class="page-link" href="#pagination">Previous</a></li>');
		
		for(var i=this.startPage;i<=pageCount;i++) {
			this.pagination.append('<li class="page-item page"><a class="page-link" href="#pagination">'+i+'</a></li>');
		}
		this.pagination.append('<li class="page-item next"><a class="page-link" href="#pagination">Next</a></li>');
		this.pagination.find(".page").on("click", this, this.onPageClick);
		this.pagination.find(".previous").on("click", this, this.onPreviousClick);
		this.pagination.find(".next").on("click", this, this.onNextClick);
		
		if(isFirst) {
			this.setActiveSelector(this.pagination.find(".page").get(0));
		} else {
			var lastIndex = this.pagination.find(".page").length-1;
			this.setActiveSelector(this.pagination.find(".page").get(lastIndex));
		}
	};
	
	Pagination.prototype.changePage = function(isNext) {
		if(isNext) {
			if(this.endPage < this.pageable_count) {
				this.startPage = this.endPage+1;
				
				if(this.pageable_count % (this.endPage+PAGE_SIZE) >= 1) {
					this.endPage = this.endPage+PAGE_SIZE;
				} else {
					this.endPage = this.endPage + Math.ceil(this.pageable_count % this.endPage);
				}
				this.makePage(true);
			}
		} else {
			if(this.startPage > 0) {
				this.endPage = this.startPage-1;
				
				if(this.pageable_count % (this.startPage-PAGE_SIZE) >=0) {
					this.startPage = this.startPage - PAGE_SIZE;
				} else {
					this.startPage = this.startPage - Math.ceil(this.pageable_count % this.startPage);
				}
				this.makePage(false);
			}
		}
	};
	
	Pagination.prototype.removePage = function() {
		if(this.pagination.find("li").length > 0) {
			this.pagination.empty();
		}
	}
});
