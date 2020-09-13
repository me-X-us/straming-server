package com.mexus.homeleisure.streaming.api.media.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 자세정보 레포지터리
 *
 * @author always0ne
 * @version 1.0
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
