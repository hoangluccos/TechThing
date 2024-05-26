package com.learning.service;

import com.learning.model.Image;

import java.util.List;

public interface ImageService {
    void save(Image image);
    Image getById(Integer id);
    List<Image> getAll();
    void delete(Image image);
    List<Image> getImagesByProductId(Integer productId);
}