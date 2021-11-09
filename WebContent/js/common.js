var request = null;

function createRequest(){
	
	try{
		request = new XMLHttpRequest(); //针对IE7
	}catch(e){
		try{
			request = new ActiveXObject("Msxml2.XMLHTTP");//针对较新版本IE
		}catch(e){
    		try{
				request = new ActiveXObject("Microsoft.XMLHTTP");//IE6
    		}catch(e){
        		request = false;
    		}
		}
	}
	
}