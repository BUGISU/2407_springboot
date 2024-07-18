package com.example.test_server.respository;

import com.example.test_server.entity.Products;
import com.example.test_server.entity.QProducts;
import com.example.test_server.repository.ProductsRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@SpringBootTest
class ProductsRepositoryTests {

  @Autowired
  private ProductsRepository productsRepository;

  @Test
  public void testRepository() {
    System.out.println(">>"+productsRepository.getClass().getName());
  }

  @Test
  public void insertDummies() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Products products = Products.builder()
          .pname("제품" + i)
          .pmodel("모델" + i)
          .pmaker("제조"+i)
          .price(i)
          .build();
      System.out.println(productsRepository.save(products));
    });
  }
  @Test
  public void testUpdate() {
    Optional<Products> result = productsRepository.findById(300L);
    if (result.isPresent()) {
      Products products = result.get();
      products.changePname("Changed Pname...");
      products.changePmodel("Changed Pmodel...");
      products.changePmaker("Changed Pmaker...");

      productsRepository.save(products);
    }
  }

  //단일 항목 검색
  @Test
  public void testQuery1() {
    // Pageable :: 페이지 정보(첫페이지,갯수,정렬)를 가진 객체
    Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
    // 동적 질의를 위한 Guestbook 객체의 QGuestbook 준비
    QProducts qProducts = QProducts.products;
    // 검색할 변수
    String keyword = "1";
    // 검색을 실행할 객체
    BooleanBuilder builder = new BooleanBuilder();
    // 검색 조건 설정할 객체
    BooleanExpression expression = qProducts.pname.contains(keyword);
    // 검색 실행 준비 완료
    builder.and(expression);
    // 검색 실행 후 Page 객체에 담음.
    Page<Products> result = productsRepository.findAll(builder, pageable);
    result.stream().forEach(new Consumer<Products>() {
      @Override
      public void accept(Products products) {
        System.out.println(products);
      }
    });
  }

  // 다중 항목 검색
  @Test
  public void testQuery2() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
    QProducts qProducts = QProducts.products;
    String keyword = "1";
    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression exName = qProducts.pname.contains(keyword);
    BooleanExpression exModel = qProducts.pmodel.contains(keyword);
    BooleanExpression exAll = exName.or(exModel);
    builder.and(exAll);
    builder.and(qProducts.pno.gt(0L)); //형식적이지만 추가해서 조건을 온전하게 함.
    Page<Products> result = productsRepository.findAll(builder, pageable);
    result.stream().forEach(products -> System.out.println(products));
  }

}