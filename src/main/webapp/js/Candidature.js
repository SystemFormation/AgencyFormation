function viewLink(id,i){
    var id = id;
    var index = i;
    $.ajax({
        type: 'GET',
        data:{"idCandidato":id},
        url: 'ViewCandidaturaControl',
        success: function (data1){
            var cv =data1.substr(0,data1.indexOf('.'));
            var doc = data1.substr(data1.indexOf('.')+1,data1.length);
            if(doc.length>0){
                var x = document.getElementsByName("hrefDocumenti")[index];
                x.style.display = "block";
            }else{
                var x = document.getElementsByName("hrefDocumenti")[index];
                x.style.display = "none";
            }
            if(cv.length>0){
                var x = document.getElementsByName("hrefCurriculum")[index];
                x.style.display = "block";
            }else{
                var x = document.getElementsByName("hrefCurriculum")[index];
                x.style.display = "none";
            }

        }
    })
}

function view(i){
    var index = i;
    var x = document.getElementsByName("drop")[index];
    var setting = x.style.display;
    if(setting == "none"){
        x.style.display = "flex";
    }
    else{
        x.style.display = "none";
    }
}

function acceptCandidature(id){
    var id = id;
    $.ajax({
        type: 'GET',
        data:{"idCandidato":id},
        url: 'AcceptCandidatureControl',
        success: function (data){
            if(data== "1"){
                window.location.reload();
            }
        }
    })
}

function rejectCandidature(id){
    var id = id;
    $.ajax({
        type: 'GET',
        data:{"idCandidato":id},
        url: 'RejectCandidatureControl',
        success: function (data){
            if(data== "1"){
                window.location.reload();
            }
        }
    })
}
function open(index){
    var index = index;
    var x = document.getElementById("colloquio");
    var setting = x.style.display;
    if(setting == "none"){
        x.style.display = "block";
        console.log("ciao");
    }
    else{
        x.style.display = "none";
    }
}
