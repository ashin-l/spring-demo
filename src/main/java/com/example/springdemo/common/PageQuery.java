package com.example.springdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery {

    /**
     * 设置分页查询默认值
     */
    private Integer currentPage = 1;

    private Integer pageSize = 10 ;

}
