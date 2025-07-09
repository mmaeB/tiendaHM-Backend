package com.tiendahm.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private Integer idMenu;

    @NotNull
    @Size(max=20)
    private String icon;

    @NotNull
    @Size(max=50)
    private String name;

    @NotNull
    @Size(max=50)
    private String url;
}
