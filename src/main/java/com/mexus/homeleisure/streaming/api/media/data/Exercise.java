package com.mexus.homeleisure.streaming.api.media.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    /**
     * pk
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exerciseId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "exerciseId")
    private List<Motion> motions;

    private String shapeVideoPath;
}
