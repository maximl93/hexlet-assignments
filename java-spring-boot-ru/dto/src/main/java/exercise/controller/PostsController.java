package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> showAll() {
        return postRepository.findAll().stream()
                .map(this::transferToPostDTO)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public PostDTO showOne(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return transferToPostDTO(post);
    }

    private PostDTO transferToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        List<CommentDTO> comments = commentRepository.findByPostId(post.getId()).stream()
                                .map(this::transferToCommentDTO)
                                .toList();
        postDTO.setComments(comments);
        return postDTO;
    }

    private CommentDTO transferToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }
}
// END
