function resetError(selector){
    $(selector + " *").removeClass("ui-state-error");
}

function start() {
    statusDialog.show();
}

function stop() {  
   statusDialog.hide();
}