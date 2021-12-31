function viewLink(id){
    var id = id;
    $.ajax({
        type: 'GET',
        data:{"idCandidato":id},
        url: 'ViewCandidaturaControl',
        success: function (data1){
            var cv =data1.substr(0,data1.indexOf('.'));
            var doc = data1.substr(data1.indexOf('.')+1,data1.length);
            if(doc.length>0){
                var x = document.getElementsByName("hrefDocumenti")[0];
                x.style.display = "block";
            }else{
                var x = document.getElementsByName("hrefDocumenti")[0];
                x.style.display = "none";
            }
            if(cv.length>0){
                var x = document.getElementsByName("hrefCurriculum")[0];
                x.style.display = "block";
            }else{
                var x = document.getElementsByName("hrefCurriculum")[0];
                x.style.display = "none";
            }

        }
    })
}

function view(){
    var x = document.getElementsByName("drop")[0];
    var settings = x.style.display;
    if(settings == 'none'){
        x.style.display = "flex";
    }
    else{
        x.style.display = "none";
    }
}

//window.getComputedStyle(x).display==="none"