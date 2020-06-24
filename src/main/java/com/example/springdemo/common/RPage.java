package com.example.springdemo.common;

import java.util.List;

import com.github.pagehelper.Page;

import lombok.Data;

@Data
public class RPage<T> {

  private Long total;
  private List<T> list;

  public RPage(Long total, List<T> list) {
      this.total = total;
      this.list = list;
  }

  public RPage(List<T> list) {
      this.list = list;
  }

  /**
   *
   * @param page IPage对象
   */
  public static <T> RPage setPage(List<?> data)
  {
      return new RPage(((Page)data).getTotal(),data);
  }
}
