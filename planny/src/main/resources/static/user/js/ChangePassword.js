class ChangePassword {
    changePassword = async () => {
        const currentPassword = $('#matKhauHienTai').val()
        const newPassword = $('#matKhauMoi').val()
        const confirmNewPassword = $('#xacNhanMatKhauMoi').val()

        if (newPassword !== confirmNewPassword) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Mật khẩu mới không khớp!',
            })
            return
        }

        let {data: response} = await axios.post('/api-public/account/change-password', {
            currentPassword: currentPassword,
            newPassword: newPassword,
            confirmNewPassword: confirmNewPassword
        })
        if(response.data) {
            Swal.fire({
                icon: 'success',
                title: 'Thành công',
                text: response.message,
            }).then(() => {
                window.location.href = '/home'
            })
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: response.message,
            })
        }
    }
}
