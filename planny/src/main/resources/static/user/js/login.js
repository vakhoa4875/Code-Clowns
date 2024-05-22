document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    alert('Login form submitted!');
});
function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId());
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail());
    var id_token = googleUser.getAuthResponse().id_token;
    console.log('ID Token: ' + id_token);
    alert('Google Sign-In successful!');
}