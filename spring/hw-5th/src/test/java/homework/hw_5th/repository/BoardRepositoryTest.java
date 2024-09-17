package homework.hw_5th.repository;

import homework.hw_5th.entity.Board;
import homework.hw_5th.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void test1(){
        // 게시판 생성
        Board board = new Board();
        boardRepository.save(board);

        // 게시판에 글 3개 생성
        Post post1 = new Post();
        post1.setTitle("제목1");
        post1.setContent("내용1");
        post1.setBoard(board);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setTitle("제목2");
        post2.setContent("내용2");
        post2.setBoard(board);
        postRepository.save(post2);

        Post post3 = new Post();
        post3.setTitle("제목3");
        post3.setContent("내용3");
        post3.setBoard(board);
        postRepository.save(post3);

        Board savedBoard = boardRepository.findById(board.getId()).orElseThrow();

        // Board에서 post 목록 조회
        for (Post post : savedBoard.getPosts()) {
            System.out.println("번호 : " + post.getId());
            System.out.println("제목 : " + post.getTitle());
            System.out.println("내용 : " + post.getContent());
        }
    }
}