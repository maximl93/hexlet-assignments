package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment findById(@PathVariable Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment with id " + id +" not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment save(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping(path = "/{id}")
    public Comment update(@PathVariable Long id,
                       @RequestBody Comment newData) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment with id " + id +" not found"));
        comment.setBody(newData.getBody());
        return commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }

}
// END
