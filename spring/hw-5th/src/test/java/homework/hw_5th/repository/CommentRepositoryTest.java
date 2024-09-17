package homework.hw_5th.repository;

import homework.hw_5th.entity.Author;
import homework.hw_5th.entity.Board;
import homework.hw_5th.entity.Comment;
import homework.hw_5th.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void commentTest(){
        // 게시판 생성
        Board board = new Board();
        boardRepository.save(board);

        // 작성자1,2,3,4 생성
        Author author1 = new Author();
        author1.setName("작성자1");
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("작성자2");
        authorRepository.save(author2);

        Author author3 = new Author();
        author3.setName("작성자3");
        authorRepository.save(author3);

        Author author4 = new Author();
        author4.setName("작성자4");
        authorRepository.save(author4);

        // 작성자1의 게시글 생성
        Post savedPost = new Post();
        savedPost.setAuthor(author1);
        savedPost.setTitle("제목1");
        savedPost.setContent("내용1");
        savedPost.setBoard(board);
        postRepository.save(savedPost);

        // 작성자2,3,4의 댓글 생성
        Comment comment1 = new Comment();
        comment1.setContent("작성자2의 댓글1");
        comment1.setAuthor(author2);
        comment1.setPost(savedPost);
        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setContent("작성자2의 댓글2");
        comment2.setAuthor(author2);
        comment2.setPost(savedPost);
        commentRepository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setContent("작성자3의 댓글");
        comment3.setAuthor(author3);
        comment3.setPost(savedPost);
        commentRepository.save(comment3);

        Comment comment4 = new Comment();
        comment4.setContent("작성자4의 댓글");
        comment4.setAuthor(author4);
        comment4.setPost(savedPost);
        commentRepository.save(comment4);

        // 게시판의 게시글&댓글 조회
        Board savedBoard = boardRepository.findById(board.getId()).orElseThrow();
        for (Post post : savedBoard.getPosts()) {
            System.out.println("작성자 : " + post.getAuthor().getName());
            System.out.println("제목 : " + post.getTitle());
            System.out.println("내용 : " + post.getContent());

            // 각 게시글에 대한 댓글
            for(Comment comment : post.getComments()){
                System.out.println("------------------------------------");
                System.out.println("댓글 작성자 : " + comment.getAuthor().getName());
                System.out.println("댓글 내용 : " + comment.getContent());
            }
        }


    }
}