package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> { //JpaRepository<어떤 엔티티인지, pk(id)로 지정한 타입>

    Page<Board> findByTitleContaining(String searchKeyWord, Pageable pageable);

    //findBy+컬럼명+Containing으로 메서드 이름을 만들면 컬럼에서 키워드가 포함된 것을 찾겠다는 의미
}
