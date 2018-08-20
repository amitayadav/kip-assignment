// Get the modal
var signupmodal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == signupmodal) {
        modal.style.display = "none";
    }
}

var loginmodal = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == loginmodal) {
        modal.style.display = "none";
    }
}