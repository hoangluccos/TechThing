package com.learning.service;

import java.util.List;

import com.learning.model.Image;
import com.learning.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Image getById(Integer id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public void delete(Image image) {
        imageRepository.delete(image);
    }

    @Override
    public List<Image> getImagesByProductId(Integer productId) {
        return imageRepository.findImagesByProductId(productId);
    }

}