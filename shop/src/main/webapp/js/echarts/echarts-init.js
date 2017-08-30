require.config({
	paths : {
		echarts : './js/echarts'
	}
});

/**
 * meixl
 * @param doc
 * @param option
 */

//单个
function load(doc,option){
	require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line','echarts/chart/map' ], function(ec) {
		// --- 折柱 ---
		ec.init(doc).setOption(option);
	})
}

//多个
function loads(docs){
	require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line','echarts/chart/map' ], function(ec) {
		// --- 折柱 ---
		for(var i=0;i<docs.length;i++){
			ec.init(docs[i].doc).setOption(docs[i].option);
		}
	})
}
var Echarts = {"load":load,"loads":loads};
var E = {"load":load,"loads":loads};

