/**
 * 
 */
$(document).ready(function(e){

   var formObj=$("form[role='form']");

   $("input[type='submit']").on("click",function(e){

      e.preventDefault();
    	
			var title=$("#title").val();
			var text=$("#text").val();
			
		   	if(title.length==0){
				alert("제목을 입력해주세요.");
				return false;
			} else if(text.length==0){
				alert("내용을 입력해주세요.");
				return false;
			}
			
      var str="";
      $(".uploadResult ul li").each(function(i,obj){
    	  var jobj=$(obj);
    	  
    	  str+="<input type='text' name='attachList["+i+"].filename' value='"+jobj.data("filename")+"'>"
    	  str+="<input type='text' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>"
    	  str+="<input type='text' name='attachList["+i+"].uploadpath' value='"+jobj.data("path")+"'>"
    	  str+="<input type='text' name='attachList["+i+"].filetype' value='"+jobj.data("type")+"'>"
      })
	var tstr="";
      $(".tuploadResult ul li").each(function(i,obj){
    	  var jobj=$(obj);
    	  
				
		  tstr+="<input type='text' id='try' name='t' value='/resources/thumb"+"/s_"+jobj.data("uuid")+"_"+jobj.data("filename")+"'>"
      })
	  formObj.append(tstr).submit();
      formObj.append(str).submit();
//      formObj.append(tstr);
//      formObj.append(str);
   })// $("button[type='submit']") 끝
   
   var regex = new RegExp("(.*?)\.(jpg|jpeg|png|gif)$");
   var maxSize = 5242880; 

   function checkExtension(filename, fileSize) {
		
      if (regex.test(filename)==false) {
         alert("해당 종류의 파일은 업로드할 수 없습니다.");
		$("input[type='file']").val(null);
         return false;
      }
      if (fileSize >= maxSize) {
         alert("파일 사이즈 초과");
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
      
            
            showUploadResult(result);
      

         },
		error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
      }); //$.ajax
      
   }); //$("input[type='file']").change(function(e) { 이벤트 끝.

$("input[name='tuploadFile']").change(function(e) {

      var formData = new FormData();

      var inputFile = $("input[name='tuploadFile']");

      var files = inputFile[0].files;

      console.log(files);

      for (var i = 0; i < files.length; i++) {
         // checkExtension함수 호출
         if (!checkExtension(files[i].name, files[i].size)) {
            return false;
         }
     
         formData.append("tuploadFile", files[i]);
      }

      $.ajax({
         url : '/uploadAjaxActions',
         processData : false,
         contentType : false,
         data : formData,
         type : 'POST',
         dataType : 'json',
         success : function(result) {
      
            console.log(result);
            
            tshowUploadResult(result);
      

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
  			   str += "<img src='/resources/img"+"/"+obj.uuid +"_"+obj.fileName+"'>";
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

   function tshowUploadResult(uploadResultArr){
    
         if(!uploadResultArr || uploadResultArr.length==0){
            return;
         }
         var uploadUL = $(".tuploadResult ul");
         
         var str = "";
         
         $(uploadResultArr).each(function(i, obj){
           console.log(obj);
           
           if(obj.image){
               var fileCallPath =  encodeURIComponent( "\\thumb/s_"+obj.uuid +"_"+obj.fileName);
               str += "<li data-path='\\thumb'";
               str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'"
               str +=" ><div>";
               str += "<span> "+ obj.fileName+"</span>";
               str += "<button type='button' data-file=\'"+fileCallPath+"\' ";
               str += "data-type='image'>X</button><br>"
//			   str += "<img src='/resources/img"+fileCallPath+"'>";
               str += "</div>";
               str +="</li>";
            } 
         });
         
         uploadUL.append(str);
       }
   $(".uploadResult").on("click","button", function(e){
	   
		  var targetFile = $(this).data("file");
		  var type = $(this).data("type");
		  
		  console.log(targetFile);
		  console.log(type);
			  var targetLi=(this).closest("li");
		  
		  $.ajax({
		    url: '/deleteFile',
		    data: {fileName: targetFile, type:type},
		    dataType:'text',
		    type: 'POST',
		      success: function(result){
				console.log(result);
		         targetLi.remove();
		       }
		  }); //$.ajax
		  
		});
		
		 $(".tuploadResult").on("click","button", function(e){
	   
		  var targetFile = $(this).data("file");
		  var type = $(this).data("type");
		  
		  console.log(targetFile);
			  var targetLi=(this).closest("li");
		  
		  $.ajax({
		    url: '/tdeleteFile',
		    data: {fileName: targetFile, type:type},
		    dataType:'text',
		    type: 'POST',
		      success: function(result){
		         alert(result);
		         targetLi.remove();
		       }
		  }); //$.ajax
		  
		});
   
   
   
})