/**
 * 
 */
$(document).ready(function(){
		(function(){
				var bno=$("#Bno").val();
		$.getJSON("getAttachList",{bno:bno},function(arr){
		var str="";
		$(arr).each(function(i,attach){
			if(attach.filetype=="true"){// 이미지 파일이면,
			 	var fileCallPath =  encodeURIComponent( attach.uploadpath+ "/"+attach.uuid+"_"+attach.filename);
				str +="<li data-path='"+attach.uploadpath+"'";
				str +=" data-uuid='"+attach.uuid+"' data-filename='"+attach.filename+"' data-type='"+attach.filetype+"'>"
				str +="<div>";
				str += "<span> "+attach.filename+"</span>";
                str += "<button type='button' data-file=\'"+fileCallPath+"\' ";
                str += "data-type='image'>X</button><br>"
				str +="<img src='/resources/img/"+attach.uuid +"_"+attach.filename+"'>";
				str +="</div>";  
				str +="</li>";  
				}
			})
			$(".uploadResult ul").html(str);
		})//get제이슨
		   $(".uploadResult").on("click","button", function(e){
			   if(confirm("삭제하시겠습니까?")){
				var targetLi=(this).closest("li");
				targetLi.remove();
			}
		});
	})();//펑션
	
	var regex = new RegExp("(.*?)\.(jpg|jpeg|png|gif)$");
   var maxSize = 5242880; 

   function checkExtension(filename, fileSize) {

      if (fileSize >= maxSize) {
         alert("파일 사이즈 초과");
		$("input[type='file']").val(null);
         return false;
      }
      if (regex.test(filename)==false) {
         alert("해당 종류의 파일은 업로드할 수 없습니다.");
		$("input[type='file']").val(null);
         return false;
      }
      return true;
   } // checkExtension함수 끝
   $("input[name='uploadFile']").change(function(e) {

      var formData = new FormData();

      var inputFile = $("input[name='uploadFile']");

      var files = inputFile[0].files;

      console.log(files);

      for (var i = 0; i < files.length; i++) {

         if (!checkExtension(files[i].name, files[i].size)) {
            return false;
         }
     
         formData.append("uploadFile", files[i]);
      }

      $.ajax({
         url : '/uploadAjaxAction',
         processData : false,
         contentType : false,
         data : formData,
         type : 'POST',
         dataType : 'json',
         success : function(result) {
      
            console.log(result);
            
            showUploadResult(result);
      

         }
      }); //$.ajax
      
   }); //$("input[type='file']").change(function(e) { 이벤트 끝

   function showUploadResult(uploadResultArr){
    
         if(!uploadResultArr || uploadResultArr.length==0){
            return;
         }
         var uploadUL = $(".uploadResult ul");
         
         var str = "";
         
         $(uploadResultArr).each(function(i, obj){
           console.log(obj);
           
           if(obj.image){
               var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/"+obj.uuid +"_"+obj.fileName);
               str += "<li data-path='"+obj.uploadPath+"'";
               str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'"
               str +=" ><div>";
               str += "<span> "+ obj.fileName+"</span>";
               str += "<button type='button' data-file=\'"+fileCallPath+"\' ";
               str += "data-type='image'>X</button><br>"
//  			   str += "<img src='/resources/img"+fileCallPath+"'>";
               str += "</div>";
               str +="</li>";
            }else{ 
               var fileCallPath =  encodeURIComponent( obj.uploadpath+"/"+ obj.uuid +"_"+obj.fileName);               
                var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
                  
               str += "<li "
               str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
               str += "<span> "+ obj.fileName+"</span>";
               str += "<button type='button' id='Btn'"
               str += ">X</button><br>";
               str += "</div>";
               str +="</li>";
            }
           
         });
         
         uploadUL.append(str);
       }





 var formObj=$("form[role='form']");

   $("button").on("click",function(e){

      e.preventDefault();
      
      var oper=$(this).data("oper");
	  
	if(oper=='delete'){
		formObj.attr("action","delete")
	}else if(oper=='update'){
		var str="";
		 $(".uploadResult ul li").each(function(i,obj){
    	  var jobj=$(obj);
    	  
    	  str+="<input type='text' name='attachList["+i+"].filename' value='"+jobj.data("filename")+"'>"
    	  str+="<input type='text' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>"
    	  str+="<input type='text' name='attachList["+i+"].uploadpath' value='"+jobj.data("path")+"'>"
    	  str+="<input type='text' name='attachList["+i+"].filetype' value='"+jobj.data("type")+"'>"
 
    	  });
      	formObj.append(str).submit();
		}
      formObj.submit();
   })// $("button[type='submit']") 끝
   








	
})