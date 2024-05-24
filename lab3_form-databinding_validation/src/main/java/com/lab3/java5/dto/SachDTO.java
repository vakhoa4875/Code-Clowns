package com.lab3.java5.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SachDTO {
    @NotNull(message = "Không được để trống Mã Sách!")
    private String maSach;

    @NotBlank(message = "Không được để Tên Sách rỗng!")
    private String tenSach;

    @NotNull(message = "Không được để trống Giá sách!")
    @DecimalMin(value = "0", message = "Giá sách phải lớn hoặc bằng 0!")
    private Double gia;

    @NotBlank(message = "Không được để Loại sách rỗng!")
    private String loaiSach;

}
