function controlCandidates(){
    $.ajax({
        type:'GET',
        url: 'ViewCandidatiControl',
        success: function (data){
            if(data == "2"){
                $('#noCandidati').css("display","inline");
                $('#noCandidati').css("color","red");
                $('#noCandidati').css("font-size","14px").html("Non ci sono candidati");
                var link = document.getElementById("viewCandidates");
                link.setAttribute('href', "#");
                return false;
            }
            else{
                $('#noCandidati').css("display","none");
            }
        }
    })
}

function deleteSpan(){
    $('#noCandidati').css("display","none");
}

function checkFileCurriculum(){
    var fileUpload = document.getElementById("fileCurriculum");
    var button = document.getElementById("uploadCurriculum");
    if (fileUpload.files.length == 0) {
        $('#curriculumNotUpload').css("display","inline");
        $('#curriculumNotUpload').css("color","red");
        $('#curriculumNotUpload').css("font-size","14px").html("<br>Seleziona un file");
    }
    else{
        button.setAttribute('type',"submit")
    }
}

function checkFileDocumenti(){
    var fileUpload = document.getElementById("fileDocumenti");
    var button = document.getElementById("uploadDocumenti");
    if (fileUpload.files.length == 0) {
        $('#documentNotUpload').css("display","inline");
        $('#documentNotUpload').css("color","red");
        $('#documentNotUpload').css("font-size","14px").html("<br>Seleziona un file");
    }
    else{
        button.setAttribute('type',"submit")
    }
}