window.utils = (function(){
	return {
		"getContextPath": function(){
			var hostIndex = location.href.indexOf( location.host ) + location.host.length;
			return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
		},
		"getParams" : function(){
			// 파라미터가 담길 배열
		    var param = {};
		 
		    // 현재 페이지의 url
		    var url = decodeURIComponent(location.href);
		    // url이 encodeURIComponent 로 인코딩 되었을때는 다시 디코딩 해준다.
		    url = decodeURIComponent(url);
		 
		    var params;
		    // url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
		    if(url.indexOf('?') !== -1){
		    	params = url.substring( url.indexOf('?')+1, url.length );
			    
			    // 파라미터 구분자("&") 로 분리
			    params = params.split("&");

			    // params 배열을 다시 "=" 구분자로 분리하여 param 배열에 key = value 로 담는다.
			    var size = params.length;
			    var key, value;
			    for(var i=0 ; i < size ; i++) {
			        key = params[i].split("=")[0];
			        value = params[i].split("=")[1];
			        
			        param[key] = value;
			    }
		    }
		    return param;
		},
		"getWebSocketHost" : function(){
			// 채팅용 웹소켓 포트
			return "ws://localhost:8080";
		},
		"popupCenter" : function(url, title, w, h){
			// Fixes dual-screen position                         Most browsers      Firefox
		    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
		    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;
		 
		    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
		    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;
		 
		    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
		    var top = ((height / 2) - (h / 2)) + dualScreenTop;
		    var newWindow = window.open(url, title, 'width=' + w + ', height=' + h + ', top=' + top + ', left=' + left + ',menubar-no, toolbar=no, location=no, scrollbars=no, status=no, resizable=no, fullscreen=no, channelmode=no');
		 
		    // Puts focus on the newWindow
		    if (window.focus) {
		        newWindow.focus();
		    }
		}
	};
})();

// 객체에 사이즈 메서드 추가
Object.size = function(obj) {
    var size = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) size++;
    }
    return size;
};



