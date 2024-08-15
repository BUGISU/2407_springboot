package com.example.InstaPrj.service;

import com.example.InstaPrj.dto.FeedsDTO;
import com.example.InstaPrj.dto.PageRequestDTO;
import com.example.InstaPrj.dto.PageResultDTO;
import com.example.InstaPrj.dto.PhotosDTO;
import com.example.InstaPrj.entity.Feeds;
import com.example.InstaPrj.entity.Photos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface FeedsService {
  Long register(FeedsDTO feedsDTO);

  PageResultDTO<FeedsDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

  FeedsDTO getFeeds(Long fno);

  void modify(FeedsDTO feedsDTO);

  List<String> removeWithReviewsAndPhotos(Long pno);

  void removeUuid(String uuid);

  default Map<String, Object> dtoToEntity(FeedsDTO feedsDTO) {
    Map<String, Object> entityMap = new HashMap<>();
    Feeds feeds = Feeds.builder().fno(feedsDTO.getFno())
        .title(feedsDTO.getContent()).build();
    entityMap.put("feeds", feeds);
    List<PhotosDTO> photosDTOList = feedsDTO.getPhotosDTOList();
    if (photosDTOList != null && photosDTOList.size() > 0) {
      List<Photos> movieImageList = photosDTOList.stream().map(
          new Function<PhotosDTO, Photos>() {
            @Override
            public Photos apply(PhotosDTO movieImageDTO) {
              Photos photos = Photos.builder()
                  .path(movieImageDTO.getPath())
                  .imgName(movieImageDTO.getImgName())
                  .uuid(movieImageDTO.getUuid())
                  .feeds(feeds)
                  .build();
              return photos;
            }
          }
      ).collect(Collectors.toList());
      entityMap.put("movieImageList", movieImageList);
    }
    return entityMap;
  }

  default FeedsDTO entityToDto(Feeds feeds, List<Photos> movieImageList
      , Double avg, Long reviewsCnt) {
    FeedsDTO feedsDTO = FeedsDTO.builder()
        .fno(feeds.getFno())
        .title(feeds.getTitle())
        .regDate(feeds.getRegDate())
        .modDate(feeds.getModDate())
        .build();
    List<PhotosDTO> photosDTOList = new ArrayList<>();
    if(movieImageList.toArray().length > 0 && movieImageList.toArray()[0] != null) {
      photosDTOList = photosDTOList.stream().map(
          movieImage -> {
            PhotosDTO photosDTO = PhotosDTO.builder()
                .imgName(movieImage.getImgName())
                .path(movieImage.getPath())
                .uuid(movieImage.getUuid())
                .build();
            return photosDTO;
          }
      ).collect(Collectors.toList());
    }
    feedsDTO.setPhotosDTOList(photosDTOList);
    return feedsDTO;
  }
}
