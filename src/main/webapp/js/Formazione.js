function view(){
    var x = document.getElementById("drop");
    var setting = x.style.display;
    if(setting == "none"){
        x.style.display = "flex";
    }
    else{
        x.style.display = "none";
    }
}
function viewLink(){
    $.ajax({
        type: 'GET',
        url: 'ViewMaterialeControl',
        success: function (data){
            console.log("ciao");
            if(data1== "1"){
                var x = document.getElementById("hrefDocumenti");
                x.style.display = "block";
            }else{
                var x = document.getElementById("hrefDocumenti");
                x.style.display = "none";
            }

        }
    })
}

function checkFileMateriale(){
    var fileUpload = document.getElementById("fileMateriale");
    var button = document.getElementById("uploadMateriale");
    if (fileUpload.files.length == 0) {
        $('#materialeNotUpload').css("display","inline");
        $('#materialeNotUpload').css("color","red");
        $('#materialeNotUpload').css("font-size","14px").html("<br>Seleziona un file");
    }
    else{
        button.setAttribute('type',"submit")
    }
}

function viewSkill() {
    var x = document.getElementById("addSkill");
    var setting = x.style.display;
    if (setting == "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}