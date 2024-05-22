package com.quocdat.java5.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SachDTO {
    @NotNull(message = "Không được để trống Ma Sach")
    private String maSach;

    @NotBlank(message = "Không được để trống Ten Sach")
    private String tenSach;

    @NotNull(message = "Không được để trống gpa")
    @DecimalMin(value = "1", message = "gia phai lon hon 0")
    private Double gia;

    @NotBlank(message = "Không được để trống loai sach")
    private String loaiSach;

}
