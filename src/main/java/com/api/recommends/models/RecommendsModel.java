package com.api.recommends.models;

        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.springframework.hateoas.RepresentationModel;

        import java.io.Serializable;
        import java.math.BigDecimal;
        import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TB_ANIMES")
public class RecommendsModel extends RepresentationModel<RecommendsModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idProduct;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private byte[] image;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal rating;
}
