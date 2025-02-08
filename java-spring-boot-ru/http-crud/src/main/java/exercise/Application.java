package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> allPosts(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer per) {
        return posts.stream().skip((page * per) - per).limit(per).toList();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> onePost(@PathVariable String id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post newPost) {
        posts.add(newPost);
        return newPost;
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable String id,
                           @RequestBody Post editedPost) {
        Optional<Post> savedPost = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (savedPost.isPresent()) {
            Post post = savedPost.get();
            post.setId(editedPost.getId());
            post.setTitle(editedPost.getTitle());
            post.setBody(editedPost.getBody());
        }
        return editedPost;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
    // END
}
