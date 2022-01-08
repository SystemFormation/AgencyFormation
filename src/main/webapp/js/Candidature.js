function viewFile(id,i){
    var id = id;
    var index = i;
    $.ajax({
        type: 'GET',
        data:{"idCandidato":id},
        url: 'ViewCandidaturaControl',
        success: function (data1){
            var cv =data1.substr(0,data1.indexOf('.'));
            var doc = data1.substr(data1.indexOf('.')+1,data1.length);
            console.log(doc.length);
            if(doc.length>2){
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

function checkFile(i){
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

function acceptCandidature(id,index){
    var id = id;
    var index = index
    var date = document.getElementsByName("data1")[index];
    var time = document.getElementsByName("time")[index];
    $.ajax({
        type: 'GET',
        data:{"idCandidato":id,"data1":date.value,"time":time.value},
        url: 'AcceptCandidatureControl',
        success: function (data){
            if(data== "1"){
                window.location.reload();
            }else{
                window.location.replace('./LoginControl');
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
                console.log("ciao");
                window.location.reload();
            }
            else if(data=="2"){
                console.log("ciao 2")
                window.location.replace('./LoginControl');
            }
        }
    })
}

function openColloquio(index){
    var index = index;
    var x = document.getElementsByName("colloquio")[index];
    var setting = x.style.display;
    if(setting == "none"){
        x.style.display = "block";
    }
}
function closeColloquio(index) {
    var index = index;
    var x = document.getElementsByName("colloquio")[index];
    var setting = x.style.display;
    if (setting == "block") {
        x.style.display = "none";
    }
}