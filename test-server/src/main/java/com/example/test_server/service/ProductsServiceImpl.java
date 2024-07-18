package com.example.test_server.service;

import com.example.test_server.dto.PageRequestDTO;
import com.example.test_server.dto.PageResultDTO;
import com.example.test_server.dto.ProductsDTO;
import com.example.test_server.entity.Products;
import com.example.test_server.entity.QProducts;
import com.example.test_server.repository.ProductsRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {

  private final ProductsRepository productsRepository;

  @Override
  public Long register(ProductsDTO dto) {
    Products products = dtoToEntity(dto);
    productsRepository.save(products);
    return products.getPno();
  }

  @Override
  public PageResultDTO<ProductsDTO, Products> getList(PageRequestDTO pageRequestDTO) {
    // 알고자하는 페이지 요청에 대한 정보 객체(번호, 갯수, 정렬, 검색타입, 검색키워드)
    Pageable pageable = pageRequestDTO.getPageable(Sort.by("pno").descending());

    // 동적 검색(QueryDSL)의 조건이 담긴 객체 :: BooleanBuilder
    BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

    Page<Products> result = productsRepository.findAll(booleanBuilder, pageable);

    Function<Products, ProductsDTO> fn = new Function<Products, ProductsDTO>() {
      @Override
      public ProductsDTO apply(Products products) {
        return entityToDto(products);
      }
    };

    return new PageResultDTO<>(result, fn);
  }

  private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
    String type = pageRequestDTO.getType();
    String keyword = pageRequestDTO.getKeyword();
    // 동적 검색을 하기 위한 객체 생성
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    QProducts qProducts = QProducts.products; // 검색 대상인 QGuestbook
    BooleanExpression expression = qProducts.pno.gt(0L);//전체조건부여
    booleanBuilder.and(expression); // 첫번째 조건을 적용

    BooleanBuilder conditionBuilder = new BooleanBuilder(); // 세부 검색 조건 객체
    if(type == null || type.trim().length() == 0) return booleanBuilder;
    if(type.contains("t")) conditionBuilder.or(qProducts.pname.contains(keyword));
    if(type.contains("c")) conditionBuilder.or(qProducts.pmodel.contains(keyword));
    if(type.contains("w")) conditionBuilder.or(qProducts.pmaker.contains(keyword));
    booleanBuilder.and(conditionBuilder);
    return booleanBuilder;
  }

  @Override
  public ProductsDTO read(Long pno) {
    // 단수의 데이터를 처리할 때 : findById를 통해서 유일한 하나의 객체를 찾아보는 것.
    // Optional의 특징 : null 값을 받아도 에러가 발생하지 않고, 형변환 안해도 안전
    Optional<Products> result = productsRepository.findById(pno);
    return result.isPresent() ? entityToDto(result.get()) : null;
  }

  @Override
  public void modify(ProductsDTO productsDTO) {
    // 수정하기위해서는 먼저 해당 항목을 검색하여 객체를 획득한 후에 필요 부분만 수정.
    Optional<Products> result = productsRepository.findById(productsDTO.getPno());
    if (result.isPresent()) {
      Products products = result.get();
      products.changePname(productsDTO.getPname());
      products.changePmodel(productsDTO.getPmodel());
      products.changePmaker(productsDTO.getPmaker());
      productsRepository.save(products);
    }
  }

  @Override
  public void remove(ProductsDTO productsDTO) {
    Optional<Products> result = productsRepository.findById(productsDTO.getPno());
    if (result.isPresent()) {
      productsRepository.deleteById(productsDTO.getPno());
    }
  }
}
