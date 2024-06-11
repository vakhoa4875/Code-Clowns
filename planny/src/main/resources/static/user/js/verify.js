const validateForm = () => {
    let email = $('#email').val();
    if (!email || email === '') {
        Swal.fire({
            text: 'Email không được trống',
            icon: 'error'
        });
        return false;
    }
    return true;
}
const btnRegister = async () => {
    if (!validateForm()) {
        return;
    }
    let email = $('#email').val();
    try {
        let response = await axios.get(`/api-public/account/verify?email=${email}`);
        if (response.status === 200) {
            if (response.data === 'User verified successfully.') {
                Swal.fire({
                    title: 'Chào mừng bạn đến với Planny!',
                    text: 'Xác nhận thành công. Đang chuyển hướng đến trang đăng nhập...',
                    icon: 'success',
                }).then(() => {
                    // Chuyển hướng đến trang đăng nhập
                    window.location.href = '/login';
                });
            } else {
                Swal.fire({
                    title: 'Xác thực thất bại!',
                    text: response.data,
                    icon: 'error'
                });
            }
        } else {
            Swal.fire({
                title: 'Đã xảy ra lỗi',
                text: 'Vui lòng thử lại sau',
                icon: 'error'
            });
        }
    } catch (error) {
        Swal.fire({
            title: 'Đã xảy ra lỗi',
            text: 'Vui lòng thử lại sau',
            icon: 'error'
        });
    }
}

$('#signupForm').on('submit', (event) => {
    event.preventDefault();
    btnRegister();
});
