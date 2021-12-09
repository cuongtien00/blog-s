package com.codegym.controller;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogRestController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/search")
    public ResponseEntity<List<Blog>> resultSearch(@RequestParam String search){

        List<Blog> blogs;
        if (!search.isEmpty()){
            blogs = (List<Blog>) blogService.findAllByTittleContaining(search);
            return new ResponseEntity<>(blogs,HttpStatus.OK);
        }
        else {
            blogs= (List<Blog>) blogService.findAll();
            return new ResponseEntity<>(blogs,HttpStatus.OK);
        }

    }
@PostMapping
public ResponseEntity<Blog> addBlog(@RequestBody Blog blog){
        blogService.save(blog);
        return new ResponseEntity<>(blog,HttpStatus.OK);
}
    @GetMapping
    public ResponseEntity<Iterable<Blog>> listBlog(){
        return new ResponseEntity<>(blogService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Blog>> viewBlog(@PathVariable Long id) throws NotFoundException {
        Optional<Blog> blogOptional = blogService.findById(id);
        return new ResponseEntity<>(blogOptional,HttpStatus.OK);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable Long id) throws NotFoundException {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional.get(),HttpStatus.OK);
    }

    @GetMapping("/demo")
    public ResponseEntity<Blog> demo(@RequestParam String name){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
