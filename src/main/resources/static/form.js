function verify(id){
    var max = ($('#inputMax'+id).val());
    var min = ($('#inputMin'+id).val());
    var nbStrings = ($('#fnb'+id).val());


    if (!FormIsNull(+max, +min, +nbStrings)){
        if(minMaxValues(+max, +min)){
            nbCombinations(+max, +min, +nbStrings, id);
        }
    }
}

function  nbCombinations(max, min, nbStrings, id){

    //eles of current form
    //var eles_form = document.querySelectorAll('#letter'+id);

    //all checked eles
    var eles_Chk = document.querySelectorAll('input[type="checkbox"]:checked');
    var eles = [];
    var nbCombinations = 0;

    //eles checked of the current form"
    eles_Chk.forEach(element => {
        if (element.id === 'letter'+id) eles.push(element);
    });

    for (var i=min; i<=max;i++){
        nbCombinations = nbCombinations + Math.pow(eles.length,i);
    }

    if(nbStrings > nbCombinations){
        const error = "Error : number of strings ["+ nbStrings + "] is higher than the number of combinations [" + nbCombinations +"]" ;
        simpleAlert(error);
        return false;

    }else {
        alertify.success('Form is Clean');
        return true;
    }
}

function minMaxValues(max, min){

    if ((min != '') && (max != '')){
        try{
            max = parseInt(max);
            min = parseInt(min);

            if(max < min) {
                const msg = "The max input must be larger than the min input";
                simpleAlert(msg)
                return false;
            }
        }catch(e){
            return false;
        }
    }
    return true;
}

function FormIsNull(max, min, nbStrings){
    if (max == 0){
        simpleAlert("Max value should be grater than 0");
        return true;
    }else if(min == 0){
        simpleAlert("Min value should be grater than 0");
        return true;
    }else if (nbStrings == 0){
        simpleAlert("Number Of Strings returned should be grater than 0");
        return true;
        // check if at least one check box is heckeds
    }else return false;
} // check box!!

function simpleAlert(msg){
    alertify
        .alert(msg, function(){
            alertify.message('OK');
        });
}
