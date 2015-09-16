function modifyDatabaseFlag(rowId,butid){
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
			rowId.style.backgroundColor = "red";
			butid.style.visibility='hidden';
	    }
	  }
	xmlhttp.open("GET","/OSF/ViewQuestionsServlet?flag=deleted&id="+rowId.getAttribute("id"),true);
	xmlhttp.send();
	
}

