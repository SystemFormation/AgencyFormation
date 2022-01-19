function view() {
    var x = document.getElementById("drop");
    var setting = x.style.display;
    if (setting == "none") {
        x.style.display = "flex";
    } else {
        x.style.display = "none";
    }
}

function viewLink() {
    $.ajax({
        type: 'GET',
        url: 'ViewMaterialeControl',
        success: function (data) {
            if (data == "2") {
                var x = document.getElementById("hrefDocumenti");
                x.style.display = "block";
            } else {
                document.getElementById('materiale').removeAttribute("onclick");
                $('#noMateriale').css("display", "inline");
                $('#noMateriale').css("color", "red");
                $('#noMateriale').css("font-size", "14px").html("<br>Al momento non &egrave; presente materiale");
            }
        }
    })
}

function deleteSpanMateriale() {
    $('#noMateriale').css("display", "none");
}

function checkFileMateriale(index) {
    var index = index;
    var fileUpload = document.getElementsByName("materiale")[index];
    var button = document.getElementsByName("uploadMateriale")[index];
    if (fileUpload.files.item(0).size == 0) {
        $('#curriculumNotUpload').css("display", "inline");
        $('#curriculumNotUpload').css("color", "red");
        $('#curriculumNotUpload').css("font-size", "14px").html("<br>Seleziona un file");
    } else if (fileUpload.files.item(0).size > 10485760) {
        $('#curriculumNotUpload').css("display", "inline");
        $('#curriculumNotUpload').css("color", "red");
        $('#curriculumNotUpload').css("font-size", "14px").html("<br>File troppo grande");
    } else {
        button.setAttribute('type', "submit")
    }
}

function viewAddSkill() {
    var x = document.getElementById("addSkill");
    var setting = x.style.display;
    if (setting == "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function viewSpecifySkills(i) {
    var indexSkill = i;
    var x = document.getElementsByName("drop")[indexSkill];
    var setting = x.style.display;
    if (setting == "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function viewCompetenze(i) {
    var index = i;
    var x = document.getElementsByName("drop")[index];
    var y = document.getElementsByName("drop-button")[index];
    var setting = x.style.display;
    var setButton = y.style.display;
    if (setting == "none" && setButton == "inline") {
        x.style.display = "inline";
        y.style.display = "none";
    }
}

function viewCaricamento(i) {
    var index = i;
    var x = document.getElementsByName("drop-carica")[index];
    var y = document.getElementsByName("dropButton")[index];
    var setting = x.style.display;
    var setButton = y.style.display;
    if (setting == "none" && setButton !="none") {
        x.style.display = "inline";
        y.style.display = "none";
    }
}

function viewSkill(i) {
    var index = i;
    var x = document.getElementsByName("drop")[index];
    var setting = x.style.display;
    if (setting == "none") {
        x.style.display = "inline";
    } else
        x.style.display = "none";
}

function scioglimentoTeam(i){
    var index = i;
    var x = document.getElementsByName("conferma-scioglimento")[index];
    var setting = x.style.display;
    if (setting == "none") {
        x.style.display = "inline";
    } else
        x.style.display = "none";
}