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