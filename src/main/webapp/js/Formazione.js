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
            if(data== "2"){
                var x = document.getElementById("hrefDocumenti");
                x.style.display = "block";
            }else{
                document.getElementById('materiale').removeAttribute("onclick");
                $('#noMateriale').css("display","inline");
                $('#noMateriale').css("color","red");
                $('#noMateriale').css("font-size","14px").html("<br>Al momento non è presente materiale");
            }
        }
    })
}
function deleteSpanMateriale(){
    $('#noMateriale').css("display","none");
}

function checkFileMateriale(index){
    var index = index;
    var fileUpload = document.getElementsByName("materiale")[index];
    var button = document.getElementsByName("uploadMateriale")[index];
    if (fileUpload.files.length == 0) {
        $('#materialeNotUpload').css("display","inline");
        $('#materialeNotUpload').css("color","red");
        $('#materialeNotUpload').css("font-size","14px").html("<br>Seleziona un file");
    }
    else{
        button.setAttribute('type',"submit");
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
function checkAlreadyUpload(idTeam,index){
    var idTeam = idTeam;
    var index = index;
    $.ajax({
        type: 'GET',
        data: {"idTeam":idTeam},
        url:'CheckMaterialeFormazione',
        success: function (data){
            if(data=="2"){
                var x = document.getElementsByName("formUpload")[index];
                x.style.display = "none";
            }
        }
    })
}