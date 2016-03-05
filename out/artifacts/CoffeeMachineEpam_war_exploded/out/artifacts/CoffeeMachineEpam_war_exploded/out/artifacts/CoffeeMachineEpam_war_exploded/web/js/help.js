function checkPass() {
    var password = document.getElementById('pwd');
    var confirmPassword = document.getElementById('confPwd');

    if (password!=confirmPassword){
        alert("Mismatch");
    }
}