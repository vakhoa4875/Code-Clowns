package codeclowns.planny.planny.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BasicApiConstant {

    SUCCEED("Thành công"),
    FAILED("Thất bại"),
    ERROR("Phát sinh lỗi");

    private final String status;
}
