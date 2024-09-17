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
class AuthorRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void postTest() {
        // author 생성
        Author author = new Author();
        author.setName("작성자1");
        authorRepository.save(author);

        // 작성자1의 게시글 생성
        Post post1 = new Post();
        post1.setTitle("제목1");
        post1.setContent("내용1");
        post1.setAuthor(author);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setTitle("제목2");
        post2.setContent("내용2");
        post2.setAuthor(author);
        postRepository.save(post2);

        // 작성자1의 모든 게시글 출력
        Author savedAuthor = authorRepository.findById(author.getId()).orElseThrow();
        for (Post post : savedAuthor.getPosts()) {
            System.out.println("작성자 이름 : " + post.getAuthor().getName());
            System.out.println("게시글 번호: " + post.getId());
            System.out.println("게시글 제목: " + post.getTitle());
            System.out.println("게시글 내용: " + post.getContent());
        }
    }

    @Test
    public void commentTest() {
        // 게시판 생성
        Board board = new Board();
        boardRepository.save(board);

        // 작성자1과 작성자2 생성
        Author author1 = new Author();
        author1.setName("작성자1");
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("작성자2");
        authorRepository.save(author2);

        // 각 작성자 게시글 생성
        Post post1 = new Post();
        post1.setAuthor(author1);
        post1.setTitle("작성자1의 게시글 제목");
        post1.setContent("작성자1의 게시글 내용");
        post1.setBoard(board);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setAuthor(author2);
        post2.setTitle("작성자2의 게시글 제목");
        post2.setContent("작성자2의 게시글 내용");
        post2.setBoard(board);
        postRepository.save(post2);

        // 서로의 게시글에 댓글 생성
        Comment comment1 = new Comment();
        comment1.setContent("작성자1의 댓글");
        comment1.setPost(post2);
        comment1.setAuthor(author1);
        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setContent("작성자2의 댓글");
        comment2.setPost(post1);
        comment2.setAuthor(author2);
        commentRepository.save(comment2);

        // 게시글과 댓글 데이터 조회
        Board savedBoard = boardRepository.findById(board.getId()).orElseThrow();
        for(Post post : savedBoard.getPosts()) {
            System.out.println("------------------------------------");
            System.out.println("작성자 : " + post.getAuthor().getName());
            System.out.println("제목 : " + post.getTitle());
            System.out.println("내용 : " + post.getContent());

            // 각 게시글에 대한 댓글
            for(Comment comment : post.getComments()){
                System.out.println("댓글 작성자 : " + comment.getAuthor().getName());
                System.out.println("댓글 내용 : " + comment.getContent());
            }
        }
    }
}