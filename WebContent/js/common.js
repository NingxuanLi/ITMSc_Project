var request = null;

function createRequest(){
	
	try{
		request = new XMLHttpRequest(); //���IE7
	}catch(e){
		try{
			request = new ActiveXObject("Msxml2.XMLHTTP");//��Խ��°汾IE
		}catch(e){
    		try{
				request = new ActiveXObject("Microsoft.XMLHTTP");//IE6
    		}catch(e){
        		request = false;
    		}
		}
	}
	
}