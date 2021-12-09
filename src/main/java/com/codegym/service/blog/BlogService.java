package com.codegym.service.blog;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.blog.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAllByCategory(Category category) {
        return blogRepository.findAllByCategory(category);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) throws NotFoundException {

          Optional<Blog> blog = blogRepository.findById(id);
          if (blog.isPresent()){
              return blog;
          }
          else {
              throw new NotFoundException();
          }

    }

    @Override
    public void save(Blog blog) {
blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
blogRepository.deleteById(id);
    }

    @Override
    public Iterable<Blog> findAllByTittleContaining(String content) {
        return blogRepository.findAllByTittleContaining(content);
    }
}
