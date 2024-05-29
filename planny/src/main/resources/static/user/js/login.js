// document.getElementById('loginForm').addEventListener('submit', function (event) {
//     event.preventDefault();
//     alert('Login form submitted!');
// });

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

const status = {
    succeed: 'Thành công',
    failed: 'Thất bại',
    error: 'Phát sinh lỗi'
}
login = async () => {
    let email = $('#email').val();
    let password = $('#password').val();
    let data = {
        email: email,
        username: email,
        password: password
    }
    await axios
        .post("/api-public/account/login", data, {
            header: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            let resp = response.data;
            Swal.fire({
                title: resp.status,
                text: resp.message,
                icon:
                    resp.status === status.succeed ? 'success'
                        : resp.status === status.failed ? 'warning'
                            : 'error'
            }).then(result => {
                if (result.isConfirmed && resp.status === status.succeed) {
                    window.location.href = '/home';
                }
            })
        })
        .catch(error => {
            Swal.fire({
                title: status.error,
                icon: 'error'
            })
            console.dir(error);
        });
}
$('#loginForm').on('submit', function (event) {
    event.preventDefault(); // Prevent the default form submission
    login().then(); // Call your login function
});
