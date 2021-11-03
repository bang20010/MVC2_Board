function insertReply() {

   var data = {
      comment: document.getElementById("comment").value,
      num: document.getElementById("num").value
   };

 	var path = '<%=request.getContextPath()%>';

   if ($("#comment").val() == "" || $("#comment").val() == null) {
      alert("내용을 입력해주세요.");
      $("#comment").focus();
      return false;
   }

   $.ajax({
      url: path+"/controller?command=content_write",
      type: "GET",
      data: { "comment": data.comment, "num": data.num },
      contentType: "application/json; charset=utf-8;",
      dataType: "text",
      success: function() {

         location.reload();
      }
   });
};
