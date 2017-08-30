<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>

<body>
    <!--Step:1 Prepare a dom for ECharts which (must) has size (width & hight)-->
    <!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
    <div id="main" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
    <div id="mainMap" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
    
    <!--Step:2 Import echarts.js-->
    <!--Step:2 引入echarts.js-->
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/echarts/echarts.js"></script>
    <script src="js/echarts/echarts-init.js"></script>
    
    <script type="text/javascript">
		$.ajax({
			url:"echarts/test",
			data:{},
			dataType:"json",
			type:"post",
			cache:false,
			success:function(data){
				loadData(data);
			},
			error:function(er){
				alert("error")
			}
		})
    
		function loadData(datas){
			var doc = document.getElementById("main");
	    	var option = {
	    			tooltip : {
	    				trigger : 'axis'
	    			},
	    			legend : {
	    				data : datas.legendData //[ '蒸发量', '降水量' ]
	    			},
	    			toolbox : {
	    				show : true,
	    				feature : {
	    					mark : {
	    						show : true
	    					},
	    					dataView : {
	    						show : true,
	    						readOnly : false
	    					},
	    					magicType : {
	    						show : true,
	    						type : [ 'line', 'bar' ]
	    					},
	    					restore : {
	    						show : true
	    					},
	    					saveAsImage : {
	    						show : true
	    					}
	    				}
	    			},
	    			calculable : true,
	    			xAxis : [ {
	    				type : 'category',
	    				data :  datas.xAxisData // [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ]
	    			} ],
	    			yAxis : [ {
	    				type : 'value',
	    				splitArea : {
	    					show : true
	    				}
	    			} ],
	    			series : [
	    					{
	    						name : datas.legendData[0], //'蒸发量',
	    						type : 'bar',
	    						data : datas.series0 //[ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2,32.6, 20.0, 6.4, 3.3 ]
	    					},
	    					{
	    						name : datas.legendData[1], //'降水量',
	    						type : 'bar',
	    						data : datas.series1 //[ 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2,48.7, 18.8, 6.0, 2.3 ]
	    					} ]
	    		};
	    
	    		//load(doc,option);
	    		var docs = [{"doc":doc,"option":option},{"doc":document.getElementById("mainMap"),"option":option}];
	    		//E.load(doc, option);
	    		E.loads(docs);
		}
    		
    </script>
</body>
</html>