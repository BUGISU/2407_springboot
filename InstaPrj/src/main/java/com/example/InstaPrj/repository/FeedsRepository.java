package com.example.InstaPrj.repository;

import com.example.InstaPrj.entity.Feeds;
import com.example.InstaPrj.repository.search.SearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedsRepository extends JpaRepository<Feeds,Long>, SearchRepository {
//  select m.mno, avg(coalesce(r.grade,0)), count(r.reviewnum)
//  from movie m left outer join review r on m.mno = r.movie_mno
//  group by m.mno;

    // 영화에 대한 리뷰의 평점과 댓글 갯수를 출력
    @Query("select f, avg(coalesce(r.grade, 0)), count(distinct r) " +
            "from Feeds f left outer join Reviews r on r.feeds=f group by f ")
    Page<Object[]> getListPage(Pageable pageable);

    // 아래와 같은 경우 mi를 찾기 위해서 review 카운트 만큼 반복횟수도 늘어나는 문제점
    // mi의 inum이 가장 낮은 이미지 번호가 출력된다.
    // 영화와 영화이미지,리뷰의 평점과 댓글 갯수 출력
    @Query("select f, pt, avg(coalesce(r.grade, 0)), count(distinct r) from Feeds f " +
            "left outer join Photos pt on pt.feeds = f " +
            "left outer join Reviews   r  on r.feeds  = f group by f ")
    Page<Object[]> getListPageImg(Pageable pageable);

    // spring 3.x에서는 실행 안됨.
    @Query("select f,max(pt),avg(coalesce(r.grade, 0)),count(distinct r) from Feeds f " +
            "left outer join Photos pt on pt.feeds = f " +
            "left outer join Reviews   r  on r.feeds  = f group by f ")
    Page<Object[]> getListPageMaxImg(Pageable pageable);

    // Native Query = SQL
    @Query(value = "select f.fno, pt.pnum, pt.imgName, " +
            "avg(coalesce(r.grade, 0)), count(r.reviewnum) " +
            "from db7.movie_image pt left outer join db7.movie m on f.fno=pt.movie_mno " +
            "left outer join db7.review r on f.fno=r.movie_mno " +
            "where pt.pnum = " +
            "(select max(pnum) from db7.movie_image pt2 where pt2.movie_mno=f.fno) " +
            "group by f.fno ", nativeQuery = true)
    Page<Object[]> getListPageImgNative(Pageable pageable);

    // JPQL
    @Query("select f, pt, avg(coalesce(r.grade, 0)), count(distinct r) from Feeds f " +
            "left outer join Photos pt on pt.feeds = f " +
            "left outer join Review     r  on r.feeds  = f " +
            "where pnum = (select max(pt2.pnum) from Photos pt2 where pt2.feeds=f) " +
            "group by f ")
    Page<Object[]> getListPageImgJPQL(Pageable pageable);

    @Query("select f, max(pt.pnum) from Photos pt group by feeds")
    Page<Object[]> getMaxQuery(Pageable pageable);

    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(r) " +
            "from Feeds f left outer join MovieImage pt on pt.feeds=f " +
            "left outer join Reviews r on r.feeds = f " +
            "where f.fno = :fno group by pt ")
    List<Object[]> getMovieWithAll(Long fno); //특정 영화 조회
}
