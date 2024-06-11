package codeclowns.planny.planny.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegisterStatus {
    ACCOUNT_EXISTED("Tài khoản đã tồn tại"),
    SUCCEED("Đăng ký thành công"),
    FAILED("Đăng ký thất bại"),
    ERROR("Phát sinh lỗi"),
    PENDING("Đang chờ xác nhận");
    private String stateDescription;

}
