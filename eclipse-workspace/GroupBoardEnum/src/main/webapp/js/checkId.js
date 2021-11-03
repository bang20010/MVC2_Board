function checkId()
{ 
    var path = '<%=request.getContextPath()%>';        
    var idCheck = document.getElementById("id").value;
    
            
    window.open(path+"/controller?command=checkId&id="+idCheck,"","width=300, height=200, top=300, left=1000","_blank");


}